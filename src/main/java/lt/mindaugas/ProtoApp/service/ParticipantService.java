package lt.mindaugas.ProtoApp.service;

import lt.mindaugas.ProtoApp.entity.Participant;
import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    private final int defaultPage = 0;
    private final int defaultPageSize = 5;
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PAGE_SIZE = "page_size";

    @Autowired
    private ParticipantRepository participantRepository;

    public Page<Participant> getParticipantsByProjectId(int projectId, int page, int pageSize) {
        return participantRepository.findParticipantByProjectId(projectId, PageRequest.of(page, pageSize));
    }

    public void saveParticipant(Participant participant) {
        participantRepository.save(participant);
    }
}
