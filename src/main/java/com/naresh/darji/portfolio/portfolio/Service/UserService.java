package com.naresh.darji.portfolio.portfolio.Service;

import com.naresh.darji.portfolio.portfolio.Configuration.JWTService;
import com.naresh.darji.portfolio.portfolio.Dao.UserDao;
import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import com.naresh.darji.portfolio.portfolio.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {


    @Autowired
    JWTService jwtService;

    @Autowired
    UserDao userDao;

    public String insertUserData(OAuth2User auth){

        CustomResponse<String> response = new CustomResponse<>();
        try{
            Map<String,Object> userData = auth.getAttributes();
            userDao.insertUserData(userData);
            String token = jwtService.generateToken(String.valueOf(userData.get("email")));
            return token;
        }catch (Exception e){
             return "";
        }
    }


    public CustomResponse<UserModel> getUserByUsername(String token) {

        CustomResponse<UserModel> response = new CustomResponse<>();

        try{

            String email = jwtService.extractUsername(token);
            UserModel userModel = userDao.selectUserData(email);
            response.setStatus("Success");
            response.setMessage("Authorized");
            response.setData(userModel);
            return response;
        }catch (Exception e){
            response.setStatus("OK");
            response.setMessage("Failed");
            return response;
        }
    }
}
