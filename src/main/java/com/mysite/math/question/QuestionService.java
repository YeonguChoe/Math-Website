package com.mysite.math.question;

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
import com.mysite.math.DataNotFoundException;
import com.mysite.math.user.SiteUser;

import com.mysite.math.answer.Answer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository qr;

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"),
                        cb.like(q.get("content"), "%" + kw + "%"),
                        cb.like(a.get("content"), "%" + kw + "%"),
                        cb.like(u2.get("username"), "%" + kw + "%"));
            };
        };
    }

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
    public Page<Question> getList(int page, String keyword) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(keyword);
        return this.qr.findAll(spec, pageable);
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
