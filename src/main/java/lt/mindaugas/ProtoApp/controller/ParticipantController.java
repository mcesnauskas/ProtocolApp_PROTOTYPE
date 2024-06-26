package lt.mindaugas.ProtoApp.controller;

import lt.mindaugas.ProtoApp.entity.Participant;
import lt.mindaugas.ProtoApp.service.ParticipantService;
import lt.mindaugas.ProtoApp.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/project")
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private ProjectService projectService;

    @GetMapping(path = "/{projectId}/participants/new")
    public String getNewParticipant(@PathVariable("projectId") int projectId, Model model) {
        Participant participant = new Participant();
        participant.setProjectId(projectId);
        model.addAttribute("attrParticipant", participant);
        return "project/participant_new";
    }

    @PostMapping(path = "/{projectId}/participants/new")
    public String postNewParticipant(@PathVariable("projectId") int projectId,
                                     @ModelAttribute("attrParticipant") Participant participant,
                                     Model model) {
        participant.setProjectId(projectId);
        if (participant.getParticipantId() != null) {
            participantService.updateParticipant(participant);
        } else {
            participantService.saveParticipant(participant);
        }
        return "redirect:/project/" + projectId + "/questions";
    }

    @GetMapping(path = "/{projectId}/participants/{participantId}")
    public String getEditParticipant(@PathVariable("projectId") int projectId,
                                     @PathVariable("participantId") int participantId,
                                     Model model) {
        Participant participant = participantService.getParticipantById(participantId);
        model.addAttribute("attrParticipant", participant);
        return "project/participant_new";
    }

}
