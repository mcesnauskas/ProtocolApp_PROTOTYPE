package lt.mindaugas.ProtoApp.service;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private final int defaultPage = 0;
    private final int defaultPageSize = 5;
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PAGE_SIZE = "page_size";

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }
}
