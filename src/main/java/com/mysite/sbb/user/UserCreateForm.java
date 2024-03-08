package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
    @Size(min = 5, max = 25, message = "아이디의 길이가 5와 25자 사이어야 합니다")
    @NotEmpty(message = "ID를 입력해 주세요")
    private String username;

    @NotEmpty(message = "비밀번호를 입력해 주세요")
    private String password1;

    @NotEmpty(message = "비밀번호를 입력해 주세요")
    private String password2;

    @NotEmpty(message = "이메일을 입력해 주세요")
    @Email(message = "올바른 이메일 형식으로 작성해 주세요")
    private String email;

}