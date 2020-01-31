package com.epam.lab.model;

public class Author extends Entity{
    private long authorId;
    private String authorName;
    private String authorSurname;

    public Author() {
    }

    public Author(long authorId, String authorName, String authorSurname) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public Author(String authorName, String authorSurname) {
        this.authorName = authorName;
        this.authorSurname = authorSurname;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
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

        if (authorId != author.authorId) return false;
        if (authorName != null ? !authorName.equals(author.authorName) : author.authorName != null) return false;
        return authorSurname != null ? authorSurname.equals(author.authorSurname) : author.authorSurname == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (authorId ^ (authorId >>> 32));
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        result = 31 * result + (authorSurname != null ? authorSurname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Author{");
        sb.append("authorId=").append(authorId);
        sb.append(", authorName='").append(authorName).append('\'');
        sb.append(", authorSurname='").append(authorSurname).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
