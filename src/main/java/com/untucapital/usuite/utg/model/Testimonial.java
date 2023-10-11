package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Testimonials")
public class Testimonial extends AbstractEntity {
    private String username;
    private String comment;
    private String loanType;
    private String imageUrl;

    public Testimonial() {
    }

    public Testimonial(String username, String comment, String loanType, String imageUrl) {
        this.username = username;
        this.comment = comment;
        this.loanType = loanType;
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Testimonial{" +
                "username='" + username + '\'' +
                ", comment='" + comment + '\'' +
                ", loanType='" + loanType + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
