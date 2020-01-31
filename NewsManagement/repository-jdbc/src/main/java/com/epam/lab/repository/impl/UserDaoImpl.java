package com.epam.lab.repository.impl;

import com.epam.lab.model.User;
import com.epam.lab.mapper.UserMapper;
import com.epam.lab.repository.SqlRequest;
import com.epam.lab.repository.UserDao;
import com.epam.lab.util.EncryptPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(DataSource dataSource){
        jdbcTemplate= new JdbcTemplate(dataSource);
    }

    @Override
    public User getEntityById(long id) {
        return jdbcTemplate.queryForObject(SqlRequest.SQL_FIND_USER_BY_ID, new Object[] {id}, new UserMapper());
    }

    @Override
    public List<User> getAllEntities() {
        return jdbcTemplate.query(SqlRequest.SQL_FIND_ALL_USERS, new UserMapper());
    }

    @Override
    public boolean deleteEntity(User user) {
        return jdbcTemplate.update(SqlRequest.SQL_DELETE_USER, user.getUserId()) > 0;
    }

    @Override
    public boolean updateEntity(User user) {
        return jdbcTemplate.update(SqlRequest.SQL_UPDATE_USER,
                user.getUserName(), user.getUserSurname(), user.getLogin(), user.getPassword()) > 0;
    }

    @Override
    public boolean createEntity(User user) {
        return jdbcTemplate.update(SqlRequest.SQL_INSERT_USER,
                user.getUserName(), user.getUserSurname(), user.getLogin(), EncryptPassword.encrypt(user.getPassword())) > 0;
    }
}
