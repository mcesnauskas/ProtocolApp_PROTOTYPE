package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.entity.QuestionResponse;
import lt.mindaugas.ProtoApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/project")
public class QuestionsController {
    @Autowired
    private QuestionService questionService;


}
