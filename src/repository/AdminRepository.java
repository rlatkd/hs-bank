package repository;

import constants.FilePath;
import entity.Admin;
import exception.BaseException;
import exception.DataAccessException;

public class AdminRepository extends Repository<Admin>{
    private static AdminRepository adminRepository;
    private AdminRepository() {
        super(FilePath.ADMIN_PATH);
    }

    public static AdminRepository getInstance(){
        if(adminRepository == null)
            adminRepository = new AdminRepository();
        return adminRepository;
    }

    //로그인 이메일 비밀번호 맞는지 검사
    public Admin get(String email, String password) throws BaseException {
    	load();
    	for (Admin admin : entityList) {
    		if (admin.getEmail().equals(email) && admin.getPassword().equals(password))
    			return admin;
    	}
		return null;
    }
    
    //관리자 등록 중복 검사
    public boolean isExist(String email) throws BaseException {
    	load();
    	for (Admin admin : entityList) {
    		if (admin.getEmail().equals(email))
    			return true;
    	}
    	return false;
    }
}
