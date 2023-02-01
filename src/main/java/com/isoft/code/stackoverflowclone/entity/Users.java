package com.isoft.code.stackoverflowclone.entity;

import com.isoft.code.stackoverflowclone.commons.data.AbstractEntity;
import java.util.Objects;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users extends AbstractEntity {
    private static final long serialVersionUID = 8527186976691968874L;

    private String name;
    @Column(unique = true)
    private String email;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Users users = (Users) o;
        return Objects.equals(name, users.name) && Objects.equals(email, users.email)
            && Objects.equals(password, users.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password);
    }
}
