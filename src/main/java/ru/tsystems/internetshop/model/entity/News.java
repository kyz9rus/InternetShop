package ru.tsystems.internetshop.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity(name = "news")
@Table(name = "news")
public class News{
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String article;
    @NotNull
    private String text;
    @Column(name = "writing_date")
    private LocalDate writingDate;

    private static final long serialVersionUID = 1;

    public News() {
    }

    public News(String article, String text, LocalDate writingDate) {
        this.article = article;
        this.text = text;
        this.writingDate = writingDate;
    }
}
