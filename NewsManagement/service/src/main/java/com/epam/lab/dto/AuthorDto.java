package com.epam.lab.dto;

public class AuthorDto extends AbstractDto{
    private Long authorId;
    private String authorName;
    private String authorSurname;

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorDto authorDto = (AuthorDto) o;

        if (authorId != null ? !authorId.equals(authorDto.authorId) : authorDto.authorId != null) return false;
        if (authorName != null ? !authorName.equals(authorDto.authorName) : authorDto.authorName != null) return false;
        return authorSurname != null ? authorSurname.equals(authorDto.authorSurname) : authorDto.authorSurname == null;
    }

    @Override
    public int hashCode() {
        int result = authorId != null ? authorId.hashCode() : 0;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (authorSurname != null ? authorSurname.hashCode() : 0);
        return result;
    }
}
