package com.epam.lab.mapper;

import com.epam.lab.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setUserSurname(resultSet.getString("user_surname"));
        user.setLogin(resultSet.getString("login"));
        return user;
    }
}
