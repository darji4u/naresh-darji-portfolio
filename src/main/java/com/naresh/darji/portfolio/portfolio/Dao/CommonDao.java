package com.naresh.darji.portfolio.portfolio.Dao;

import com.naresh.darji.portfolio.portfolio.Models.ReviewAndContactModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommonDao {


    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insertReview(int userID,String review){
        try{
            String query = "INSERT INTO portfolio_reviews(USER_ID,USER_REVIEW,DATE_TIME) VALUES(?,?,NOW())";
            jdbcTemplate.update(query,userID,review);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Map<String, Object>> selectReviews(int offset,int limit) {

        String query = "SELECT " +
                "r.id as reviewID, " +
                "r.company_name as companyName, " +
                "r.designation as userDesignation , " +
                "r.message , u.user_name as userName , " +
                "r.rating, " +
                "r.date_time as dateTime, " +
                "u.user_profile as userProfile " +
                "from portfolio_review_and_message as r " +
                "JOIN portfolio_users as u " +
                "ON r.user_id = u.id " +
                "where content_type = 'review' limit ? offset ?";
        return jdbcTemplate.queryForList(query,limit,offset);

    }

    public void insertReviewOrMessage(int userID, ReviewAndContactModel reviewAndContactModel) {

        String query = "INSERT INTO portfolio_review_and_message(content_type,company_name,designation,message,rating,user_id,date_time) VALUES(?,?,?,?,?,?,NOW())";

        jdbcTemplate.update(
                query,
                reviewAndContactModel.getType(),
                reviewAndContactModel.getCompanyName(),
                reviewAndContactModel.getDesignation(),
                reviewAndContactModel.getMessage(),
                reviewAndContactModel.getRating(),
                userID
                );
    }

}
