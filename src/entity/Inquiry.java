package entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Inquiry {
    private int id;
    private String authorId;
    private String category;
    private String title;
    private String content;
    private String createdAt;
    private String status;
}
