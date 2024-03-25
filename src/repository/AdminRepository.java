package repository;

import entity.Admin;
import exception.DataAccessException;

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
    public Admin getAdmin(String email, String password) throws DataAccessException {
    	load();
    	for (Admin admin : dataList) {
    		if (admin.getEmail().equals(email) && admin.getPassword().equals(password))
    			return admin;
    	}
		return null;
    }
    
    //관리자 등록 중복 검사
    public boolean isExistAdmin(String email) throws DataAccessException {
    	load();
    	for (Admin admin : dataList) {
    		if (admin.getEmail().equals(email))
    			return true;
    	}
    	return false;
    }
    
    //관리자 명단 마지막 id 추출
    private int getLastId() {
        if (dataList.isEmpty())
            return 0;
        int lastId = dataList.get(dataList.size() - 1).getId();
        return lastId;
    }
    
    //마지막 id에 서브관리자 데이터 저장
    public void addAdmin(Admin admin) throws DataAccessException {
    	load();
    	admin.setId(getLastId() + 1);
    	dataList.add(admin);
    	save();
    }
}
