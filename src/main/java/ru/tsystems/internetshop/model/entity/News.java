package ru.tsystems.internetshop.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class is news entity
 */
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

    public Long getId() {
        return this.id;
    }

    public @NotNull String getArticle() {
        return this.article;
    }

    public @NotNull String getText() {
        return this.text;
    }

    public LocalDateTime getWritingDate() {
        return this.writingDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setArticle(@NotNull String article) {
        this.article = article;
    }

    public void setText(@NotNull String text) {
        this.text = text;
    }

    public void setWritingDate(LocalDateTime writingDate) {
        this.writingDate = writingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News that = (News) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "News(id=" + this.getId() + ", article=" + this.getArticle() + ", text=" + this.getText() + ", writingDate=" + this.getWritingDate() + ")";
    }
}
