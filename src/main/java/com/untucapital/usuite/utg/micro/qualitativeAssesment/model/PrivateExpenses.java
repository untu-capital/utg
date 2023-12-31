package com.untucapital.usuite.utg.micro.qualitativeAssesment.model;

import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PrivateExpensesMicro")
public class PrivateExpenses extends AbstractEntity {
    private String loanId;
    private String food;
    private String rent;
    private String clothing;
    private String health;
    private String transportation;
    private String communication;
    private String generatorOrCAr;
    private String utilities;
    private String religion;
    private String maid;

    public PrivateExpenses() {
    }

    public PrivateExpenses(String loanId, String food, String rent, String clothing, String health, String transportation, String communication, String generatorOrCAr, String utilities, String religion, String maid) {
        this.loanId = loanId;
        this.food = food;
        this.rent = rent;
        this.clothing = clothing;
        this.health = health;
        this.transportation = transportation;
        this.communication = communication;
        this.generatorOrCAr = generatorOrCAr;
        this.utilities = utilities;
        this.religion = religion;
        this.maid = maid;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getClothing() {
        return clothing;
    }

    public void setClothing(String clothing) {
        this.clothing = clothing;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public String getGeneratorOrCAr() {
        return generatorOrCAr;
    }

    public void setGeneratorOrCAr(String generatorOrCAr) {
        this.generatorOrCAr = generatorOrCAr;
    }

    public String getUtilities() {
        return utilities;
    }

    public void setUtilities(String utilities) {
        this.utilities = utilities;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMaid() {
        return maid;
    }

    public void setMaid(String maid) {
        this.maid = maid;
    }

    @Override
    public String toString() {
        return "PrivateExpenses{" +
                "loanId='" + loanId + '\'' +
                ", food='" + food + '\'' +
                ", rent='" + rent + '\'' +
                ", clothing='" + clothing + '\'' +
                ", health='" + health + '\'' +
                ", transportation='" + transportation + '\'' +
                ", communication='" + communication + '\'' +
                ", generatorOrCAr='" + generatorOrCAr + '\'' +
                ", utilities='" + utilities + '\'' +
                ", religion='" + religion + '\'' +
                ", maid='" + maid + '\'' +
                '}';
    }
}
