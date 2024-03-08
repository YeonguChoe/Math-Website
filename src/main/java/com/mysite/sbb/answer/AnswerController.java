package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
