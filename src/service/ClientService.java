package service;

import repository.ClientRepository;

public class ClientService implements LoginService {
    private static ClientService clientService;
    private final ClientRepository clientRepository;
    private ClientService() {
        this.clientRepository = ClientRepository.getInstance();
    }
    public static ClientService getInstance(){
        if(clientService == null)
            clientService = new ClientService();
        return clientService;
    }
    @Override
    public void login() {

    }
}
