package com.mandarin.mplus.controller;

import com.mandarin.mplus.dto.UserDTO;
import com.mandarin.mplus.model.User;
import com.mandarin.mplus.security.TokenProvider;
import com.mandarin.mplus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    @GetMapping("/user/login")
    public UserDTO loginByKakao(@RequestParam String code) {

        User user = userService.loginKakaoUser(code);
        String token = tokenProvider.create(user);

        UserDTO responseUserDTO = UserDTO.builder()
                .nickname(user.getNickname())
                .token(token)
                .build();

        return responseUserDTO;
    }
}
