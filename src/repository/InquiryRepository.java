package repository;

import entity.Inquiry;

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

}
