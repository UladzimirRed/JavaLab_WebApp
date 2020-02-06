package com.epam.lab.dto;

import com.epam.lab.model.Tag;

import java.sql.Timestamp;
import java.util.List;

public class NewsDto {
    private Long newsId;
    private String title;
    private String shortText;
    private String fullText;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private AuthorDto authorDto;
    private List<Tag> tags;

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
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

    public AuthorDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(AuthorDto authorDto) {
        this.authorDto = authorDto;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsDto newsDto = (NewsDto) o;

        if (newsId != null ? !newsId.equals(newsDto.newsId) : newsDto.newsId != null) return false;
        if (title != null ? !title.equals(newsDto.title) : newsDto.title != null) return false;
        if (shortText != null ? !shortText.equals(newsDto.shortText) : newsDto.shortText != null) return false;
        if (fullText != null ? !fullText.equals(newsDto.fullText) : newsDto.fullText != null) return false;
        if (creationDate != null ? !creationDate.equals(newsDto.creationDate) : newsDto.creationDate != null)
            return false;
        if (modificationDate != null ? !modificationDate.equals(newsDto.modificationDate) : newsDto.modificationDate != null)
            return false;
        if (authorDto != null ? !authorDto.equals(newsDto.authorDto) : newsDto.authorDto != null) return false;
        return tags != null ? tags.equals(newsDto.tags) : newsDto.tags == null;
    }

    @Override
    public int hashCode() {
        int result = newsId != null ? newsId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (shortText != null ? shortText.hashCode() : 0);
        result = 31 * result + (fullText != null ? fullText.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (modificationDate != null ? modificationDate.hashCode() : 0);
        result = 31 * result + (authorDto != null ? authorDto.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
