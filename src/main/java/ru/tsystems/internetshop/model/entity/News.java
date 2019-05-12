package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * This class is news entity
 */
@Data
@Entity(name = "news")
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String article;
    @NotNull
    private String text;
    @Column(name = "writing_date")
    private LocalDateTime writingDate;

    private static final long serialVersionUID = 1;

    public News() {
    }

    public News(String article, String text, LocalDateTime writingDate) {
        this.article = article;
        this.text = text;
        this.writingDate = writingDate;
    }
}
