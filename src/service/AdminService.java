package service;

import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import entity.Admin;
import exception.BaseException;
import exception.DataAccessException;
import exception.user.ExistingUserException;
import exception.user.UserNotFoundException;
import exception.user.admin.AdminNotFoundException;
import exception.user.admin.ExistingAdminException;
import repository.AdminRepository;

public class AdminService implements UserService {
    private static AdminService adminService;
    private final AdminRepository adminRepository;
    private AdminService() {
        this.adminRepository = AdminRepository.getInstance();
    }
    public static AdminService getInstance(){
        if(adminService == null) adminService = new AdminService();
        return adminService;
    }

    //서브관리자 생성
    @Override
    public void register(RegisterUserDto registerAdminDto) throws BaseException {
        if (adminRepository.isExist(registerAdminDto.getEmail())) throw new ExistingAdminException();
        adminRepository.add((Admin) registerAdminDto.toEntity());
    }

    //로그인
    @Override
    public int login(LoginDto loginDto) throws BaseException {
    	Admin admin = adminRepository.get(loginDto.getEmail(), loginDto.getPassword());
		if (admin == null) throw new AdminNotFoundException();
		return admin.getId();
    }
    

}
