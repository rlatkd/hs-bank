package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dto.client.DeactivateDto;
import dto.client.GetClientDto;
import dto.client.LoginDto;
import dto.client.SignupDto;
import entity.Client;
import exception.ClientNotFoundException;
import exception.DataLoadingException;
import exception.DataSavingException;
import exception.ExistingUserException;
import exception.IncorrectCredentialsException;
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
    
    //회원가입
    public void signup(SignupDto signupDto) throws ExistingUserException, DataLoadingException, DataSavingException {
    	if (clientRepository.isExistClient(signupDto.getEmail())) 
    		throw new ExistingUserException();
    	
    	Client client = Client.builder()
    					.email(signupDto.getEmail())
    					.password(signupDto.getPassword())
    					.name(signupDto.getName())
    					.birthDate(signupDto.getBirthDate())
    					.gender(signupDto.getGender())
    					.phoneNumber(signupDto.getPhoneNumber())
    					.address(signupDto.getAddress())
    					.createdAt(LocalDate.now().toString())
    					.status("activate")
    					.build();
    	
    	clientRepository.addClient(client);
	}
    
    //로그인
    public int login(LoginDto loginDto) throws DataLoadingException, IncorrectCredentialsException {
    	Client client = clientRepository.getClient(loginDto.getEmail(), loginDto.getPassword());
    	if (client == null) 
    		throw new IncorrectCredentialsException();
    	
    	return client.getId();
    }
    
    //모든 고객 계정 id, email 조회
    public ArrayList<GetClientDto> clientList() throws DataLoadingException {
    	return clientRepository.getClientList().stream()
    			.map(client -> GetClientDto.toDto(client))
    			.collect(Collectors.toCollection(ArrayList::new));
    }
    
    //비활성화할 고객ID
    public void deactivate(int id) throws DataLoadingException, ClientNotFoundException, DataSavingException {
    	Client client = clientRepository.getClient(id);
    	if (client == null)
    		throw new ClientNotFoundException();
    	client.setStatus("deactivate");
    	clientRepository.updateClient(client);
    }
    
    //활성화할 고객ID
    public void activate(int id) throws DataLoadingException, ClientNotFoundException, DataSavingException {
    	Client client = clientRepository.getClient(id);
    	if (client == null)
    		throw new ClientNotFoundException();
    	client.setStatus("activate");
    	clientRepository.updateClient(client);
    }
}
