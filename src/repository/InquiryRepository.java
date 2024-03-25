package repository;

import java.util.ArrayList;
import java.util.List;

import constants.FilePath;
import entity.Inquiry;
import exception.BaseException;
import exception.DataAccessException;

public class InquiryRepository extends Repository<Inquiry> {
    private static InquiryRepository inquiryRepository;
    private InquiryRepository() {
        super(FilePath.INQUIRY_PATH);
    }

    public static InquiryRepository getInstance(){
        if(inquiryRepository == null)
            inquiryRepository = new InquiryRepository();
        return inquiryRepository;
    }

    public List<Inquiry> getEntityList(int authorId) throws BaseException { // 사용자가 보는 본인의 문의
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
