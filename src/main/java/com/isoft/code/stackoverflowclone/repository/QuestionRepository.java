package com.isoft.code.stackoverflowclone.repository;

import com.isoft.code.stackoverflowclone.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByTagsInOrTitleContainsIgnoreCase(Set<String> tags, String title);
}
