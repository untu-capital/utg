package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "branches", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"branchName", "code"})
})
public class Branches extends AbstractEntity {

    private String branchName;

    private  String branchAddress;

    private  String branchTellPhone;

    private  String branchStatus;

    private String googleMap;

    private String directionsLink;

    private int code;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getBranchTellPhone() {
        return branchTellPhone;
    }

    public void setBranchTellPhone(String branchTellPhone) {
        this.branchTellPhone = branchTellPhone;
    }

    public String getBranchStatus() {
        return branchStatus;
    }

    public void setBranchStatus(String branchStatus) {
        this.branchStatus = branchStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getGoogleMap() {
        return googleMap;
    }

    public void setGoogleMap(String googleMap) {
        this.googleMap = googleMap;
    }

    public String getDirectionsLink() {
        return directionsLink;
    }

    public void setDirectionsLink(String directionsLink) {
        this.directionsLink = directionsLink;
    }
}
