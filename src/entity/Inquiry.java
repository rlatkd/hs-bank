package entity;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
public class Inquiry implements Serializable {
    private int id;
    private int authorId;
    private String category;
    private String title;
    private String content;
    private String createdAt;
    private String status;

    public void setId(int id) {
        this.id = id;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

