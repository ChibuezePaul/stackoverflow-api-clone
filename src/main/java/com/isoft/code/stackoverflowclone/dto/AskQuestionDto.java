package com.isoft.code.stackoverflowclone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AskQuestionDto {
    private String title;
    private String description;
    private String tags; //comma separated
    private long userId;
}
