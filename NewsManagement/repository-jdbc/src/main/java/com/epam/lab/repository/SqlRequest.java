package com.epam.lab.repository;

public class SqlRequest {
    public static final String SQL_FIND_AUTHOR = "select * from nms.author where id = ?";
    public static final String SQL_FIND_ALL_AUTHORS = "select * from nms.author";
}
