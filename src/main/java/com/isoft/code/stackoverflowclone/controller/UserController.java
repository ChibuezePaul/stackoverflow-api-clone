package com.isoft.code.stackoverflowclone.controller;

import com.isoft.code.stackoverflowclone.dto.ApiResponse;
import com.isoft.code.stackoverflowclone.dto.SearchUserDto;
import com.isoft.code.stackoverflowclone.dto.SignInDto;
import com.isoft.code.stackoverflowclone.dto.SignUpDto;
import com.isoft.code.stackoverflowclone.dto.UserDto;
import com.isoft.code.stackoverflowclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    ResponseEntity<ApiResponse> signup(@RequestBody @Valid SignUpDto signUpRequest) {
        UserDto signUpResponse = userService.signUp(signUpRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(signUpResponse)
                .build()
        );
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse> signIn(@RequestBody @Valid SignInDto signInRequest) {
        UserDto signInResponse = userService.signIn(signInRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(signInResponse)
                .build()
        );
    }

    @PostMapping("/search")
    ResponseEntity<ApiResponse> searchQuestion(@RequestBody SearchUserDto searchRequest) {
        List<UserDto> users = userService.searchUsers(searchRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(users)
                .build()
        );
    }
}

