package repository;

import entity.Client;

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
}
