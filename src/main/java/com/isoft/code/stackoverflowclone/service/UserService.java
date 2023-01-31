package com.isoft.code.stackoverflowclone.service;

import com.isoft.code.stackoverflowclone.dto.SearchUserDto;
import com.isoft.code.stackoverflowclone.dto.SignInDto;
import com.isoft.code.stackoverflowclone.dto.SignUpDto;
import com.isoft.code.stackoverflowclone.dto.UserDto;
import com.isoft.code.stackoverflowclone.exception.CustomException;

import java.util.List;

public interface UserService {
    UserDto signUp(SignUpDto signUpRequest);

    UserDto signIn(SignInDto signInRequest) throws CustomException;

    List<UserDto> searchUsers(SearchUserDto request);
}
