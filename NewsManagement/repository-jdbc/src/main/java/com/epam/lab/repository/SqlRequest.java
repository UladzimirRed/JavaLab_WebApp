package com.epam.lab.repository;

public class SqlRequest {
    public static final String SQL_FIND_AUTHOR_BY_ID = "select * from authors where id = ?";
    public static final String SQL_FIND_ALL_AUTHORS = "select * from authors";
    public static final String SQL_UPDATE_AUTHOR = "update authors set author_name = ?, author_surname = ? where id = ?";
    public static final String SQL_DELETE_AUTHOR = "delete from authors where id = ?";
    public static final String SQL_INSERT_AUTHOR = "insert into authors(author_name, author_surname) values (?, ?)";

    public static final String SQL_FIND_NEWS_BY_ID = "select * from news where id = ?";
    public static final String SQL_FIND_ALL_NEWS = "select * from news";
    public static final String SQL_UPDATE_NEWS = "update news set title = ?, short_text = ?, full_text = ?, modification_date = ? where id = ?";
    public static final String SQL_DELETE_NEWS = "delete from news where id = ?";
    public static final String SQL_INSERT_NEWS = "insert into news(title, short_text, full_text, creation_date, modification_date) " +
            "values (?, ?, ?, ?, ?)";
}
