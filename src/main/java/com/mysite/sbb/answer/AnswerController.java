package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
    private final QuestionService qs;
    private final AnswerService as;
    private final UserService us;

    // create으로 들어오는 HTTP post 메소드를 처리 하는 메소드 이다
    // MVC 런타임에서 메소드의 이름은 상관이 없고 어노테이션만 중요하다
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm,
            BindingResult bindingResult, Principal currentLogedInUser) {
        // 댓글이 등록된 게시글을 받아온다
        Question question = this.qs.getQuestion(id);
        SiteUser siteUser = this.us.getUser(currentLogedInUser.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        this.as.create(question, answerForm.getContent(), siteUser);
        // 반환값은 새로고침하는 기능을한다
        return String.format("redirect:/question/detail/%s", id);
    }

    // 댓글 수정 Get 메소드
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal currentLoggedInUser) {
        Answer answer = this.as.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(currentLoggedInUser.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
        }
        answerForm.setContent(answer.getContent());
        return "answer_form";
    }

    // 댓글 수정 Post 메소드
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm,
            BindingResult bindingResult, @PathVariable("id") Integer id, Principal currentLoggedInUser) {
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }
        Answer answer = this.as.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(currentLoggedInUser.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
        }
        this.as.modify(answer, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(@PathVariable("id") Integer id, Principal currentLoggedInUser) {
        Answer answer = this.as.getAnswer(id);
        if (!answer.getAuthor().getUsername().equals(currentLoggedInUser.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다");
        }
        this.as.delete(answer);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String answerVote(@PathVariable("id") Integer id, Principal currentLoggedInUser) {
        Answer answer = this.as.getAnswer(id);
        SiteUser siteUser = this.us.getUser(currentLoggedInUser.getName());
        this.as.vote(answer, siteUser);
        return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
    }
}
