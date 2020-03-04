package com.epam.lab.dto;

import com.epam.lab.model.Author;
import com.epam.lab.model.Tag;

import java.sql.Timestamp;
import java.util.Set;

public class NewsDto extends AbstractDto {
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private Set<Tag> tags;
    private Set<Author> author;

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

    public Set<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Set<Author> author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDto newsDto = (NewsDto) o;

        if (!title.equals(newsDto.title)) return false;
        if (!shortText.equals(newsDto.shortText)) return false;
        if (!fullText.equals(newsDto.fullText)) return false;
        if (!creationDate.equals(newsDto.creationDate)) return false;
        if (!modificationDate.equals(newsDto.modificationDate)) return false;
        if (!tags.equals(newsDto.tags)) return false;
        return author.equals(newsDto.author);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + shortText.hashCode();
        result = 31 * result + fullText.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + modificationDate.hashCode();
        result = 31 * result + tags.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
