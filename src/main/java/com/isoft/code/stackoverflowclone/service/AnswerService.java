package com.isoft.code.stackoverflowclone.service;

import com.isoft.code.stackoverflowclone.dto.AnswerDto;
import com.isoft.code.stackoverflowclone.dto.AnswerQuestionDto;
import com.isoft.code.stackoverflowclone.dto.SearchAnswerDto;
import com.isoft.code.stackoverflowclone.entity.Answer;

import java.util.List;

public interface AnswerService {
    int upVoteAnswer(long id);

    int downVoteAnswer(long id);

    Answer answerQuestion(AnswerQuestionDto request);

    List<AnswerDto> searchAnswers(SearchAnswerDto request);
}
