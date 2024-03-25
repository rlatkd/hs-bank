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

    public List<Inquiry> getEntityList(int authorId) throws DataAccessException { // 사용자가 보는 본인의 문의
        load();
        List<Inquiry> inquiryList = new ArrayList<>(); // new 안 하면 null... 지금은 빈 거
        for (Inquiry inquiry : entityList) {
            if (inquiry.getAuthorId() == authorId) {
                inquiryList.add(inquiry);
            }
        }
        return inquiryList;
    }
}
