package com.untucapital.usuite.utg.DTO.response;

import com.untucapital.usuite.utg.DTO.AbstractEntityDTO;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


public class SignatureResponseDTO extends AbstractEntityDTO {

    private String fullName;

    private String position;

    private String branch;

    private String imageUrl;

    public SignatureResponseDTO() {
    }

    public SignatureResponseDTO(String fullName, String position, String branch, String imageUrl) {
        this.fullName = fullName;
        this.position = position;
        this.branch = branch;
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Signature{" +
                "fullName='" + fullName + '\'' +
                ", postion='" + position + '\'' +
                ", branch='" + branch + '\'' +
                ", imageUpload='" + imageUrl + '\'' +
                '}';
    }
}