package dto.inquiry;
import entity.Inquiry;
import enumeration.inquiry.InquiryCategory;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class GetInquiryDto {
    private int id;
    private int authorId;
    private String category;
    private String title;
    private String content;
    private String createdAt;
    private String status;

    public static GetInquiryDto toDto(Inquiry inquiry){
        return GetInquiryDto.builder()
                .id(inquiry.getId())
                .category(inquiry.getCategory().getKorean())
                .authorId(inquiry.getAuthorId())
                .status(inquiry.getStatus().getKorean())
                .content(inquiry.getContent())
                .title(inquiry.getTitle())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }

    @Override
    public String toString() {
        return "[문의ID : " + id + "] " +
                "[작성자ID : " + authorId + "] " +
                "[분류 : " + category + "] " +
                "[제목 : " + title + "] " +
                "[내용 : " + content + "] " +
                "[생성일시 : " + createdAt + "] " +
                "[처리상태 : " + status + "] " +
                '\n';
    }
}
