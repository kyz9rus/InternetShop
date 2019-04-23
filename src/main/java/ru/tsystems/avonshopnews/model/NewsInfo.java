package ru.tsystems.avonshopnews.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class NewsInfo implements Serializable {
    private Long id;
    private String article;
    private String text;
    private LocalDate writingDate;

    private static final long serialVersionUID = 1;

    public NewsInfo(String article, String text, LocalDate writingDate) {
        this.article = article;
        this.text = text;
        this.writingDate = writingDate;
    }
}
