package dto.inquiry;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RemoveInquiryDto {
    private int id;
    private int authorId;
}
