package service;

import repository.InquiryRepository;

public class InquiryService {
    private static InquiryService inquiryService;
    private final InquiryRepository inquiryRepository;
    private InquiryService() {
        this.inquiryRepository = InquiryRepository.getInstance();
    }
    public static InquiryService getInstance(){
        if(inquiryService == null)
            inquiryService = new InquiryService();
        return inquiryService;
    }
}
