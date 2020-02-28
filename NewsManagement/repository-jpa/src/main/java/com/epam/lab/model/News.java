package com.epam.lab.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "news")
public class News extends AbstractEntity {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "short_text", nullable = false)
    private String shortText;
    @Column(name = "full_text", nullable = false)
    private String fullText;
    @Column(name = "creation_date", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp creationDate;
    @Column(name = "modification_date", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modificationDate;
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "news_tag",
            joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private Set<Tag> tags;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "news_author",
            joinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private Set<Author> authors;

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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
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
        if (modificationDate != null ? !modificationDate.equals(news.modificationDate) : news.modificationDate != null)
            return false;
        if (tags != null ? !tags.equals(news.tags) : news.tags != null) return false;
        return authors != null ? authors.equals(news.authors) : news.authors == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (shortText != null ? shortText.hashCode() : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("title='").append(title).append('\'');
        sb.append(", shortText='").append(shortText).append('\'');
        sb.append(", fullText='").append(fullText).append('\'');
        sb.append(", creationDate=").append(creationDate);
        sb.append(", modificationDate=").append(modificationDate);
        sb.append(", tags=").append(tags);
        sb.append(", authors=").append(authors);
        sb.append('}');
        return sb.toString();
    }
}
