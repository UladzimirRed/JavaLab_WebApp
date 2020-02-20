package com.epam.lab.model;

import javax.persistence.*;

@Entity
@Table(name = "authors")
public class Author extends AbstractEntity {
    @Column (name = "author_name", nullable = false)
    private String authorName;
    @Column (name = "author_surname", nullable = false)
    private String authorSurname;

    public Author() {
    }

    public Author(String authorName, String authorSurname) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (!authorName.equals(author.authorName)) return false;
        return authorSurname.equals(author.authorSurname);
    }

    @Override
    public int hashCode() {
        int result = authorName.hashCode();
        result = 31 * result + authorSurname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("authorId=").append(super.getId());
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", authorSurname='").append(authorSurname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
