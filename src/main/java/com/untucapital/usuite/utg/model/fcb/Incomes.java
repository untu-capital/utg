package com.untucapital.usuite.utg.model.fcb;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "incomes")
public class Incomes extends AbstractEntity {

    @Column(name = "employer")
    private String employer;

    @Column(name = "industry")
    private String industry;

    @Column(name = "salary_range")
    private String salaryRange;

    @Column(name = "position")
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

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public java.lang.String getPosition() {
        return position;
    }

    public void setPosition(java.lang.String position) {
        this.position = position;
    }
}
