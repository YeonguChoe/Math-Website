package com.mysite.math;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/sbb")
    @ResponseBody
    public String getMethodName() {
        return "가나다라마바사아";
    }


    // Root로 접근하면 자동으로 /question/list로 이동하게 하는 코드
    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }

}
