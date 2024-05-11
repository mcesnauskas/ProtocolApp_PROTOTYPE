package lt.mindaugas.ProtoApp.repository;

import lt.mindaugas.ProtoApp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    Page<Question> findByProjectId(int projectId, Pageable pageable);

    Question findTopByProjectIdOrderByQuestionNumberDesc(int projectId);

}
