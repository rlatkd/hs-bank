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
                category(inquiry.getCategory()).
                authorName(authorName).
                title(inquiry.getTitle()).
                status(inquiry.getStatus()).
                createdAt(inquiry.getCreatedAt()).build();
    }
}
