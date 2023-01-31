package com.isoft.code.stackoverflowclone.repository;

import com.isoft.code.stackoverflowclone.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Modifying
    @Query("update Answer set votes = votes+1 where id = :id")
    int upVoteAnswer(long id);

    @Modifying
    @Query("update Answer set votes = votes-1 where id = :id")
    int downVoteAnswer(long id);

    List<Answer> findAllByAnswerContainingIgnoreCase(String title);
}
