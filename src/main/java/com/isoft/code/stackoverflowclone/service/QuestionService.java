package com.isoft.code.stackoverflowclone.service;

import com.isoft.code.stackoverflowclone.dto.AnswerQuestionDto;
import com.isoft.code.stackoverflowclone.dto.AskQuestionDto;
import com.isoft.code.stackoverflowclone.dto.QuestionDto;
import com.isoft.code.stackoverflowclone.dto.SearchQuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto askQuestion(AskQuestionDto questionRequest);

    List<QuestionDto> findAllQuestions();

    QuestionDto answerQuestion(AnswerQuestionDto request);

    List<QuestionDto> searchQuestion(SearchQuestionDto searchRequest);
}
