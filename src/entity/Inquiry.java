package entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Inquiry implements Serializable {
    private int id;
    private String authorId;
    private String category;
    private String title;
    private String content;
    private String createdAt;
    private String status;
}
