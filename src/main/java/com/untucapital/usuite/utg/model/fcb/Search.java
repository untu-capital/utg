package com.untucapital.usuite.utg.model.fcb;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

public class Search {

    private String searchPurpose;

    private String dateSearched;

    private String status;

    private Integer score;

    private String subscriberName;

    private String branchName;

    private String subscriberShortName;

    public String getSearchPurpose() {
        return searchPurpose;
    }

    public void setSearchPurpose(String searchPurpose) {
        this.searchPurpose = searchPurpose;
    }

    public String getDateSearched() {
        return dateSearched;
    }

    public void setDateSearched(String dateSearched) {
        this.dateSearched = dateSearched;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSubscriberShortName() {
        return subscriberShortName;
    }

    public void setSubscriberShortName(String subscriberShortName) {
        this.subscriberShortName = subscriberShortName;
    }
}
