package lt.mindaugas.ProtoApp.repository;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

//    @Query(
//            value = "SELECT * FROM question WHERE project_id = :id",
//            nativeQuery = true
//    )
//    Iterable<Question> fetchProjectQuestionsByProjectId(int id);
}
