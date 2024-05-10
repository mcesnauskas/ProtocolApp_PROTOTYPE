package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/project")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

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
        return "redirect:/project/" + projectId + "/questions/new";
    }
}
