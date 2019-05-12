package ru.tsystems.internetshop.model.DTO;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * This class is DTO for news entity
 */
public class NewsDTO {
    private long id;
    private String article;
    private String text;
    private LocalDateTime writingDate;

    public NewsDTO() {
    }

    public long getId() {
        return this.id;
    }

    public String getArticle() {
        return this.article;
    }

    public String getText() {
        return this.text;
    }

    public LocalDateTime getWritingDate() {
        return this.writingDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWritingDate(LocalDateTime writingDate) {
        this.writingDate = writingDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(id, newsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NewsDTO(id=" + this.getId() + ", article=" + this.getArticle() + ", text=" + this.getText() + ", writingDate=" + this.getWritingDate() + ")";
    }
}
