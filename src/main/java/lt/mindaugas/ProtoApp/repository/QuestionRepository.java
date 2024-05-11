package lt.mindaugas.ProtoApp.repository;

import lt.mindaugas.ProtoApp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query(value = "SELECT * FROM question WHERE project_id = ?1 AND status = 1",
            countQuery = "SELECT count(*) FROM question WHERE project_id = ?1 AND status = 1",
            nativeQuery = true)
    Page<Question> findQuestionByProjectId(int projectId, Pageable pageable);

    @Query(value = "SELECT * FROM question WHERE project_id = ?1 ORDER BY question_number DESC LIMIT 1",
            nativeQuery = true)
    Question findLastQuestionNumberByProjectId(int projectId);

}
