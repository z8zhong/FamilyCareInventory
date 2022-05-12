package com.example.FamilyCareInventory.dao;

import com.example.FamilyCareInventory.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;


@Repository
public class UserJdbcTemp {
    private static final String SELECT_USER_BY_ID_SQL =
            "SELECT * FROM User WHERE userId = ?";

    private static final String VALIDATE_PASSWORD_USERNAME_SQL =
            "SELECT pwd = ? AS `valid?` FROM `User` WHERE user_name = ?";

    private static final String SELECT_ROLE_BY_USERNAME_SQL =
            "SELECT role FROM User WHERE user_name = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserJdbcTemp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getInt("userId"), rs.getString("user_name"),
                rs.getString("role"), rs.getString("pwd"));
        return user;
    }

    public User getUser(int userId) {
        User user = jdbcTemplate.queryForObject(SELECT_USER_BY_ID_SQL, this::mapRowToUser, userId);
        return user;
    }

    public Boolean validateUserByUsername(String username, String password) {
        Boolean validUser = false;
        SqlRowSet rs = jdbcTemplate.queryForRowSet(VALIDATE_PASSWORD_USERNAME_SQL, password, username);
        if (rs.next()) {
            if (rs.getBoolean("valid?")) {
                validUser = rs.getBoolean("valid?");
                ;
            } else {
                return false;
            }
        }
        return validUser;
    }

    public String getRoleByUsername(String username) {
        String role = "";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SELECT_ROLE_BY_USERNAME_SQL, username);
        if (rs.next()) {
            role = rs.getString("role");
        }
        return role;
    }
}