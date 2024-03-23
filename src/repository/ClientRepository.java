package repository;

import entity.Client;
import exception.DataLoadingException;
import exception.DataSavingException;

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
    public boolean isExistClient(String email) throws DataLoadingException {
    	load();
    	for (Client client : dataList) {
    		if (client.getEmail().equals(email))
    			return true;
    	}
    	return false;
    }
    
    //회원가입 마지막 id 추출
    public int getLastId() throws DataLoadingException {
    	load();
    	if (dataList.isEmpty())
    		return 0;
    	
    	int lastId = dataList.get(dataList.size() - 1).getId();
    	return lastId;
    }
    
    //마지막 id에 데이터 저장
    public void addClient(Client client) throws DataLoadingException, DataSavingException {
        client.setId(getLastId() + 1);
    	dataList.add(client);
    	save();
    }
    
    //로그인 이메일 비밀번호 맞는지 검사
    public Client getClient(String email, String password) throws DataLoadingException {
    	load();
    	for (Client client : dataList) {
    		if (client.getEmail().equals(email) && client.getPassword().equals(password))
    			return client;
    	}
    	return null;
    }

    public Client getClient(int ownerId) throws DataLoadingException {
        load();
        for(Client client : dataList)
            if(client.getId() == ownerId) return client;
        return null;
    }
}
