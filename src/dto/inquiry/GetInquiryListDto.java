package dto.inquiry;
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
}
