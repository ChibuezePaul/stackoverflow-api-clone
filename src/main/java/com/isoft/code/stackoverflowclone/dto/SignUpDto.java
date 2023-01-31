package com.isoft.code.stackoverflowclone.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class SignUpDto {
    @NotBlank(message = "display name is required")
    private String displayName;

    @NotBlank(message = "password is required")
    private String password;

//    @Pattern()
    @NotBlank(message = "email is required")
    private String email;
}
