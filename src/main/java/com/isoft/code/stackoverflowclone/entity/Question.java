package com.isoft.code.stackoverflowclone.entity;

import com.isoft.code.stackoverflowclone.commons.data.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
}
