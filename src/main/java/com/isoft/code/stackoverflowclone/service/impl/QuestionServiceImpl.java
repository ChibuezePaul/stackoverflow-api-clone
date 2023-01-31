package com.isoft.code.stackoverflowclone.service.impl;

import com.isoft.code.stackoverflowclone.dto.AnswerDto;
import com.isoft.code.stackoverflowclone.dto.AnswerQuestionDto;
import com.isoft.code.stackoverflowclone.dto.AskQuestionDto;
import com.isoft.code.stackoverflowclone.dto.QuestionDto;
import com.isoft.code.stackoverflowclone.dto.SearchQuestionDto;
import com.isoft.code.stackoverflowclone.entity.Answer;
import com.isoft.code.stackoverflowclone.entity.Question;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.exception.CustomException;
import com.isoft.code.stackoverflowclone.repository.QuestionRepository;
import com.isoft.code.stackoverflowclone.service.AnswerService;
import com.isoft.code.stackoverflowclone.service.QuestionService;
import com.isoft.code.stackoverflowclone.util.Utils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerService answerService;
    private final Utils utils;

    @Override
    public QuestionDto askQuestion(AskQuestionDto request) {
        Question question = new Question();
        question.setTitle(request.getTitle());
        question.setDescription(request.getDescription());
        question.setTags(extractTags(request.getTags()));
        question.setCreatedBy(utils.getUserDetails().getUser());
        questionRepository.save(question);
        return toQuestionDto(question);
    }

    @Override
    public List<QuestionDto> findAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return toQuestionDtos(questions);
    }

    @Override
    public QuestionDto answerQuestion(AnswerQuestionDto request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new CustomException("Question not found", HttpStatus.NOT_FOUND));
        Answer answer = answerService.answerQuestion(request);
        question.getAnswers().add(answer);
        question = questionRepository.save(question);
        return toQuestionDto(question);
    }

    @Override
    public List<QuestionDto> searchQuestion(SearchQuestionDto request) {
        Set<String> tags = extractTags(request.getTags());
        List<Question> questions = questionRepository.findAllByTagsInOrTitleContainsIgnoreCase(tags, request.getTitle());
        return toQuestionDtos(questions);
    }

    private List<QuestionDto> toQuestionDtos(List<Question> questions) {
        return questions.stream()
                .map(this::toQuestionDto)
                .collect(Collectors.toList());
    }

    private QuestionDto toQuestionDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .desciption(question.getDescription())
                .createdBy(question.getCreatedBy().getName())
                .tags(String.join(",", question.getTags()))
                .answers(question.getAnswers()
                        .stream()
                        .map(this::toAnswerDto)
                        .collect(Collectors.toList())
                ).build();
    }

    private AnswerDto toAnswerDto(Answer answer) {
        return AnswerDto.builder()
                .createdBy(answer.getCreatedBy().getName())
                .id(answer.getId())
                .votes(answer.getVotes())
                .answer(answer.getAnswer())
                .build();
    }

    private Set<String> extractTags(String commaSeparatedTags) {
        return StringUtils.isBlank(commaSeparatedTags)
                ? Collections.emptySet()
                : Arrays.stream(commaSeparatedTags.split(","))
                        .collect(Collectors.toSet());
    }
}
