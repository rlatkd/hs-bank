package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dto.user.client.UpdateClientDto;
import dto.user.client.GetClientDto;
import dto.user.client.GetCurrentClientDto;
import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import entity.Client;
import exception.DataAccessException;
import exception.ExistingUserException;
import exception.UserNotFoundException;
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
    
    //회원가입
    public void registerClient(RegisterUserDto registerUserDto) throws ExistingUserException, DataAccessException {
    	if (clientRepository.isExistClient(registerUserDto.getEmail())) 
			throw new ExistingUserException();
    	
    	Client client = Client
    					.builder()
    					.name(registerUserDto.getName())
    					.email(registerUserDto.getEmail())
    					.password(registerUserDto.getPassword())
    					.birthDate(registerUserDto.getBirthDate())
    					.gender(registerUserDto.getGender())
    					.phoneNumber(registerUserDto.getPhoneNumber())
    					.address(registerUserDto.getAddress())
    					.createdAt(LocalDate.now().toString())
    					.status("activate")
    					.build();
    	
    	clientRepository.addClient(client);
	}
    
    //로그인
    @Override
    public int login(LoginDto loginDto) throws DataAccessException, UserNotFoundException {
    	Client client = clientRepository.getClient(loginDto.getEmail(), loginDto.getPassword());
    	if (client == null) 
    		throw new UserNotFoundException();
    	
    	return client.getId();
    }
    
    //마이페이지; 조회-현재 로그인 된 사용자 view에 전달
    public GetCurrentClientDto getCurrentClient(int id) throws DataAccessException, UserNotFoundException {
    	Client client = clientRepository.getClient(id);
    	if (client == null) 
    		throw new UserNotFoundException();
    	return GetCurrentClientDto.toDto(client);
    }
    
    //마이페이지; 수정-내 정보 수정
    public void updateClient(UpdateClientDto updateClientDto) throws DataAccessException, UserNotFoundException {
    	Client client = clientRepository.getClient(updateClientDto.getId());
    	if (client == null)
    		throw new UserNotFoundException();
    	client.setName(updateClientDto.getName());
    	client.setEmail(updateClientDto.getEmail());
    	client.setPassword(updateClientDto.getEmail());
    	client.setBirthDate(updateClientDto.getBirthDate());
    	client.setGender(updateClientDto.getGender());
    	client.setPhoneNumber(updateClientDto.getPhoneNumber());
    	client.setAddress(updateClientDto.getAddress());
    	
    	clientRepository.updateClient();
    }
    
    //마이페이지; 삭제-내 정보 삭제
    public void deleteClient(int id) throws DataAccessException, UserNotFoundException {
    	Client client = clientRepository.getClient(id);
    	if (client == null)
    		throw new UserNotFoundException();
    	
    	clientRepository.deleteClient(id);
    }
    
    //모든 고객 계정 id, email 조회
    public ArrayList<GetClientDto> clientList() throws DataAccessException {
    	return clientRepository.getClientList().stream()
    			.map(client -> GetClientDto.toDto(client))
    			.collect(Collectors.toCollection(ArrayList::new));
    }
    
    //비활성화할 고객ID
    public void deactivate(int id) throws DataAccessException, UserNotFoundException {
    	Client client = clientRepository.getClient(id);
    	if (client == null)
    		throw new UserNotFoundException();
    	client.setStatus("deactivate");
    	clientRepository.updateClientStatus(client);
    }
    
    //활성화할 고객ID
    public void activate(int id) throws DataAccessException, UserNotFoundException {
    	Client client = clientRepository.getClient(id);
    	if (client == null)
    		throw new UserNotFoundException();
    	client.setStatus("activate");
    	clientRepository.updateClientStatus(client);
    }
}
