package com.naresh.darji.portfolio.portfolio.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Repository
public class MediaDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void addMedia(Integer id, String type, MultipartFile file) throws IOException {
        String queryInsertMedia = "INSERT INTO portfolio_media(MEDIA_TYPE,MEDIA_NAME,MEDIA_BELONGS_TO,MEDIA_BELONGS_ID,MEDIA_DATA,DATE_TIME) VALUES(?,?,?,?,?,NOW())";
        jdbcTemplate.update(queryInsertMedia,file.getContentType(),file.getName(),type,id,file.getBytes());
    }
}
