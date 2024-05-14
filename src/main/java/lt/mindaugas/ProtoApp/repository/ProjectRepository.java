package lt.mindaugas.ProtoApp.repository;

import lt.mindaugas.ProtoApp.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    @Query(value = "SELECT * FROM project",
            countQuery = "SELECT count(*) FROM project",
            nativeQuery = true)
    Page<Project> fetchAllProjects(Pageable pageable);

    @Query(value = "SELECT project_short_name FROM project WHERE project_id = ?1",
            nativeQuery = true)
    String fetchShortNameById(int projectId);
}
