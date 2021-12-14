package com.untucapital.usuite.utg.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "loan_application", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_number", "phonenumber"})
})
public class ClientLoan extends AbstractEntity {

    @NotNull
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "middlename")
    private String middlename;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "id_number", nullable = false)
    private String id_number;

    @Column(name = "marital", nullable = false)
    private String marital;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "phonenumber", nullable = false)
    private String phonenumber;

    @Column(name = "pob", nullable = false)
    private String pob;

    @Column(name = "industry_code", nullable = false)
    private String industryCode;

    @Column(name = "loan", nullable = false)
    private String loan;

    @Column(name = "street_no", nullable = false)
    private String street_no;

    @Column(name = "street_name", nullable = false)
    private String street_name;

    @Column(name = "surbub", nullable = false)
    private String surbub;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "tenure", nullable = false)
    private String tenure;

    @Column(name = "status")
    private String status;

    private Integer fcbScore;

    private String fcbStatus;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getMarital() {
        return marital;
    }

    public void setMarital(String marital) {
        this.marital = marital;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPob() {
        return pob;
    }

    public void setPob(String pob) {
        this.pob = pob;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public String getStreet_no() {
        return street_no;
    }

    public void setStreet_no(String street_no) {
        this.street_no = street_no;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getSurbub() {
        return surbub;
    }

    public void setSurbub(String surbub) {
        this.surbub = surbub;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getFcbScore() {
        return fcbScore;
    }

    public void setFcbScore(Integer fcbScore) {
        this.fcbScore = fcbScore;
    }

    public String getFcbStatus() {
        return fcbStatus;
    }

    public void setFcbStatus(String fcbStatus) {
        this.fcbStatus = fcbStatus;
    }
}
