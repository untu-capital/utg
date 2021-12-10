package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

public class Report {

    private String id;

    @JsonProperty(value = "Subscriber")
    private String subscriber;

    @JsonProperty(value = "User")
    private String user;

    @JsonProperty(value = "Date")
    private String date;

    @JsonProperty(value = "Full_Name")
    private String fullName;

    @JsonProperty(value = "National_ID")
    private String nationalID;

    @JsonProperty(value = "Score")
    private Integer score;

    @JsonProperty(value = "Status")
    private String status;

    @JsonProperty(value = "DOB")
    private String dob;

    @JsonProperty(value = "Passport")
    private String passport;

    @JsonProperty(value = "License")
    private String license;

    @JsonProperty(value = "Mobile")
    private String mobile;

    @JsonProperty(value = "Phone")
    private String phone;

    @JsonProperty(value = "Email")
    private String email;

    @JsonProperty(value = "Gender")
    private String gender;

    @JsonProperty(value = "Married")
    private String married;

    @JsonProperty(value = "Nationality")
    private String nationality;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
