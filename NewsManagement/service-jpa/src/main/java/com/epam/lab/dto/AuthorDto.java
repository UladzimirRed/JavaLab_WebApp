package com.epam.lab.dto;

public class AuthorDto extends AbstractDto {
    private String authorName;
    private String authorSurname;

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

        if (!authorName.equals(authorDto.authorName)) return false;
        return authorSurname.equals(authorDto.authorSurname);
    }

    @Override
    public int hashCode() {
        int result = authorName.hashCode();
        result = 31 * result + authorSurname.hashCode();
        return result;
    }
}
