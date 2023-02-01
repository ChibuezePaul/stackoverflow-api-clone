package com.isoft.code.stackoverflowclone.entity;

import com.isoft.code.stackoverflowclone.commons.data.AbstractEntity;
import java.util.Objects;
import javax.persistence.FetchType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Question extends AbstractEntity {
    private static final long serialVersionUID = -8389001151935031892L;

    private String title;

    private String description;

    @ElementCollection
    private Set<String> tags;

    @ManyToOne
    private Users createdBy;

    @OneToMany
    List<Answer> answers = new ArrayList<>(0);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Question question = (Question) o;
        return Objects.equals(title, question.title) && Objects.equals(description,
            question.description) && Objects.equals(tags, question.tags)
            && Objects.equals(createdBy, question.createdBy) && Objects.equals(
            answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, tags, createdBy, answers);
    }
}
