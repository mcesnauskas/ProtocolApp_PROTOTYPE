package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.entity.ProjectResponse;
import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.repository.ProjectRepository;
import lt.mindaugas.ProtoApp.service.ProjectService;
import lt.mindaugas.ProtoApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;



//    NEW PROJECT

    @GetMapping(path = "/new")
    private String getNewProject(Model model) {
        model.addAttribute("attrProject", new Project());
        return "project/project";
    }

//    @PostMapping(path = "/new")
//    private String postNewProject(
//            @ModelAttribute("attrProject") Project project
//    ) {
//        project = projectService.saveProject(project);
//        return "redirect:/project/%s/questions/new".formatted(project.getProjectId());
//    }

    @PostMapping(path = "/new")
    private String postNewProject(
            @ModelAttribute("attrProject") Project project,
            @RequestParam(value = "action", required = true) String action
    ) {
        project = projectService.saveProject(project);
        if (action.equals("ADD PARTICIPANT")) {
            return "redirect:/project/all";
//            return "redirect:/project/" + project.getProjectId() + "/participants/new";
        } else {
            return "redirect:/project/all";
//            return "redirect:/project/" + project.getProjectId() + "/questions/new";
        }
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
//        model.addAttribute("attrSearchProject", new Project()); // Jei darysiu paieska
        return "project/projects_all";
    }
}
