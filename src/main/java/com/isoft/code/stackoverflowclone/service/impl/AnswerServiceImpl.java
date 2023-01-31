package com.isoft.code.stackoverflowclone.service.impl;

import com.isoft.code.stackoverflowclone.dto.AnswerDto;
import com.isoft.code.stackoverflowclone.dto.AnswerQuestionDto;
import com.isoft.code.stackoverflowclone.dto.SearchAnswerDto;
import com.isoft.code.stackoverflowclone.entity.Answer;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.repository.AnswerRepository;
import com.isoft.code.stackoverflowclone.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    @Transactional
    public int upVoteAnswer(long id) {
        return answerRepository.upVoteAnswer(id);
    }

    @Override
    @Transactional
    public int downVoteAnswer(long id) {
        return answerRepository.downVoteAnswer(id);
    }

    @Override
    public Answer answerQuestion(AnswerQuestionDto request) {
        Answer answer = new Answer();
        answer.setAnswer(request.getAnswer());
        Users createdBy = new Users();
        createdBy.setId(request.getUserId()); //TODO get from token
        answer.setCreatedBy(createdBy);
        return answerRepository.save(answer);
    }

    @Override
    public List<AnswerDto> searchAnswers(SearchAnswerDto request) {
        List<Answer> answers = answerRepository.findAllByAnswerContainingIgnoreCase(request.getTitle());
        return answers.stream()
                .map(this::toAnswerDto)
                .collect(Collectors.toList());
    }

    private AnswerDto toAnswerDto(Answer answer) {
        return AnswerDto.builder()
                .createdBy(answer.getCreatedBy().getName())
                .id(answer.getId())
                .votes(answer.getVotes())
                .answer(answer.getAnswer())
                .build();
    }
}
