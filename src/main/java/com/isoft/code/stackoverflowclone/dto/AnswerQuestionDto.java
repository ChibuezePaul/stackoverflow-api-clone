package com.isoft.code.stackoverflowclone.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerQuestionDto {
    private String answer;
    private long userId;
    private long questionId;
}
