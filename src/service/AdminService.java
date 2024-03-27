package service;

import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import entity.Admin;
import enumeration.ActivationStatus;
import exception.BaseException;
import exception.user.admin.AdminDeactivateException;
import exception.user.admin.AdminNotFoundException;
import exception.user.admin.AdminExistException;
import exception.user.client.ClientDeactivateException;
import repository.AdminRepository;
import dto.user.admin.RegisterMainAdminDto;

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
        if (adminRepository.isExist(registerAdminDto.getEmail())) throw new AdminExistException();
        adminRepository.add((Admin) registerAdminDto.toEntity());
    }

    public void registerMain(RegisterUserDto registerMainAdminDto) throws BaseException {
        if (adminRepository.isExist(registerMainAdminDto.getEmail())) throw new AdminExistException();
        adminRepository.add((Admin) registerMainAdminDto.toEntity());
    }

    public void checkExistMain(RegisterUserDto registerMainAdminDto) throws BaseException {
        if (adminRepository.isExist(registerMainAdminDto.getEmail())) throw new AdminExistException();
    }

    //로그인
    @Override
    public int login(LoginDto loginDto) throws BaseException {
    	Admin admin = adminRepository.get(loginDto.getEmail(), loginDto.getPassword());
		if (admin == null) throw new AdminNotFoundException();
        if (admin.getStatus() == ActivationStatus.DEACTIVATE) throw new AdminDeactivateException();

        return admin.getId();
    }

}
