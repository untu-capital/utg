package com.untucapital.usuite.utg.model;

import com.untucapital.usuite.utg.model.enums.RoleType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

/**
 * @author Chirinda Nyasha Dell {22/11/2021}
 */

@Entity
@Table(name = "roles")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleType name;

    private String description;

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
