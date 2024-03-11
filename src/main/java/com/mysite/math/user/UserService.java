package com.mysite.math.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mysite.math.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository ur;
    private final PasswordEncoder pe;

    public SiteUser create(String username, String password, String email) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);

        // 비밀번호는 암호화 해서 리포지토리로 저장
        user.setPassword(pe.encode(password));

        // 리포지토리에 저장
        this.ur.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.ur.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("사용자 정보를 찾을수 없습니다");
        }
    }
}
