package com.naresh.darji.portfolio.portfolio.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Autowired
    MediaDao mediaDao;

    public void selectProjects(){

        String query;

    }

    public void insertProject(MultipartFile[] files, Map<String, Object> data) throws IOException {

        String insertQuery = "INSERT INTO portfolio_projects(PROJECT_NAME,PROJECT_DESCRIPTION,PROJECT_CATEGORY,PROJECT_LINK,PROJECT_TECHNOLOGY,DATE_TIME) VALUES(?,?,?,?,?,NOW())";
        jdbcTemplate.update(
                insertQuery,
                String.valueOf(data.get("projectName")),
                String.valueOf(data.get("projectDescription")),
                String.valueOf(data.get("projectCategory")),
                String.valueOf(data.get("projectLink")),
                String.valueOf(data.get("technology"))
        );

        String queryGetId = "SELECT MAX(PROJECT_ID) FROM portfolio_projects WHERE PROJECT_NAME = ?";
        Integer projectId = jdbcTemplate.queryForObject(queryGetId, Integer.class, String.valueOf(data.get("projectName")));

        mediaDao.addMedia(projectId,"PROJECT",files[0]);

    }

    public List<Map<String, Object>> selectAllProjects() {

         String queryForProjects = "SELECT " +
                "pp.project_name as projectName," +
                "pp.project_description as projectDescription," +
                "pp.project_link as projectLink," +
                "pp.project_category as projectCategory,"+
                "pp.project_technology as technology," +
                "pm.media_type as mediaType," +
                "pm.media_data as design " +
                "from portfolio_projects as pp " +
                "JOIN portfolio_media as pm " +
                "ON pp.project_id = pm.media_belongs_id " +
                "WHERE pm.media_belongs_to = 'PROJECT'";

        return jdbcTemplate.queryForList(queryForProjects);

    }
}
