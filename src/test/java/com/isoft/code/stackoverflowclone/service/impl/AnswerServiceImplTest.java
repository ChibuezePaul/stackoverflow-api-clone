package com.isoft.code.stackoverflowclone.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.isoft.code.stackoverflowclone.dto.AnswerDto;
import com.isoft.code.stackoverflowclone.dto.AnswerQuestionDto;
import com.isoft.code.stackoverflowclone.dto.SearchAnswerDto;
import com.isoft.code.stackoverflowclone.entity.Answer;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.service.impl.util.TestUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class AnswerServiceImplTest extends TestUtil {

  @Autowired
  private AnswerServiceImpl underTest;

  @Test
  void upVoteAnswer_shouldReturnZeroWhenAnswerDoesNotExist() {
    assertEquals(0, underTest.upVoteAnswer(1L));
  }

 @Test
  void upVoteAnswer_shouldReturnOneWhenAnswerExists() {
   initDummySecurityContext();
   var createdBy = new Users();
   createdBy.setId(1L);
   var answer = new Answer();
   answer.setAnswer("to be upvoted");
   answer.setCreatedBy(createdBy);
   Answer savedAnswer = answerRepository.save(answer);

   int voteAnswer = underTest.upVoteAnswer(savedAnswer.getId());
   assertEquals(1, voteAnswer);
  }

  @Test
  void downVoteAnswer_shouldReturnZeroWhenAnswerDoesNotExist() {
    assertEquals(0, underTest.downVoteAnswer(2L));
  }

  @Test
  void downVoteAnswer_shouldReturnOneWhenAnswerExists() {
    initDummySecurityContext();
    var createdBy = new Users();
    createdBy.setId(1L);
    var answer = new Answer();
    answer.setAnswer("to be downvoted");
    answer.setCreatedBy(createdBy);
    Answer savedAnswer = answerRepository.save(answer);

    int downVoteAnswer = underTest.downVoteAnswer(savedAnswer.getId());
    assertEquals(1, downVoteAnswer);
  }

  @Test
  void answerQuestion() {
    initDummySecurityContext();

    var dto = new AnswerQuestionDto();
    dto.setQuestionId(1L);
    dto.setAnswer("an answer");

    Answer answer = underTest.answerQuestion(dto);
    assertEquals(dto.getAnswer(), answer.getAnswer());
    assertEquals(1, answerRepository.findAll().size());
  }

  @Test
  void searchAnswers_shouldReturnEmptyListWhenNoAnswerExistsInDatabase() {
    assertTrue(underTest.searchAnswers(new SearchAnswerDto()).isEmpty());
  }

  @Test
  void searchAnswers_shouldReturnAnswersWhenAnswerExistsInDatabase() {
    initDummySecurityContext();

    var createdBy = new Users();
    createdBy.setId(1L);

    Answer answer = new Answer();
    answer.setCreatedBy(createdBy);
    answer.setAnswer("an answer by cp");
    answerRepository.save(answer);

    var dto = new SearchAnswerDto();
    dto.setTitle("cp");
    List<AnswerDto> answers = underTest.searchAnswers(dto);

    assertEquals(1, answers.size());
    assertTrue(answers.get(0).getAnswer().contains(dto.getTitle()));
    assertEquals(1, answerRepository.findAll().size());
  }
}