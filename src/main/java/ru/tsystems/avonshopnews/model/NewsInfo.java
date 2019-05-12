package ru.tsystems.avonshopnews.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class contains information about news and the class is coming from avonshopnews project by JMS
 * */
@Data
public class NewsInfo implements Serializable {
    public int a;
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
