package com.isoft.code.stackoverflowclone.controller;

import com.isoft.code.stackoverflowclone.dto.AnswerDto;
import com.isoft.code.stackoverflowclone.dto.ApiResponse;
import com.isoft.code.stackoverflowclone.dto.SearchAnswerDto;
import com.isoft.code.stackoverflowclone.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/answers")
public class AnswerController {

    private static final String SUCCESS_MESSAGE = "Success";
    private static final String ANSWER_NOT_FOUND = "Answer Not Found";
    private final AnswerService answerService;

    @PutMapping("/up-vote/{id}")
    ResponseEntity<ApiResponse> upVoteAnswer(@PathVariable long id) {
        int upVote = answerService.upVoteAnswer(id);
        boolean voted = upVote == 1;
        return ResponseEntity.status(voted ? 200 : 404)
                .body(ApiResponse.builder()
                        .message(voted ? SUCCESS_MESSAGE : ANSWER_NOT_FOUND)
                        .data(id)
                        .build()
                );
    }

    @PutMapping("/down-vote/{id}")
    ResponseEntity<ApiResponse> downVoteAnswer(@PathVariable long id) {
        int downVote = answerService.downVoteAnswer(id);
        boolean voted = downVote == 1;
        return ResponseEntity.status(voted ? 200 : 404)
                .body(ApiResponse.builder()
                        .data(id)
                        .build()
                );
    }

    @PostMapping("/search")
    ResponseEntity<ApiResponse> searchQuestion(@RequestBody SearchAnswerDto searchRequest) {
        List<AnswerDto> answers = answerService.searchAnswers(searchRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(answers)
                .build()
        );
    }
}
