package com.epam.lab.model;

public class Tag extends AbstractEntity {
    private String tagName;

    public Tag() {
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }


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

        return tagName.equals(tag.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("tag id=").append(super.getId());
        sb.append(", tag name='").append(tagName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
