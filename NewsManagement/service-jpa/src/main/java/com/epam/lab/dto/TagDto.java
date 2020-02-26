package com.epam.lab.dto;

public class TagDto extends AbstractDto {
    private String tagName;

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

        TagDto tagDto = (TagDto) o;

        return tagName.equals(tagDto.tagName);
    }

    @Override
    public int hashCode() {
        return tagName.hashCode();
    }
}
