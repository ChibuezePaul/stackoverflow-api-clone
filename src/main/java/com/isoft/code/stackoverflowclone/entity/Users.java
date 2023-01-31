package com.isoft.code.stackoverflowclone.entity;

import com.isoft.code.stackoverflowclone.commons.data.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Users extends AbstractEntity {
    private static final long serialVersionUID = 8527186976691968874L;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;
}
