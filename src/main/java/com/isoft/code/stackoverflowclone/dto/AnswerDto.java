package com.isoft.code.stackoverflowclone.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private long id;
    private String createdBy;
    private int votes;
    private String answer;
}
