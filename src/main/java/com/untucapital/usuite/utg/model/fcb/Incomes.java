package com.untucapital.usuite.utg.model.fcb;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "incomes")
public class Incomes extends AbstractEntity {

    private String employer;

    private String industry;

    private String salary_range;

    private String position;

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public java.lang.String getIndustry() {
        return industry;
    }

    public void setIndustry(java.lang.String industry) {
        this.industry = industry;
    }

    public java.lang.String getSalary_range() {
        return salary_range;
    }

    public void setSalary_range(java.lang.String salary_range) {
        this.salary_range = salary_range;
    }

    public java.lang.String getPosition() {
        return position;
    }

    public void setPosition(java.lang.String position) {
        this.position = position;
    }
}
