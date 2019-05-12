package ru.tsystems.internetshop.model.DTO;

import java.util.Objects;

/**
 * This class is DTO for news entity
 */
public class NewsDTOWithFormat {
    private long id;
    private String article;
    private String text;
    private String writingDate;

    public NewsDTOWithFormat() {
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

    public String getWritingDate() {
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

    public void setWritingDate(String writingDate) {
        this.writingDate = writingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTOWithFormat newsDTOWithFormat = (NewsDTOWithFormat) o;
        return Objects.equals(id, newsDTOWithFormat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "NewsDTOWithFormat(id=" + this.getId() + ", article=" + this.getArticle() + ", text=" + this.getText() + ", writingDate=" + this.getWritingDate() + ")";
    }
}
