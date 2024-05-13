package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Participant;
import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.service.ParticipantService;
import lt.mindaugas.ProtoApp.service.ProjectService;
import lt.mindaugas.ProtoApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/project")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ParticipantService participantService;

    @GetMapping(path = "/{projectId}/questions/new")
    public String getNewQuestion(@PathVariable("projectId") int projectId, Model model) {
        Question question = new Question();
        question.setProjectId(projectId);
        model.addAttribute("attrQuestion", question);
        return "project/question_new";
    }

    @PostMapping(path = "/{projectId}/questions/new")
    public String addNewQuestion(@PathVariable("projectId") int projectId,
                                 @ModelAttribute("attrQuestion") Question question,
                                 Model model) {
        if (question.getQuestion().isEmpty()) {
            return "project/question_new";
        } else {
            question.setStatus((byte) 1);
            questionService.saveQuestion(question);
            return "redirect:/project/" + projectId + "/questions";
        }
    }

    @GetMapping(path = "/{projectId}/questions")
    public String getQuestionsByProjectId(@PathVariable("projectId") int projectId,
                                          @RequestParam(name = "page", defaultValue = "0") int page,
                                          @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                          Model model) {
        Page<Question> questionsPage = questionService.getQuestionsByProjectId(projectId, page, pageSize);
        Page<Participant> participantsPage = participantService.getParticipantsByProjectId(projectId, page, pageSize);
        String name = projectService.getProjectShortName(projectId);
        model.addAttribute("attrProjectShortName", name);
        model.addAttribute("attrQuestions", questionsPage.getContent());
        model.addAttribute("attrParticipants", participantsPage.getContent());
        model.addAttribute("currentPage", questionsPage.getNumber());
        model.addAttribute("totalPages", questionsPage.getTotalPages());
        model.addAttribute("projectId", projectId);
        return "project/questions";
    }

    @GetMapping(path = "/{projectId}/questions/{questionId}")
    public String getEditParticipant(@PathVariable("projectId") int projectId,
                                     @PathVariable("questionId") int questionId,
                                     Model model) {
        Question question = questionService.getQuestionById(questionId);
        model.addAttribute("attrQuestion", question);
        return "project/question_edit";
    }

    @PostMapping(path = "/{projectId}/questions/{questionId}/resolve")
    public String resolveQuestion(@PathVariable("projectId") int projectId,
                                  @PathVariable("questionId") int questionId) {
        questionService.resolveQuestion(questionId);
        return "redirect:/project/" + projectId + "/questions";
    }
}
