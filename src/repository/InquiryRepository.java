package repository;

import java.util.ArrayList;
import java.util.List;

import entity.Inquiry;
import exception.DataAccessException;

public class InquiryRepository extends Repository<Inquiry> {
    private static InquiryRepository inquiryRepository;
    private InquiryRepository() {
        super();
        this.path += "Inquiry.txt";
    }

    public static InquiryRepository getInstance(){
        if(inquiryRepository == null)
            inquiryRepository = new InquiryRepository();
        return inquiryRepository;
    }
    
    public Inquiry getInquiry(int id) throws DataAccessException { // 이걸 서비스가 받으면 서비스가 DTO로 변환해서 그걸 view에서 Dto2String이든 뭐든 해서 보여줌
    	load();
    	for(Inquiry inq : dataList) {
    		if(inq.getId() == id) {
    			return inq;
    		}
    	}
    	return null; //for문 밖에서도 던질 거 필요함     	
    }

    private int getLastId() {
        if(dataList.isEmpty())
            return 0;

        int lastId = dataList.get(dataList.size() - 1).getId();
        return lastId;
    }

    public List<Inquiry> getInquiryList() throws DataAccessException { // 반환형이 ArrayList라서 + 관리자가 보는 남들의 전체 문의
    	load();
    	return dataList;
    }

    public ArrayList<Inquiry> getInquiryList(int authorId) throws DataAccessException { // 사용자가 보는 본인의 문의
        load();
        ArrayList<Inquiry> inquiryList = new ArrayList<>(); // new 안 하면 null... 지금은 빈 거
        for (Inquiry inq : dataList) {
            if (inq.getAuthorId() == authorId) {
                inquiryList.add(inq);
            }
        }
        return inquiryList;
    }

    public void addInquiry(Inquiry inq) throws DataAccessException { // 문의 추가
        load();
        inq.setId(getLastId() + 1);
        dataList.add(inq);
        save();
    }

    public void removeInquiry(int id) throws DataAccessException { // 문의 삭제
        load();
        for (int i = 0; i < dataList.size(); i++) {
            if(dataList.get(i).getId() == id) {
                dataList.remove(i);
                break;
            }
        }
        save();
    }

    public void update() throws DataAccessException { // save 쓰려고 만든 거 (업데이트 때 사용)
        save();
    }
    
    
}
