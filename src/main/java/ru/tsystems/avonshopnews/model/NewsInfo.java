package ru.tsystems.avonshopnews.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class dequeue from "news queue" (embedded wildfly queue)
 * It has to match with class on another side (Avon Shop News)
 */
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

    public Long getId() {
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

    public void setArticle(String article) {
        this.article = article;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setWritingDate(LocalDateTime writingDate) {
        this.writingDate = writingDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NewsInfo)) return false;
        final NewsInfo other = (NewsInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$article = this.getArticle();
        final Object other$article = other.getArticle();
        if (this$article == null ? other$article != null : !this$article.equals(other$article)) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        final Object this$writingDate = this.getWritingDate();
        final Object other$writingDate = other.getWritingDate();
        if (this$writingDate == null ? other$writingDate != null : !this$writingDate.equals(other$writingDate))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NewsInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $article = this.getArticle();
        result = result * PRIME + ($article == null ? 43 : $article.hashCode());
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        final Object $writingDate = this.getWritingDate();
        result = result * PRIME + ($writingDate == null ? 43 : $writingDate.hashCode());
        return result;
    }

    public String toString() {
        return "NewsInfo(id=" + this.getId() + ", article=" + this.getArticle() + ", text=" + this.getText() + ", writingDate=" + this.getWritingDate() + ")";
    }
}
