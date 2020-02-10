package com.epam.lab.dto;

import java.util.List;

public class NewsSearchCriteria {
    Long authorId;
    List<Long> tagsId;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public List<Long> getTagsId() {
        return tagsId;
    }

    public void setTagsId(List<Long> tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewsSearchCriteria that = (NewsSearchCriteria) o;

        if (authorId != null ? !authorId.equals(that.authorId) : that.authorId != null) return false;
        return tagsId != null ? tagsId.equals(that.tagsId) : that.tagsId == null;
    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (tagsId != null ? tagsId.hashCode() : 0);
        return result;
    }
}
