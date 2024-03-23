package service;

import java.time.LocalDate;

import dto.admin.LoginDto;
import dto.user.RegisterUserDto;
import entity.Admin;
import exception.DataLoadingException;
import exception.DataSavingException;
import exception.ExistingUserException;
import exception.IncorrectCredentialsException;
import repository.AdminRepository;

public class AdminService implements LoginService {
    private static AdminService adminService;
    private final AdminRepository adminRepository;
    private AdminService() {
        this.adminRepository = AdminRepository.getInstance();
    }
    public static AdminService getInstance(){
        if(adminService == null)
            adminService = new AdminService();
        return adminService;
    }
    @Override
    public void login() {

    }
    
    //로그인
    public int login(LoginDto loginDto) throws DataLoadingException, IncorrectCredentialsException {
    	Admin admin = adminRepository.getAdmin(loginDto.getEmail(), loginDto.getPassword());
		if (admin == null)
			throw new IncorrectCredentialsException();
    	
		return admin.getId();
    }
    
    //서브관리자 생성
    public void registerAdmin(RegisterUserDto registerUserDto) throws DataLoadingException, ExistingUserException, DataSavingException {
    	if (adminRepository.isExistAdmin(registerUserDto.getEmail()))
    		throw new ExistingUserException();
    	
    	Admin admin = Admin
    					.builder()
    					.email(registerUserDto.getEmail())
    					.password(registerUserDto.getPassword())
    					.name(registerUserDto.getName())
    					.birthDate(registerUserDto.getBirthDate())
    					.gender(registerUserDto.getGender())
    					.phoneNumber(registerUserDto.getPhoneNumber())
    					.address(registerUserDto.getAddress())
    					.createdAt(LocalDate.now().toString())
    					.status("sub")
    					.build();
    	
    	adminRepository.addAdmin(admin);
    }
}
