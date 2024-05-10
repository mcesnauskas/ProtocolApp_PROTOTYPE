package lt.mindaugas.ProtoApp.service;

import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    private final int defaultPage = 0;
    private final int defaultPageSize = 10;
    public static final String PARAM_PAGE = "page";
    public static final String PARAM_PAGE_SIZE = "page_size";

    @Autowired
    private QuestionRepository questionRepository;

    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    public Page<Question> getQuestionsByProjectId(int projectId, int page, int pageSize) {
        return questionRepository.findByProjectId(projectId, PageRequest.of(page, pageSize));
    }

//    private ResponseEntity<?> fetchQuestionsByIdResponse(Pageable page) {
//        Page<Question> pageResponse = questionRepository.fetchAllQuestionsById(page);
//        return ResponseEntity.ok(
//                new QuestionResponse(
//                        pageResponse.getPageable().getPageNumber(),
//                        pageResponse.getPageable().getPageSize(),
//                        pageResponse.getTotalPages(),
//                        pageResponse.get().toList()
//                )
//        );
//    }
//
//    private int toNumberOrDefault(String value, int defaultValue) {
//        if (value == null) return defaultValue;
//        if (value.isBlank()) return defaultValue;
//        return Integer.parseInt(value);
//    }

}
