package dto.inquiry;
import entity.Inquiry;
import enumeration.inquiry.InquiryCategory;
import enumeration.inquiry.InquiryStatus;
import lombok.Builder;
import lombok.Getter;

import static utils.DateTimeGenerator.getDateTimeNow;

@Builder
@Getter
public class RegisterInquiryDto {
    private int authorId;
    private InquiryCategory category;
    private String title;
    private String content;

    public Inquiry toEntity(){
        return Inquiry.builder()
                .authorId(authorId)
                .category(category)
                .status(InquiryStatus.WAIT)
                .content(content)
                .title(title)
                .createdAt(getDateTimeNow())
                .build();
    }
}
