package lt.mindaugas.ProtoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/project")
public class ProjectsController {

    @GetMapping(path = "/projects")
    private String getProjects(){
        return "project/projects";
    }
}
