package service;

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
}
