package lt.mindaugas.ProtoApp.service;

import lt.mindaugas.ProtoApp.entity.Question;
import lt.mindaugas.ProtoApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) {
        int projectId = question.getProjectId();
        int nextQuestionNumber = getNextQuestionNumber(projectId);
        question.setQuestionNumber(nextQuestionNumber);
        return questionRepository.save(question);
    }

    private int getNextQuestionNumber(int projectId) {
        Question lastQuestion = questionRepository.findLastQuestionNumberByProjectId(projectId);
        if (lastQuestion == null) {
            return 1;
        } else {
            return lastQuestion.getQuestionNumber() + 1;
        }
    }

    public Page<Question> getQuestionsByProjectId(int projectId, int page, int pageSize) {
        return questionRepository.findQuestionByProjectId(projectId, PageRequest.of(page, pageSize));
    }

    public Question getQuestionById(int questionId) {
        return questionRepository.findQuestionById(questionId);
    }

//    public void resolveQuestion(int questionId) {
//        Question question = questionRepository.findById(questionId).get();
//        question.setStatus((byte) 0);
//        questionRepository.save(question);
//    }

    public void updateQuestionCommentAndStatus(Integer questionId, String comment) {
        Question question = questionRepository.findQuestionById(questionId);
        question.setComment(comment);
        question.setStatus((byte) 0);
        questionRepository.save(question);
    }
}
