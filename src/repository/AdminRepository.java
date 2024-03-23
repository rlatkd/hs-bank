package repository;

import entity.Admin;
import exception.DataLoadingException;

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
    
    //로그인 이메일 비밀번호 맞는지 검사
    public Admin getAdmin(String email, String password) throws DataLoadingException {
    	load();
    	for (Admin admin : dataList) {
    		if (admin.getEmail().equals(email) && admin.getPassword().equals(password))
    			return admin;
    	}
		return null;
    }
}
