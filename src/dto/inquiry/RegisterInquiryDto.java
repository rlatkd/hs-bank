package dto.inquiry;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class RegisterInquiryDto {
    private int authorId;
    private String category;
    private String title;
    private String content;
}
