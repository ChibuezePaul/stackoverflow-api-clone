package com.isoft.code.stackoverflowclone.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SignInDto {
    @NotBlank(message = "password is required")
    private String password;

    //    @Pattern()
    @NotBlank(message = "email is required")
    private String email;
}
