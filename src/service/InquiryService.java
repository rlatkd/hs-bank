package service;

import dto.inquiry.EditInquiryDto;
import dto.inquiry.GetInquiryDto;
import dto.inquiry.GetInquiryListDto;
import dto.inquiry.RegisterInquiryDto;
import entity.Client;
import entity.Inquiry;
import enumeration.inquiry.InquiryStatus;
import exception.BaseException;
import exception.DataAccessException;
import exception.inquiry.EmptyInquiryListException;
import exception.inquiry.InquiryNotFoundException;
import repository.ClientRepository;
import repository.InquiryRepository;

import java.util.ArrayList;
import java.util.List;

public class InquiryService {
    private static InquiryService inquiryService;
    private final InquiryRepository inquiryRepository;

    private final ClientRepository clientRepository;

    private InquiryService() {
        this.inquiryRepository = InquiryRepository.getInstance();
        this.clientRepository = ClientRepository.getInstance();
    }

    public static InquiryService getInstance() {
        if (inquiryService == null) inquiryService = new InquiryService();
        return inquiryService;
    }

    // GetInquiryDto로 보내줌
    public GetInquiryDto getInquiry(int id) throws BaseException {
        Inquiry inquiry = inquiryRepository.get(id);
        if (inquiry == null) throw new InquiryNotFoundException();
        return GetInquiryDto.toDto(inquiry);
    }

    //문의 삭제
    public void removeInquiry(int id) throws BaseException {
        Inquiry inquiry = inquiryRepository.get(id);
        if (inquiry == null) throw new InquiryNotFoundException();
        inquiryRepository.remove(id);
    }

    //문의목록 조회
    public List<GetInquiryListDto> getInquiryList() throws BaseException {
        List<Inquiry> inquiryList = inquiryRepository.getEntityList();
        if (inquiryList.isEmpty()) throw new EmptyInquiryListException();

        List<GetInquiryListDto> getInquiryListDtoList = new ArrayList<>();
        for (Inquiry inquiry : inquiryList){
            Client author = clientRepository.get(inquiry.getAuthorId());
            GetInquiryListDto getInquiryListDto = GetInquiryListDto.toDto(inquiry, author.getName());
            getInquiryListDtoList.add(getInquiryListDto);
        }
        return getInquiryListDtoList;
    }

    // 문의 등록
    public void registerInquiry(RegisterInquiryDto registerInquiryDto) throws BaseException {
        inquiryRepository.add(registerInquiryDto.toEntity());
    }

    //문의 수정
    public void editInquiry(EditInquiryDto editInquiryDto) throws BaseException {
        Inquiry inq = inquiryRepository.get(editInquiryDto.getId());
        if (inq == null) throw new InquiryNotFoundException();
        inq.setCategory(editInquiryDto.getCategory());
        inq.setContent(editInquiryDto.getContent());
        inq.setTitle(editInquiryDto.getTitle());

        inquiryRepository.update();
    }

    // 문의 상태 :: 반려
    public void rejectInquiry(int id) throws BaseException {
        Inquiry inquiry = inquiryRepository.get(id);
        if(inquiry == null) throw new InquiryNotFoundException();
        inquiry.setStatus(InquiryStatus.REJECT);
        inquiryRepository.update();
    }

    // 문의 상태 :: 완료
    public void completeInquiry(int id) throws BaseException {
        Inquiry inquiry = inquiryRepository.get(id);
        if(inquiry == null) throw new InquiryNotFoundException();
        inquiry.setStatus(InquiryStatus.COMPLETE);
        inquiryRepository.update();
    }

    public List<GetInquiryListDto> getInquiryList(int authorId) throws BaseException {
        List<Inquiry> inquiryList = inquiryRepository.getEntityList(1);
        if (inquiryList.isEmpty()) throw new EmptyInquiryListException();

        List<GetInquiryListDto> getInquiryListDtoList = new ArrayList<>();
        for (Inquiry inquiry : inquiryList){
            Client author = clientRepository.get(inquiry.getAuthorId());
            GetInquiryListDto getInquiryListDto = GetInquiryListDto.toDto(inquiry, author.getName());
            getInquiryListDtoList.add(getInquiryListDto);
        }
        return getInquiryListDtoList;
    }
}
