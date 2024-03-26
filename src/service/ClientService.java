package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dto.user.RegisterUserDto;
import dto.user.client.UpdateClientDto;
import dto.user.client.GetClientDto;
import dto.user.client.GetCurrentClientDto;
import dto.user.LoginDto;
import entity.Client;
import enumeration.ActivationStatus;
import exception.BaseException;
import exception.user.client.ClientDeactivateException;
import exception.user.client.ClientNotFoundException;
import exception.user.client.ClientExistException;
import repository.ClientRepository;

public class ClientService implements UserService {
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
	@Override
	public void register(RegisterUserDto registerClientDto) throws BaseException {
    	if (clientRepository.isExist(registerClientDto.getEmail()))
			throw new ClientExistException();
    	clientRepository.add((Client) registerClientDto.toEntity());
	}

	//로그인
    @Override
    public int login(LoginDto loginDto) throws BaseException {
    	Client client = clientRepository.get(loginDto.getEmail(), loginDto.getPassword());
    	if (client == null) throw new ClientNotFoundException();
		if (client.getStatus() == ActivationStatus.DEACTIVATE) throw new ClientDeactivateException();
    	
    	return client.getId();
    }
    
    //마이페이지; 조회-현재 로그인 된 사용자 view에 전달
    public GetCurrentClientDto getCurrentClient(int id) throws BaseException {
    	Client client = clientRepository.get(id);
    	if (client == null) throw new ClientNotFoundException();
    	return GetCurrentClientDto.toDto(client);
    }
    
    //마이페이지; 수정-내 정보 수정
    public void updateClient(UpdateClientDto updateClientDto) throws BaseException {
    	Client client = clientRepository.get(updateClientDto.getId());
    	if (client == null) throw new ClientNotFoundException();

    	client.setName(updateClientDto.getName());
    	client.setEmail(updateClientDto.getEmail());
    	client.setPassword(updateClientDto.getEmail());
    	client.setBirthDate(updateClientDto.getBirthDate());
    	client.setGender(updateClientDto.getGender());
    	client.setPhoneNumber(updateClientDto.getPhoneNumber());

    	clientRepository.update();
    }
    
    //마이페이지; 삭제-내 정보 삭제
    public void removeClient(int id) throws BaseException {
    	Client client = clientRepository.get(id);
    	if (client == null) throw new ClientNotFoundException();
    	clientRepository.remove(id);
    }
    
    //모든 고객 계정 id, email 조회
    public List<GetClientDto> getClientList() throws BaseException {
    	return clientRepository.getEntityList().stream()
    			.map(client -> GetClientDto.toDto(client))
    			.collect(Collectors.toCollection(ArrayList::new));
    }
    
    //비활성화할 고객ID
    public void deactivate(int id) throws BaseException {
    	Client client = clientRepository.get(id);
    	if (client == null) throw new ClientNotFoundException();
    	client.setStatus(ActivationStatus.DEACTIVATE);
    	clientRepository.update();
    }
    
    //활성화할 고객ID
    public void activate(int id) throws BaseException {
    	Client client = clientRepository.get(id);
    	if (client == null) throw new ClientNotFoundException();
    	client.setStatus(ActivationStatus.ACTIVATE);
    	clientRepository.update();
    }
}
