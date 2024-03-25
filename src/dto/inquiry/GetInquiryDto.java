package dto.inquiry;
import entity.Inquiry;
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

    public static GetInquiryDto toDto(Inquiry inquiry){
        return GetInquiryDto.builder()
                .category(inquiry.getCategory())
                .authorId(inquiry.getAuthorId())
                .status((inquiry.getStatus()))
                .content(inquiry.getContent())
                .title(inquiry.getTitle())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }
}
