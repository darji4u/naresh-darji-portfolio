package com.naresh.darji.portfolio.portfolio.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import com.naresh.darji.portfolio.portfolio.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blogs")
//@CrossOrigin(origins = {"https://naresh-darji-portfolio.netlify.app/"})
@CrossOrigin(origins = {"http://localhost:3000"})
public class BlogController {



    @Autowired
    BlogService blogService;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/addBlog",method = RequestMethod.POST)
    public CustomResponse<String> addBlog(@RequestParam("file") MultipartFile file,@RequestParam String data) throws JsonProcessingException {
        Map<String, Object> resultMap = objectMapper.readValue(data, Map.class);
        return blogService.addBlog(file,resultMap);
    }

    @RequestMapping(value = "/getBlogs",method = RequestMethod.GET)
    public CustomResponse<Map<String,Object>> getBlogs(@RequestParam("pageNo") int pageNo, @RequestParam("limit") int limit){
        return blogService.getAllBlogs((limit*(pageNo-1)),limit);
    }

}
