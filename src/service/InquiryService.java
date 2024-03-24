package service;

import dto.inquiry.EditInquiryDto;
import dto.inquiry.GetInquiryDto;
import dto.inquiry.GetInquiryListDto;
import dto.inquiry.RegisterInquiryDto;
import entity.Inquiry;
import exception.DataLoadingException;
import exception.DataSavingException;
import exception.EmptyInquiryListException;
import exception.InquiryNotFoundException;
import repository.ClientRepository;
import repository.InquiryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static utils.utils.dateTimeFormatter;

public class InquiryService {
    private static InquiryService inquiryService;
    private final InquiryRepository inquiryRepository;

    private final ClientRepository clientRepository;

    private InquiryService() {
        this.inquiryRepository = InquiryRepository.getInstance();
        this.clientRepository = ClientRepository.getInstance();
    }

    public static InquiryService getInstance() {
        if (inquiryService == null)
            inquiryService = new InquiryService();
        return inquiryService;
    }

    // GetInquiryDto로 보내줌
    public GetInquiryDto getInquiry(int id) throws DataLoadingException, InquiryNotFoundException {
        Inquiry inquiry = inquiryRepository.getInquiry(id);
        if (inquiry == null) throw new InquiryNotFoundException();

        return GetInquiryDto.builder()
                .category(inquiry.getCategory())
                .authorId(inquiry.getAuthorId())
                .status((inquiry.getStatus()))
                .content(inquiry.getContent())
                .title(inquiry.getTitle())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }

    public void deleteInquiry(int id) throws DataLoadingException, InquiryNotFoundException, DataSavingException {
        Inquiry inquiry = inquiryRepository.getInquiry(id);
        if (inquiry == null) throw new InquiryNotFoundException();
        inquiryRepository.removeInquiry(id);
    }

    //문의목록 조회
    public ArrayList<GetInquiryListDto> getInquiryList() throws DataLoadingException, EmptyInquiryListException {
        ArrayList<GetInquiryListDto> InquiryList = new ArrayList<>();
        if (inquiryRepository.getInquiryList().isEmpty()) throw new EmptyInquiryListException();
        for (Inquiry inq : inquiryRepository.getInquiryList()) {
            GetInquiryListDto getInquiryListDto = GetInquiryListDto.builder().
                    category(inq.getCategory()).
                    authorName(clientRepository.getClient(inq.getId()).getName()).
                    title(inq.getTitle()).
                    status(inq.getStatus()).
                    createdAt(inq.getCreatedAt()).build();
            InquiryList.add(getInquiryListDto);
        }
        return InquiryList;
    }

    // 문의 등록
    public void registerInquiry(RegisterInquiryDto registerInquiryDto) throws DataLoadingException, DataSavingException {
        Inquiry inq = Inquiry.builder()
                .authorId(registerInquiryDto.getAuthorId())
                .category(registerInquiryDto.getCategory())
                .status("대기 중")
                .content(registerInquiryDto.getContent())
                .title(registerInquiryDto.getTitle())
                .createdAt((LocalDateTime.now()).format(dateTimeFormatter))
                .build();

        inquiryRepository.addInquiry(inq);
    }

    public void editInquiry(EditInquiryDto editInquiryDto) throws DataLoadingException, InquiryNotFoundException, DataSavingException {
        Inquiry inq = inquiryRepository.getInquiry(editInquiryDto.getId());
        if (inq == null) throw new InquiryNotFoundException();
        inq.setCategory(editInquiryDto.getCategory());
        inq.setContent(editInquiryDto.getContent());
        inq.setTitle(editInquiryDto.getTitle());

        inquiryRepository.update();
    }

    // 문의 상태 :: 반려
    public void rejectInquiry(int id) throws DataLoadingException, InquiryNotFoundException, DataSavingException {
        Inquiry inquiry = inquiryRepository.getInquiry(id);
        if(inquiry == null) throw new InquiryNotFoundException();
        inquiry.setStatus("반려");
        inquiryRepository.update();
    }

    // 문의 상태 :: 완료
    public void completeInquiry(int id) throws DataLoadingException, InquiryNotFoundException, DataSavingException {
        Inquiry inquiry = inquiryRepository.getInquiry(id);
        if(inquiry == null) throw new InquiryNotFoundException();
        inquiry.setStatus("처리");
        inquiryRepository.update();
    }

    public ArrayList<GetInquiryListDto> getInquiryList(int authorId) throws DataLoadingException, EmptyInquiryListException {
        ArrayList<GetInquiryListDto> dtoList = new ArrayList<>();
        ArrayList<Inquiry> inquiryList = inquiryRepository.getInquiryList(authorId);
        if (inquiryList.isEmpty()) throw new EmptyInquiryListException();
        for (Inquiry inq : inquiryList) {
            GetInquiryListDto getInquiryListDto = GetInquiryListDto.builder().
                    category(inq.getCategory()).
                    authorName(clientRepository.getClient(inq.getId()).getName()).
                    title(inq.getTitle()).
                    status(inq.getStatus()).
                    createdAt(inq.getCreatedAt()).build();
            dtoList.add(getInquiryListDto);
        }
        return dtoList;

    }
}
