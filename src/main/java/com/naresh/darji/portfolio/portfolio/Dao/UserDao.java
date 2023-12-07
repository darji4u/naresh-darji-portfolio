package com.naresh.darji.portfolio.portfolio.Dao;

import com.naresh.darji.portfolio.portfolio.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {


    @Autowired
    JdbcTemplate jdbcTemplate;



    public void insertUserData(Map<String, Object> userData) {

        String existQuery = "Select COUNT(*) FROM portfolio_users WHERE USER_EMAIL = ?";
        Integer exist = jdbcTemplate.queryForObject(existQuery,Integer.class,userData.get("email"));
        if(exist==0){
            String query = "INSERT INTO portfolio_users(USER_EMAIL,USER_NAME,USER_PROFILE) value(?,?,?)";
            jdbcTemplate.update(query,userData.get("email"),userData.get("name"),userData.get("picture"));
        }else{
            String query = "UPDATE portfolio_users SET USER_NAME = ?,USER_PROFILE = ? WHERE USER_EMAIL = ?";
            jdbcTemplate.update(query,userData.get("name"),userData.get("picture"),userData.get("email"));
        }
    }

    public UserModel selectUserData(String email) {

        try{
            String query = "SELECT ID as userID, USER_EMAIL as userEmail, USER_NAME as userName, USER_PROFILE as userProfile FROM portfolio_users WHERE USER_EMAIL = ?";
            return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(UserModel.class), email);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;

    }
}
