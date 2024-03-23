package service;

import dto.admin.LoginDto;
import entity.Admin;
import exception.DataLoadingException;
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
}
