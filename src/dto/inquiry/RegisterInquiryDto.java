package dto.inquiry;
import entity.Inquiry;
import lombok.Builder;
import lombok.Getter;

import static utils.DateUtils.dateTimeNow;

@Builder
@Getter
public class RegisterInquiryDto {
    private int authorId;
    private String category;
    private String title;
    private String content;

    public Inquiry toEntity(){
        return Inquiry.builder()
                .authorId(authorId)
                .category(category)
                .status("대기")
                .content(content)
                .title(title)
                .createdAt(dateTimeNow)
                .build();
    }
}
