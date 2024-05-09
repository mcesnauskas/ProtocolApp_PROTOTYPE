package lt.mindaugas.ProtoApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/project")
public class ParticipantController {

    @GetMapping(path = "/participant")
    private String getParticipant(Model model){
        model.addAttribute("attrName", "Welcome to the participant page!");
        return "project/participant";
    }
}
