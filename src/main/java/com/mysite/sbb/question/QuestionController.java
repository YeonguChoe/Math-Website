package com.mysite.sbb.question;

import java.security.Principal;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

// RequestMapping에 경로를 적어주면, 아래의 GetMapping에서 나머지 주소만 적어주면 된다
@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

    // 서비스를 안두고 직접 의존성 주입으로 받아오는 방법
    // private final QuestionRepository qr;

    // 중간에 서비스를 두고 서비스로 부터 받아오는 방법
    private final QuestionService qs;
    private final UserService us;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        // 직접 받아 오는 방법
        // List<Question> questionListInput = qr.findAll();

        // 서비스로부터 받아오는 방법
        Page<Question> pg = this.qs.getList(page, kw);

        // pg변수를 Model 객체에 추가하여 question_list.html에 넘겨 준다.
        // model은 html파일을 의미한다
        // attributeName인 "paging"은 html파일에서 사용될 변수의 이름이다
        model.addAttribute("paging", pg);
        model.addAttribute("kw", kw);

        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    // url로 부터 id를 받아오게 한다
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.qs.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    // 게시물 생성 버튼을 눌렀을때 게시물을 삭제하는 메소드
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String goToQuestionForm(QuestionForm questionForm) {
        return "question_form";
    }

    // 질문 생성에 대한 메소드
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String createQuestion(@Valid QuestionForm questionForm, BindingResult bindingResult,
            Principal currentLogedInUser) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        } else {

            // 서비스에서 사용자 이름 받아오기
            SiteUser author = this.us.getUser(currentLogedInUser.getName());

            // form 검사에서 성공한 경우
            this.qs.create(questionForm.getSubject(), questionForm.getContent(), author);
        }
        return "redirect:/question/list";
    }

    // 게시글 수정 메소드 (Get)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
        Question question = this.qs.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }

    // 게시글 수정 메소드 (Post)
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        Question question = this.qs.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.qs.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(@PathVariable("id") Integer id, Principal currentLoggedInUser) {
        Question question = this.qs.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(currentLoggedInUser.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다");
        }
        this.qs.delete(question);
        // 삭제 이후 다시 돌아옴
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(@PathVariable("id") Integer id, Principal currentLoggedInUser) {
        Question question = this.qs.getQuestion(id);
        SiteUser siteUser = this.us.getUser(currentLoggedInUser.getName());
        this.qs.vote(question, siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }

}
