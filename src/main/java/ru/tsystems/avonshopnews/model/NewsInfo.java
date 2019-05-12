package ru.tsystems.avonshopnews.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NewsInfo implements Serializable {
    private Long id;
    private String article;
    private String text;
    private LocalDateTime writingDate;

    private static final long serialVersionUID = 1;

    public NewsInfo(String article, String text, LocalDateTime writingDate) {
        this.article = article;
        this.text = text;
        this.writingDate = writingDate;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
