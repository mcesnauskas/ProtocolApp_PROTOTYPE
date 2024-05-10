package lt.mindaugas.ProtoApp.service;

import lt.mindaugas.ProtoApp.entity.Project;
import lt.mindaugas.ProtoApp.entity.ProjectResponse;
import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.entity.QuestionResponse;
import lt.mindaugas.ProtoApp.repository.ProjectRepository;
import lt.mindaugas.ProtoApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.CacheRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionService {
    private final int defaultPage = 0;
    private final int defaultPageSize = 10;
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PAGE_SIZE = "page_size";

    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
}
