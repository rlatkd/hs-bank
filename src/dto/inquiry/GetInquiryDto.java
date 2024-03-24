package dto.inquiry;
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
}
