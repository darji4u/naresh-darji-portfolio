package com.naresh.darji.portfolio.portfolio.Service;

import com.naresh.darji.portfolio.portfolio.Dao.ProjectDao;
import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ProjectService {


    @Autowired
    ProjectDao projectDao;

    public void getProjects(){

    }

    public CustomResponse<String> addProject(MultipartFile[] files, Map<String, Object> data) {
        try{
            projectDao.insertProject(files,data);
            return new CustomResponse<String>("OK","Success",null);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new CustomResponse<>("Failes","Something went wrong",null);
        }
    }

    public CustomResponse<List<Map<String, Object>>> getAllProjects() {
        CustomResponse<List<Map<String,Object>>> response = new CustomResponse<>();
        try{
            List<Map<String,Object>> projectData = projectDao.selectAllProjects();
            for(Map<String,Object> proData : projectData){
                String[] technologyList = String.valueOf(proData.get("technology")).split(",");
                proData.put("technology",technologyList);
            }
            response.setStatus("OK");
            response.setMessage("Success");
            response.setData(projectData);
        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setStatus("Failed");
            response.setData(null);
            response.setMessage("Something went wrong");
        }
        return response;
    }
}
