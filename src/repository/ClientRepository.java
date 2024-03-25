package repository;

import java.util.List;

import entity.Client;
import exception.DataAccessException;

public class ClientRepository extends Repository<Client> {
    private static ClientRepository clientRepository;
    private ClientRepository() {
        super();
        this.path += "Client.txt";
    }

    public static ClientRepository getInstance(){
        if(clientRepository == null)
            clientRepository = new ClientRepository();
        return clientRepository;
    }
    
    //회원가입 이메일 중복 검사
    public boolean isExistClient(String email) throws DataAccessException {
    	load();
    	for (Client client : dataList) {
    		if (client.getEmail().equals(email))
    			return true;
    	}
    	return false;
    }
    
    //회원가입 마지막 id 추출
    private int getLastId() {
    	if (dataList.isEmpty())
    		return 0;
    	
    	int lastId = dataList.get(dataList.size() - 1).getId();
    	return lastId;
    }
    
    //마지막 id에 데이터 저장
    public void addClient(Client client) throws DataAccessException {
        load();
        client.setId(getLastId() + 1);
    	dataList.add(client);
    	save();
    }
    
    //로그인 이메일 비밀번호 맞는지 검사
    public Client getClient(String email, String password) throws DataAccessException {
    	load();
    	for (Client client : dataList) {
    		if (client.getEmail().equals(email) && client.getPassword().equals(password))
    			return client;
    	}
    	return null;
    }
    
    //고객 관리 서비스 진입 시 모든 고객 id, email 조회
    public List<Client> getClientList() throws DataAccessException {
    	load();
    	return dataList;
    }
       
    //선택한 고객 상태변경
    public void updateClientStatus(Client client) throws DataAccessException {
    	save();
    }

    //고객 정보 조회
    public Client getClient(int id) throws DataAccessException {
        load();
        for(Client client : dataList)
            if(client.getId() == id) return client;
        return null;
    }
    
    //고객 정보 수정
    public void updateClient() throws DataAccessException {
    	save();
    }
    
    //고객 정보 삭제
    public void deleteClient(int id) throws DataAccessException {
    	load();
    	for (int i = 0; i < dataList.size(); i++) {
			if (dataList.get(i).getId() == id) {
				dataList.remove(i);
				break;
			}
		}
    	save();
    }
}
