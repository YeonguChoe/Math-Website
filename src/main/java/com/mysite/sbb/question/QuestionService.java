package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository qr;

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.qr.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("I couldn't find the question");
        }
    }

    public void create(String subject, String content, SiteUser author) {
        // 새로운 게시글 만들기
        Question question = new Question();
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        question.setAuthor(author);
        // 만든 후 저장
        this.qr.save(question);
    }

    // 페이지 만드는 방법
    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.qr.findAll(pageable);
    }

    // 게시물 수정
    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.qr.save(question);
    }

    // 게시물 삭제
    public void delete(Question question) {
        this.qr.delete(question);
    }

    // 투표
    public void vote(Question question, SiteUser siteUser) {
        // 추천인 추가
        question.getVoter().add(siteUser);
        this.qr.save(question);
    }
}
