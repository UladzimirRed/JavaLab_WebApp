package com.epam.lab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@Entity
public class News extends AbstractEntity {
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp creationDate;
    private Timestamp modificationDate;

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    @Column(nullable = false)
    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    @Column(nullable = false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Column(nullable = false)
    public Timestamp getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Timestamp modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (shortText != null ? !shortText.equals(news.shortText) : news.shortText != null) return false;
        if (fullText != null ? !fullText.equals(news.fullText) : news.fullText != null) return false;
        if (creationDate != null ? !creationDate.equals(news.creationDate) : news.creationDate != null) return false;
        return modificationDate != null ? modificationDate.equals(news.modificationDate) : news.modificationDate == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (shortText != null ? shortText.hashCode() : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("news id=").append(super.getId());
        sb.append(", \ntitle='").append(title).append('\'');
        sb.append(", \nshortText='").append(shortText).append('\'');
        sb.append(", \nfullText='").append(fullText).append('\'');
        sb.append(", \ncreationDate=").append(creationDate);
        sb.append(", \nmodificationDate=").append(modificationDate);
        sb.append('}');
        return sb.toString();
    }
}
