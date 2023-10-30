package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;
import com.untucapital.usuite.utg.model.enums.RoleType;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;


public class RoleResponseDTO extends AbstractEntityDTO {

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
