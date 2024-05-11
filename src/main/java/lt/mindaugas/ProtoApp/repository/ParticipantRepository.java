package lt.mindaugas.ProtoApp.repository;

import lt.mindaugas.ProtoApp.entity.Participant;
import lt.mindaugas.ProtoApp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ParticipantRepository extends CrudRepository<Participant, Integer> {

    @Query(value = "SELECT * FROM participant WHERE project_id = ?1",
            nativeQuery = true)
    Page<Participant> findParticipantByProjectId(int projectId, Pageable pageable);
}
