package dto.inquiry;
import enumeration.inquiry.InquiryCategory;
import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class EditInquiryDto {
    private int id;
    private InquiryCategory category;
    private String title;
    private String content;
}
