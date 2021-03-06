package com.epam.lab.repository;

public class SqlRequest {

    private SqlRequest() {
    }

    public static final String SQL_FIND_AUTHOR_BY_ID = "select * from authors where id = ?";
    public static final String SQL_FIND_ALL_AUTHORS = "select * from authors";
    public static final String SQL_UPDATE_AUTHOR = "update authors set author_name = ?, author_surname = ? where id = ?";
    public static final String SQL_DELETE_AUTHOR = "delete from authors where id = ?";
    public static final String SQL_INSERT_AUTHOR = "insert into authors(author_name, author_surname) values (?, ?)";

    public static final String SQL_FIND_NEWS_BY_ID = "select * from news where id = ?";
    public static final String SQL_FIND_ALL_NEWS = "select * from news";
    public static final String SQL_UPDATE_NEWS = "update news set title = ?, short_text = ?, full_text = ?, modification_date = ? where id = ?";
    public static final String SQL_UPDATE_TITLE = "update news set title = ?, modification_date = current_timestamp where id = ?";
    public static final String SQL_UPDATE_SHORT_TEXT = "update news set short_text = ?, modification_date = current_timestamp where id = ?";
    public static final String SQL_UPDATE_FULL_TEXT = "update news set full_text = ?, modification_date = current_timestamp where id = ?";
    public static final String SQL_DELETE_NEWS = "delete from news where id = ?";
    public static final String SQL_INSERT_NEWS = "insert into news(title, short_text, full_text, creation_date, modification_date) " +
            "values (?, ?, ?, current_timestamp, current_timestamp)";

    public static final String SQL_FIND_USER_BY_ID = "select * from users where id = ?";
    public static final String SQL_FIND_ALL_USERS = "select id, user_name, user_surname, login from users";
    public static final String SQL_UPDATE_USER = "update users set user_name = ?, user_surname = ?, login = ?, password = ? where id = ?";
    public static final String SQL_DELETE_USER = "delete from users where id = ?";
    public static final String SQL_INSERT_USER = "insert into users(user_name, user_surname, login, password) " +
            "values (?, ?, ?, ?)";

    public static final String SQL_FIND_TAG_BY_ID = "select * from tags where id = ?";
    public static final String SQL_FIND_ALL_TAGS = "select * from tags";
    public static final String SQL_UPDATE_TAG = "update tags set tag_name = ? where id = ?";
    public static final String SQL_DELETE_TAG = "delete from tags where id =?";
    public static final String SQL_INSERT_TAG = "insert into tags (tag_name) values (?)";

    public static final String SQL_FIND_AUTHOR_BY_NEWS_ID = "select id, author_name, author_surname " +
            "from authors join news_author on id = author_id where news_id = ?";
    public static final String SQL_FIND_TAGS_BY_NEWS_ID = "select id, tag_name " +
            "from tags join news_tag on tag_id = id where news_id = ?";

    public static final String SQL_LINK_AUTHOR_ID_WITH_NEWS_ID = "insert into news_author (author_id, news_id) values (?, ?)";
    public static final String SQL_LINK_TAGS_ID_WITH_NEWS_ID = "insert into news_tag (tag_id, news_id) values (?, ?)";
    public static final String SQL_UNLINK_AUTHOR_ID_WITH_NEWS_ID = "delete from news_author where news_id = ?";
    public static final String SQL_UNLINK_TAG_ID_WITH_NEWS_ID = "delete from news_tag where news_id = ?";


}
