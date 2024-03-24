package dto.inquiry;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class EditInquiryDto {
    private int id;
    private String category;
    private String title;
    private String content;
}
