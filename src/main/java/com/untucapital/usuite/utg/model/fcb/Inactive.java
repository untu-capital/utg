package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

public class Inactive {

    private String counterparty;

    private String branch;

    private String amount;

    @JsonProperty(value = "event_type")
    private String eventType;

    private String currency;

    private String comment;

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
