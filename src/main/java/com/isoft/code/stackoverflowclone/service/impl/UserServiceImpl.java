package com.isoft.code.stackoverflowclone.service.impl;

import com.isoft.code.stackoverflowclone.dto.SearchUserDto;
import com.isoft.code.stackoverflowclone.dto.SignInDto;
import com.isoft.code.stackoverflowclone.dto.SignUpDto;
import com.isoft.code.stackoverflowclone.dto.UserDto;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.exception.CustomException;
import com.isoft.code.stackoverflowclone.repository.UserRepository;
import com.isoft.code.stackoverflowclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDto signUp(SignUpDto signUpRequest) {
        Users newUser = new Users();
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setName(signUpRequest.getDisplayName());
//        newUser.setPassword(passwordEncoder.encode(String.valueOf(signUpRequest.getPassword()))); //TODO fix
        newUser.setPassword(signUpRequest.getPassword());
        userRepository.save(newUser);
        return UserDto.builder()
                .id(newUser.getId())
                .displayName(newUser.getName())
                .email(newUser.getEmail())
                .token("123654") //TODO fix
                .build();
    }

    @Override
    public UserDto signIn(SignInDto signInRequest) {
        Users user = userRepository.findByEmail(signInRequest.getEmail())
                .orElseThrow(() -> {
                    String user_not_found = "User with email " + signInRequest.getEmail() + " not found";
                    return new CustomException(user_not_found, HttpStatus.NOT_FOUND);
                });

        return UserDto.builder()
                .id(user.getId())
                .displayName(user.getName())
                .email(user.getEmail())
                .token("123654") //TODO fix
                .build();
    }

    @Override
    public List<UserDto> searchUsers(SearchUserDto request) {
        return userRepository.findAllByEmailOrNameContainingIgnoreCase(request.getEmail(), request.getName())
                .stream()
                .map(user -> UserDto.builder()
                        .email(user.getEmail())
                        .displayName(user.getName())
                        .id(user.getId())
                        .build())
                .collect(Collectors.toList());
    }
}
