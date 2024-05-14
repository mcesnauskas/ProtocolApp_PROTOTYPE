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

    public void saveQuestion(Question question) {
        int projectId = question.getProjectId();
        int nextQuestionNumber = getNextQuestionNumber(projectId);
        question.setQuestionNumber(nextQuestionNumber);
        questionRepository.save(question);
    }

    public Page<Question> getQuestionsByProjectId(int projectId, int page, int pageSize) {
        return questionRepository.findQuestionByProjectId(projectId, PageRequest.of(page, pageSize));
    }

    public Question getQuestionById(int questionId) {
        return questionRepository.findQuestionById(questionId);
    }

    public void updateQuestionComment(Integer questionId, String comment) {
        Question question = questionRepository.findQuestionById(questionId);
        question.setComment(comment);
        questionRepository.save(question);
    }

    public void updateQuestionStatus(Integer questionId, byte status) {
        Question question = questionRepository.findQuestionById(questionId);
        question.setStatus(status);
        questionRepository.save(question);
    }

    private int getNextQuestionNumber(int projectId) {
        Question lastQuestion = questionRepository.findLastQuestionNumberByProjectId(projectId);
        if (lastQuestion == null) {
            return 1;
        } else {
            return lastQuestion.getQuestionNumber() + 1;
        }
    }
}
