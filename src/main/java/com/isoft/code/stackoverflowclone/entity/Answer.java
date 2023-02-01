package com.isoft.code.stackoverflowclone.entity;

import com.isoft.code.stackoverflowclone.commons.data.AbstractEntity;
import java.util.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Answer extends AbstractEntity {
    private static final long serialVersionUID = -4324628622719954637L;

    private String answer;

    private int votes;

    @ManyToOne
    private Users createdBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer1 = (Answer) o;
        return votes == answer1.votes && Objects.equals(answer, answer1.answer)
            && Objects.equals(createdBy, answer1.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, votes, createdBy);
    }
}
