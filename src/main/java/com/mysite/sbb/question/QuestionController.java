package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    // 서비스를 안두고 직접 의존성 주입으로 받아오는 방법
    // private final QuestionRepository qr;

    // 중간에 서비스를 두고 서비스로 부터 받아오는 방법
    private final QuestionService qs;

    @GetMapping("/question/list")
    public String list(Model model) {
        // 직접 받아 오는 방법
        // List<Question> questionListInput = qr.findAll();

        // 서비스로부터 받아오는 방법
        List<Question> questionListInput = qs.getList();

        // questionList 이름의 변수로 questionListInput을 question_list.html에 넘겨 준다.
        // model은 html파일을 의미한다
        model.addAttribute("questionList", questionListInput);

        return "question_list";
    }
}
