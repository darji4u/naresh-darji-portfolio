package com.naresh.darji.portfolio.portfolio.Controllers;

import com.naresh.darji.portfolio.portfolio.Configuration.JWTService;
import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import com.naresh.darji.portfolio.portfolio.Models.UserModel;
import com.naresh.darji.portfolio.portfolio.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = {"http://localhost:3000"})
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @RequestMapping
    public void login(OAuth2AuthenticationToken authentication, HttpServletResponse response) throws IOException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        Map<String,Object> userData = oauth2User.getAttributes();
        String token = userService.insertUserData(oauth2User);
        System.out.println(oauth2User.toString());
        String redirectUri = "https://naresh-darji-portfolio.netlify.app/authorized?authID="+token;
        response.sendRedirect(redirectUri);
    }

    @RequestMapping(value = "/currentUser",method = RequestMethod.GET)
    public CustomResponse<UserModel> getCurrentUser(HttpServletRequest request){
        String auth = request.getHeader("Authorization");
        System.out.println(auth);
        return userService.getUserByUsername(auth.substring(7));
    }

    @RequestMapping("/isValidToken")
    public String isValid(@RequestParam("authToken") String token){

//        return jwtService.isTokenValid(token,)
        return "";
    }

}
