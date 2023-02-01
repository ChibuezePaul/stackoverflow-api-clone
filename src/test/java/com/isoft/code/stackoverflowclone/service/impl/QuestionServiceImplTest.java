package com.isoft.code.stackoverflowclone.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.isoft.code.stackoverflowclone.dto.AnswerQuestionDto;
import com.isoft.code.stackoverflowclone.dto.AskQuestionDto;
import com.isoft.code.stackoverflowclone.dto.QuestionDto;
import com.isoft.code.stackoverflowclone.dto.SearchQuestionDto;
import com.isoft.code.stackoverflowclone.entity.Question;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.repository.QuestionRepository;
import com.isoft.code.stackoverflowclone.service.impl.util.TestUtil;
import com.isoft.code.stackoverflowclone.util.Utils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

class QuestionServiceImplTest extends TestUtil {

  @Autowired
  @InjectMocks
  private QuestionServiceImpl underTest;

  @Autowired
  private Utils utils;

  @Autowired
  protected QuestionRepository questionRepository;

  @BeforeEach
  void setUp() {
    questionRepository.deleteAll();
  }

  @Test
  void askQuestion() {
    AskQuestionDto dto = new AskQuestionDto();
    dto.setTitle("my early morning routine involves coffee and music");
    dto.setDescription("can you help share a better morning routine");
    dto.setTags("music,coffee");

    initDummySecurityContext();

    QuestionDto question = underTest.askQuestion(dto);

    assertEquals(dto.getTitle(), question.getTitle());
    assertTrue(question.getTags().contains("music"));
    assertEquals(1, questionRepository.findAll().size());
  }

  @Test
  void findAllQuestions_shouldReturnEmptyWhenNoQuestionExistInDatabase() {
    List<QuestionDto> questions = underTest.findAllQuestions();
    assertTrue(questions.isEmpty());
  }

  @Test
  void findAllQuestions_shouldReturnAllQuestionsInDatabase() {
    initDummySecurityContext();
    var createdBy = new Users();
    createdBy.setId(1L);

    Question fisrtQuestion = new Question();
    fisrtQuestion.setCreatedBy(createdBy);
    fisrtQuestion.setTitle("question-one");
    fisrtQuestion.setTags(Collections.emptySet());

    Question secondQuestion = new Question();
    secondQuestion.setCreatedBy(createdBy);
    secondQuestion.setTitle("question-two");
    secondQuestion.setTags(Collections.singleton("tag"));

    questionRepository.saveAll(Arrays.asList(fisrtQuestion, secondQuestion));

    List<QuestionDto> questions = underTest.findAllQuestions();

    assertEquals(2, questions.size());
    assertEquals(2, questionRepository.findAll().size());

  }

  @Test
  void answerQuestion() {
    answerRepository.deleteAll();
    initDummySecurityContext();
    var createdBy = new Users();
    createdBy.setId(1L);

    Question question = new Question();
    question.setCreatedBy(createdBy);
    question.setTitle("question-one");
    question.setTags(Collections.emptySet());
    questionRepository.save(question);

    AnswerQuestionDto dto = new AnswerQuestionDto();
    dto.setQuestionId(question.getId());
    dto.setAnswer("answer-one");

    QuestionDto answerQuestion = underTest.answerQuestion(dto);

    assertEquals(1, answerQuestion.getAnswers().size());
    assertEquals(dto.getAnswer(), answerQuestion.getAnswers().get(0).getAnswer());
    assertEquals(1, answerRepository.findAll().size());
  }

  @Test
  void searchQuestion() {
    initDummySecurityContext();
    var createdBy = new Users();
    createdBy.setId(1L);

    Question question = new Question();
    question.setCreatedBy(createdBy);
    question.setTitle("question-one");
    question.setTags(Collections.emptySet());
    questionRepository.save(question);

    SearchQuestionDto dto = new SearchQuestionDto();
    dto.setTitle("one");

    List<QuestionDto> questions = underTest.searchQuestion(dto);
    assertEquals(1, questions.size());
  }
}