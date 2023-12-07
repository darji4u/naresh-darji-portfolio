package com.naresh.darji.portfolio.portfolio.Service;

import com.naresh.darji.portfolio.portfolio.Configuration.JWTService;
import com.naresh.darji.portfolio.portfolio.Dao.CommonDao;
import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import com.naresh.darji.portfolio.portfolio.Models.ReviewAndContactModel;
import com.naresh.darji.portfolio.portfolio.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @Autowired
    CommonDao commonDao;

    public CustomResponse<String> addReview(String authID,String review){

        CustomResponse<UserModel> userData = new CustomResponse<>();
        CustomResponse<String> response = new CustomResponse<>();
        try{
            userData = userService.getUserByUsername(authID.substring(7));
            boolean isInserted = commonDao.insertReview(userData.getData().getUserID(), review);

            if(isInserted){
                response.setStatus("OK");
                response.setMessage("Success");
                response.setData("Review Addess Successfully");
                return response;
            }

        }catch (Exception e){

        }

        response.setStatus("Failed");
        response.setMessage("Failed");
        response.setData("Review Not Added !");
        return response;
    }

    public CustomResponse<List<Map<String, Object>>> getAllReviews(int offset,int limit) {

        CustomResponse<List<Map<String,Object>>> response = new CustomResponse<>();

        try{

            List<Map<String,Object>> reviews = commonDao.selectReviews(offset,limit);
            response.setStatus("Success");
            response.setMessage("");
            response.setData(reviews);

        }catch (Exception e){
            response.setStatus("Failed");
            response.setMessage(e.getMessage());
            response.setData(null);
        }

        return response;
    }

    public CustomResponse<String> addReviewAndContact(String authID, ReviewAndContactModel reviewAndContactModel) {

        CustomResponse<String> response = new CustomResponse<>();
        try{
            if(reviewAndContactModel.getType().equalsIgnoreCase("review")||reviewAndContactModel.getType().equalsIgnoreCase("message")||reviewAndContactModel.getType().equalsIgnoreCase("hire")){
                UserModel userModel = userService.getUserByUsername(authID.substring(7)).getData();
                commonDao.insertReviewOrMessage(userModel.getUserID(),reviewAndContactModel);
                if(reviewAndContactModel.getType().equals("message")||reviewAndContactModel.getType().equals("hire")){
                    response.setMessage("Message Send Successfully");
                }else if(reviewAndContactModel.getType().equals("review")){
                    response.setMessage("Review Added Successfully");
                }
                response.setStatus("Success");
                return response;
            }else{
                response.setMessage("Invalid Request");
                response.setStatus("Failed");
                return response;
            }
        }catch (Exception e){
            response.setMessage(e.getMessage());
            response.setStatus("Failed");
            return response;
        }
    }

    public CustomResponse<Map<String, Object>> getPortfolioDefaultData() {

        CustomResponse<Map<String,Object>> response = new CustomResponse<>();

        try{
            return response;
        }catch (Exception e){
            return response;
        }
    }
}
