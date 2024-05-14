package lt.mindaugas.ProtoApp.service;

import lt.mindaugas.ProtoApp.entity.Participant;
import lt.mindaugas.ProtoApp.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public Page<Participant> getParticipantsByProjectId(int projectId, int page, int pageSize) {
        return participantRepository.findParticipantByProjectId(projectId, PageRequest.of(page, pageSize));
    }

    public void saveParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    public Participant getParticipantById(int participantId) {
        return participantRepository.findParticipantById(participantId);
    }

    public void updateParticipant(Participant participant) {
        participantRepository.save(participant);
    }
}
