package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.service.ProjectService;
import lt.mindaugas.ProtoApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/project")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ProjectService projectService;

    // Methods to add a new question:

    @GetMapping(path = "/{projectId}/questions/new")
    public String getNewQuestion(@PathVariable("projectId") int projectId, Model model) {
        Question question = new Question();
        question.setProjectId(projectId);
        model.addAttribute("attrQuestion", question);
        return "project/question_new";
    }

    @PostMapping(path = "/{projectId}/questions/new")
    private String postNewQuestion(@PathVariable("projectId") int projectId,
                                   @ModelAttribute("attrQuestion") Question question) {
        question.setProjectId(projectId);
        question.setStatus((byte) 1);
        questionService.saveQuestion(question);
        return "redirect:/project/" + projectId + "/questions";
    }

    // Methods to get questions by projectID:

    @GetMapping(path = "/{projectId}/questions")
    public String getQuestionsByProjectId(@PathVariable("projectId") int projectId,
                                          @RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                          Model model) {
        Page<Question> questionsPage = questionService.getQuestionsByProjectId(projectId, page, pageSize);
        String name = projectService.getProjectShortName(projectId);
        model.addAttribute("attrProjectShortName", name);
        model.addAttribute("attrQuestions", questionsPage.getContent());
        model.addAttribute("currentPage", questionsPage.getNumber());
        model.addAttribute("totalPages", questionsPage.getTotalPages());
        model.addAttribute("projectId", projectId);
        return "project/questions";
    }


}
