package service;

import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import entity.Admin;
import exception.DataAccessException;
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
    
    //로그인
    @Override
    public int login(LoginDto loginDto) throws DataAccessException, IncorrectCredentialsException {
    	Admin admin = adminRepository.getAdmin(loginDto.getEmail(), loginDto.getPassword());
		if (admin == null)
			throw new IncorrectCredentialsException();
    	
		return admin.getId();
    }
    
    //서브관리자 생성
    public void registerAdmin(RegisterUserDto registerUserDto) throws DataAccessException, ExistingUserException {
    	if (adminRepository.isExistAdmin(registerUserDto.getEmail()))
    		throw new ExistingUserException();
    	
    	Admin admin = Admin
    					.builder()
    					.name(registerUserDto.getName())
    					.email(registerUserDto.getEmail())
    					.password(registerUserDto.getPassword())
    					.status("sub")
    					.build();
    	
    	adminRepository.addAdmin(admin);
    }
}
