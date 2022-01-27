package com.untucapital.usuite.utg.model;

import com.untucapital.usuite.utg.model.fcb.Response;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "client_loans")
public class ClientLoan extends AbstractEntity {

    @NotNull
    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String idNumber;

    @Column(nullable = false)
    private String maritalStatus;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String placeOfBusiness;

    @Column(nullable = false)
    private String industryCode;

    @Column(nullable = false)
    private String loanAmount;

    @Column(nullable = false)
    private String streetNo;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String businessStartDate;


    private String streetName;

    private String suburb;

    private String photoUpload;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String tenure;

    @NotNull
    @JoinColumn(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String loanStatus;

    private Integer fcbScore;

    private String fcbStatus;

    private String comment;

    private String assignTo;

    private String additionalRemarks;

    @OneToOne
    @NotNull
    @JoinColumn(name = "fcb_response_id", nullable = false)
    private Response fcbResponse;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }
    public String getPhotoUpload() {
        return photoUpload;
    }
    public void setPhotoUpload(String photoUpload) { this.photoUpload = photoUpload;}
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaceOfBusiness() {
        return placeOfBusiness;
    }

    public void setPlaceOfBusiness(String placeOfBusiness) {
        this.placeOfBusiness = placeOfBusiness;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburb() {
        return suburb;
    }
    public void setSuburb(String suburb) {
        this.suburb = suburb;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
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

    public Response getFcbResponse() {
        return fcbResponse;
    }

    public void setFcbResponse(Response fcbResponse) {
        this.fcbResponse = fcbResponse;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(String assignTo) {
        this.assignTo = assignTo;
    }

    public String getAdditionalRemarks() {
        return additionalRemarks;
    }

    public void setAdditionalRemarks(String additionalRemarks) {
        this.additionalRemarks = additionalRemarks;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

     public String getBusinessStartDate() {
        return businessStartDate;
    }

    public void setBusinessStartDate(String businessStartDate) {
        this.businessStartDate = businessStartDate;
    }
}
