package service;

import java.time.LocalDate;
import java.util.List;

import dto.client.LoginDto;
import dto.client.SignupDto;
import entity.Client;
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
    public void login(LoginDto loginDto) throws DataLoadingException, IncorrectCredentialsException {
    	if (!clientRepository.isCorrect(loginDto.getEmail(), loginDto.getPassword())) 
    		throw new IncorrectCredentialsException();
    }
}



