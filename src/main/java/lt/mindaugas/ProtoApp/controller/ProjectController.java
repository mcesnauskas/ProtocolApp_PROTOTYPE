package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.repository.ProjectRepository;
import lt.mindaugas.ProtoApp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            @ModelAttribute("attrProject") Project project
    ) {
        project = projectService.saveProject(project);
        return "redirect:/project/%s/questions".formatted(project.getProjectId());
    }

    @GetMapping(path = "/{id:\\d+}/questions")
    private String getProject(Model model) {
        model.addAttribute("attrProject", new Project());
        return "project/questions";
    }

//    @{'/project/' + ${attrCustomer.customerId} + '/details'}

    //    @PostMapping(path = "/{id:\\d+}/details")
    @PostMapping(path = "/{id:\\d+}/questions")
    private String postProject(
            @ModelAttribute("attrProject") Project project
    ) {
        project.setProjectId(1);
        return "redirect:/project/%s/questions".formatted(project.getProjectId());
    }

}
