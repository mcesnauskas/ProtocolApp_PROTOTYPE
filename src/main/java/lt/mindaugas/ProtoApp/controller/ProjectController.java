package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.entity.ProjectResponse;
import lt.mindaugas.ProtoApp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(path = "/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "/new")
    private String getNewProject(Model model) {
        model.addAttribute("attrProject", new Project());
        return "project/project";
    }

    @PostMapping(path = "/new")
    private String postNewProject(
            @ModelAttribute("attrProject") Project project,
            @RequestParam(value = "action", required = true) String action
    ) {
        projectService.saveProject(project);
        return "redirect:/project/all";
    }

    @PostMapping(path = "/{id:\\d+}/questions")
    private String postProject(
            @ModelAttribute("attrQuestion") Project project
    ) {
        project.setProjectId(1);
        return "redirect:/project/%s/questions".formatted(project.getProjectId());
    }

    @GetMapping(path = "/all")
    private String getAllProjects(
            @RequestParam(required = false) Map<String, String> requestParam,
            Model model
    ){
        ProjectResponse response = (ProjectResponse) projectService.getAllProjects(requestParam).getBody();
        model.addAttribute("attrProjects", response);
        model.addAttribute("currentPage", response.getPage());
        model.addAttribute("totalPages", response.getTotalPage());
        return "project/projects_all";
    }
}
