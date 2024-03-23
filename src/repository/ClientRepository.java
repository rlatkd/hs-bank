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
    
    public boolean isExistClient(String email) throws DataLoadingException {
    	load();
    	for (Client client : dataList) {
    		if (client.getEmail().equals(email))
    			return true;
    	}
    	return false;
    }
    
    public int getLastId() throws DataLoadingException {
    	load();
    	if (dataList.isEmpty())
    		return 1;
    	
    	int lastIndex = dataList.get(dataList.size() - 1).getId();
    	return lastIndex;
    }
    
    public void addClient(Client client) throws DataLoadingException, DataSavingException {
    	client.setId(getLastId() + 1);
    	dataList.add(client);
    	save();
    }
}
