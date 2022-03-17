package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Testimonials")
public class Testimonial extends AbstractEntity {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String imageUpload;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String loanType;

    private boolean allow;

    public Testimonial() {
    }

    public Testimonial(String username, String imageUpload, String comment, String loanType, boolean allow) {
        this.username = username;
        this.imageUpload = imageUpload;
        this.comment = comment;
        this.loanType = loanType;
        this.allow = allow;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(String imageUpload) {
        this.imageUpload = imageUpload;
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

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    @Override
    public String toString() {
        return "Testimonial{" +
                "username='" + username + '\'' +
                ", imageUpload='" + imageUpload + '\'' +
                ", comment='" + comment + '\'' +
                ", loanType='" + loanType + '\'' +
                ", allow=" + allow +
                '}';
    }
}
