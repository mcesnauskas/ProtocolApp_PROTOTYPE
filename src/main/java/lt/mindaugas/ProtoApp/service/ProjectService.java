package lt.mindaugas.ProtoApp.service;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.entity.ProjectResponse;
import lt.mindaugas.ProtoApp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
    private final int defaultPage = 0;
    private final int defaultPageSize = 10;
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PAGE_SIZE = "page_size";

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public ResponseEntity<?> getAllProjects(Map<String, String> requestParam) {
        return fetchAllProjectsResponse(
                PageRequest.of(
                        toNumberOrDefault(requestParam.get(PARAM_PAGE), defaultPage),
                        toNumberOrDefault(requestParam.get(PARAM_PAGE_SIZE), defaultPageSize)
                )
        );
    }

    public String getProjectShortName(int projectId) {
        return projectRepository.fetchShortNameById(projectId);
    }

    private ResponseEntity<?> fetchAllProjectsResponse(Pageable page) {
        Page<Project> pageResponse = projectRepository.fetchAllProjects(page);
        return ResponseEntity.ok(
                new ProjectResponse(
                        pageResponse.getPageable().getPageNumber(),
                        pageResponse.getPageable().getPageSize(),
                        pageResponse.getTotalPages(),
                        pageResponse.get().toList()
                )
        );
    }

    private int toNumberOrDefault(String value, int defaultValue) {
        if (value == null) return defaultValue;
        if (value.isBlank()) return defaultValue;
        return Integer.parseInt(value);
    }
}
