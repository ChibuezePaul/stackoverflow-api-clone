package com.isoft.code.stackoverflowclone.entity;

import com.isoft.code.stackoverflowclone.commons.data.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Answer extends AbstractEntity {
    private static final long serialVersionUID = -4324628622719954637L;

    private String answer;

    private int votes;

    @ManyToOne
    private Users createdBy;
}
