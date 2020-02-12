package com.epam.lab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Tag extends AbstractEntity {
    private Long tagId;
    private String tagName;

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    @Id
    @GeneratedValue
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Column(nullable = false)
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (tagId != tag.tagId) return false;
        return tagName != null ? tagName.equals(tag.tagName) : tag.tagName == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (tagId ^ (tagId >>> 32));
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("tag id=").append(tagId);
        sb.append(", tag name='").append(tagName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
