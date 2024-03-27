package dto.inquiry;
import entity.Inquiry;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class GetInquiryListDto {
    private int id;
    private String authorName;
    private String category;
    private String title;
    private String createdAt;
    private String status;

    public static GetInquiryListDto toDto(Inquiry inquiry, String authorName){
        return GetInquiryListDto.builder().
                id(inquiry.getId()).
                category(inquiry.getCategory().getKorean()).
                authorName(authorName).
                title(inquiry.getTitle()).
                status(inquiry.getStatus().getKorean()).
                createdAt(inquiry.getCreatedAt()).build();
    }

    @Override
    public String toString() {
        return "[문의ID : " + id + "] " +
                "[작성자 이름 : " + authorName + "] " +
                "[분류 : " + category + "] " +
                "[제목 : " + title + "] " +
                "[생성일시 : " + createdAt + "] " +
                "[처리상태 : " + status + "] " +
                '\n';
    }
}
