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
        return "GetInquiryDto{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", status='" + status + '\'' +
                '}' + '\n';
    }
}
