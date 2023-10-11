package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lead_status")
public class LeadStatus extends AbstractEntity {

    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}