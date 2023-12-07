package com.naresh.darji.portfolio.portfolio.Service;

import com.naresh.darji.portfolio.portfolio.Dao.BlogDao;
import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogService {

    @Autowired
    BlogDao blogDao;

    public CustomResponse<String> addBlog(MultipartFile file, Map<String, Object> resultMap) {
        CustomResponse<String> response = new CustomResponse<>();
        try {

            blogDao.insertBlog(file, resultMap);
            response.setMessage("Blog Submitted Successfully");
            response.setStatus("OK");

        } catch (Exception e) {
            response.setStatus("Failed");
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public CustomResponse<Map<String, Object>> getAllBlogs(int offset, int limit) {
        CustomResponse<Map<String, Object>> response = new CustomResponse<>();

        try {
            Map<String, Object> blogData = blogDao.selectBlogs(offset, limit);

            response.setMessage("Success");
            response.setStatus("OK");
            response.setData(blogData);
        } catch (Exception e) {
            response.setStatus("Failed");
            response.setMessage(e.getMessage());
        }
        return response;
    }
}
