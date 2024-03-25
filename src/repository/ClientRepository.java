package repository;

import entity.Client;
import exception.BaseException;
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
    public boolean isExist(String email) throws BaseException {
    	load();
    	for (Client client : entityList) {
    		if (client.getEmail().equals(email))
    			return true;
    	}
    	return false;
    }

    //로그인 이메일 비밀번호 맞는지 검사
    public Client get(String email, String password) throws BaseException {
    	load();
    	for (Client client : entityList) {
    		if (client.getEmail().equals(email) && client.getPassword().equals(password))
    			return client;
    	}
    	return null;
    }
}
