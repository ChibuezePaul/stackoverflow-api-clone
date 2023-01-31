package com.isoft.code.stackoverflowclone.dto;

import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private long id;
    private String title;
    private String desciption;
    private String createdBy;
    private String tags;
    private List<AnswerDto> answers;
}
