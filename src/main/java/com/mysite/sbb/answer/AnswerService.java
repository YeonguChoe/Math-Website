package com.mysite.sbb.answer;

import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

import java.util.Optional;
import com.mysite.sbb.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository ar;

    public void create(Question question, String content, SiteUser siteuser) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(siteuser);
        this.ar.save(answer);
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.ar.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("댓글을 찾을수 없습니다");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.ar.save(answer);
    }

    public void delete(Answer answer) {
        this.ar.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.ar.save(answer);
    }
}
