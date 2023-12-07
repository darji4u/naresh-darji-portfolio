package com.naresh.darji.portfolio.portfolio.Controllers;

import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import com.naresh.darji.portfolio.portfolio.Models.ReviewAndContactModel;
import com.naresh.darji.portfolio.portfolio.Service.CommonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/utility")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CommonController {

    @Autowired
    CommonService commonService;

    @RequestMapping(value = "/addReview",method = RequestMethod.POST)
    public CustomResponse<String> addReview(@RequestParam("review") String review, HttpServletRequest request){
        String authID = request.getHeader("Authorization");
        return commonService.addReview(authID,review);
    }

    @RequestMapping(value = "/postReviewOrContact",method = RequestMethod.POST)
    public CustomResponse<String> postReviewAndContact(@RequestBody ReviewAndContactModel reviewAndContactModel, HttpServletRequest request){
        String authID = request.getHeader("Authorization");
        return commonService.addReviewAndContact(authID,reviewAndContactModel);
    }

    @RequestMapping(value = "/getReviews",method = RequestMethod.GET)
    public CustomResponse<List<Map<String, Object>>> getReviews(@RequestParam("pageNo") int pageNo,@RequestParam("limit") int limit){

        return commonService.getAllReviews((limit*(pageNo-1)),limit);
    }


    @RequestMapping(value = "/getPortfolioDefaultData",method = RequestMethod.GET)
    public CustomResponse<Map<String,Object>> getPortfolioDefaultData(){
        return commonService.getPortfolioDefaultData();
    }



}
