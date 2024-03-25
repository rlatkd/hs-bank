package entity;

import enumeration.inquiry.InquiryCategory;
import enumeration.inquiry.InquiryStatus;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Inquiry extends Entity {
    private int authorId;
    private InquiryCategory category;
    private String title;
    private String content;
    private String createdAt;
    private InquiryStatus status;

    public void setCategory(InquiryCategory category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(InquiryStatus status) {
        this.status = status;
    }
}

