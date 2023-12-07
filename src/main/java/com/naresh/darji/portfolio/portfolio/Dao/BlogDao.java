package com.naresh.darji.portfolio.portfolio.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BlogDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MediaDao mediaDao;

    public void insertBlog(MultipartFile file, Map<String, Object> resultMap) throws IOException {

        String insertBlog = "INSERT INTO portfolio_blogs(title,tags,subcontent,content) VALUES(?,?,?,?)";
        jdbcTemplate.update(insertBlog,resultMap.get("title"),resultMap.get("tags"),resultMap.get("subContent"),resultMap.get("content"));

        String queryGetId = "SELECT MAX(id) FROM portfolio_blogs WHERE TITLE = ?";

        int blogId = jdbcTemplate.queryForObject(queryGetId,Integer.class,resultMap.get("title"));
        mediaDao.addMedia(blogId,"BLOG",file);

    }

    public Map<String, Object> selectBlogs(int offset,int limit) {
        String querySelectBlogs = "SELECT pb.title,pb.tags,pb.subcontent,pb.content,pm.media_data as mediaData,pm.date_time as dateTime from portfolio_blogs as pb JOIN portfolio_media as pm on pb.id = pm.media_belongs_id WHERE pm.media_belongs_to = 'BLOG' limit ? offset ?";
        String queryTotalBlogsCount = "SELECT COUNT(*) from portfolio_blogs";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(querySelectBlogs,limit,offset);
        int tNOB = jdbcTemplate.queryForObject(queryTotalBlogsCount,Integer.class);

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("blogList",list);
        responseMap.put("totalBlogs",tNOB);
        return responseMap;
    }
}
