package com.ll.exam.sbb.question;

import com.ll.exam.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 붙은것에 대해 생성자가 만들어짐.
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(int id) throws DataNotFoundException {
        Optional<Question> oq = questionRepository.findById(id);

        if (oq.isPresent()) {
            return oq.get();
        }

        throw new DataNotFoundException("question not found");
    }

}
