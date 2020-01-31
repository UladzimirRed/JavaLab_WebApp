package com.epam.lab.model;

import java.sql.Timestamp;

public class News extends Entity{
    private long newsId;
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp creationDate;
    private Timestamp modificationDate;

    public News() {
    }

    public News(String title, String shortText, String fullText, Timestamp creationDate, Timestamp modificationDate) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public News(String title, String shortText, String fullText) {
        this.title = title;
        this.shortText = shortText;
        this.fullText = fullText;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortText() {
        return shortText;
    }

    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    public String getFullText() {
        return fullText;
    }

    public void setFullText(String fullText) {
        this.fullText = fullText;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

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

        if (newsId != news.newsId) return false;
        if (title != null ? !title.equals(news.title) : news.title != null) return false;
        if (shortText != null ? !shortText.equals(news.shortText) : news.shortText != null) return false;
        if (fullText != null ? !fullText.equals(news.fullText) : news.fullText != null) return false;
        if (creationDate != null ? !creationDate.equals(news.creationDate) : news.creationDate != null) return false;
        return modificationDate != null ? modificationDate.equals(news.modificationDate) : news.modificationDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (newsId ^ (newsId >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortText != null ? shortText.hashCode() : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("newsId=").append(newsId);
        sb.append(", \ntitle='").append(title).append('\'');
        sb.append(", \nshortText='").append(shortText).append('\'');
        sb.append(", \nfullText='").append(fullText).append('\'');
        sb.append(", \ncreationDate=").append(creationDate);
        sb.append(", \nmodificationDate=").append(modificationDate);
        sb.append('}');
        return sb.toString();
    }
}
