package com.isoft.code.stackoverflowclone.controller;

import com.isoft.code.stackoverflowclone.dto.AnswerQuestionDto;
import com.isoft.code.stackoverflowclone.dto.ApiResponse;
import com.isoft.code.stackoverflowclone.dto.AskQuestionDto;
import com.isoft.code.stackoverflowclone.dto.QuestionDto;
import com.isoft.code.stackoverflowclone.dto.SearchQuestionDto;
import com.isoft.code.stackoverflowclone.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    ResponseEntity<ApiResponse> askQuestion(@RequestBody @Valid AskQuestionDto questionRequest) {
        QuestionDto question = questionService.askQuestion(questionRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(question)
                .build()
        );
    }

    @GetMapping
    ResponseEntity<ApiResponse> viewQuestions() {
        List<QuestionDto> questions = questionService.findAllQuestions();
        return ResponseEntity.ok(ApiResponse.builder()
                .data(questions)
                .build()
        );
    }

    @PutMapping("/answer/{id}")
    ResponseEntity<ApiResponse> answerQuestion(@RequestBody AnswerQuestionDto answerRequest, @PathVariable long id) {
        answerRequest.setQuestionId(id);
        QuestionDto answeredQuestion = questionService.answerQuestion(answerRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(answeredQuestion)
                .build()
        );
    }

    @PostMapping("/search")
    ResponseEntity<ApiResponse> searchQuestion(@RequestBody SearchQuestionDto searchRequest) {
        List<QuestionDto> questions = questionService.searchQuestion(searchRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(questions)
                .build()
        );
    }
}
