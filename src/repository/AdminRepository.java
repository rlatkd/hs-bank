package repository;

import entity.Admin;

public class AdminRepository extends Repository<Admin>{
    private static AdminRepository adminRepository;
    private AdminRepository() {
        super();
        this.path += "Admin.txt";
    }

    public static AdminRepository getInstance(){
        if(adminRepository == null)
            adminRepository = new AdminRepository();
        return adminRepository;
    }
}
