package com.naresh.darji.portfolio.portfolio.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naresh.darji.portfolio.portfolio.Models.CustomResponse;
import com.naresh.darji.portfolio.portfolio.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/getProjects",method = RequestMethod.GET)
    public CustomResponse<List<Map<String,Object>>> getProjects(){
        return projectService.getAllProjects();
    }

//    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/addProject",method = RequestMethod.POST, consumes = { "multipart/form-data" })
    public CustomResponse<String> addProject(@RequestParam("files") MultipartFile[] files, @RequestParam String data) throws JsonProcessingException {


        Map<String, Object> resultMap = objectMapper.readValue(data, Map.class);
        return projectService.addProject(files,resultMap);

    }


}
