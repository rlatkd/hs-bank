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
                category(inquiry.getCategory().getKorean()).
                authorName(authorName).
                title(inquiry.getTitle()).
                status(inquiry.getStatus().getKorean()).
                createdAt(inquiry.getCreatedAt()).build();
    }

    @Override
    public String toString() {
        return "GetInquiryListDto{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", status='" + status + '\'' +
                '}' + "\n";
    }
}
