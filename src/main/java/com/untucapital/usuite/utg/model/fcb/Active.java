package com.untucapital.usuite.utg.model.fcb;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

public class Active {

    private Object counterparty;

    private Object branch;

    private String amount;

    private String eventType;

    private String currency;

    private Object comment;

    public Object getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(Object counterparty) {
        this.counterparty = counterparty;
    }

    public Object getBranch() {
        return branch;
    }

    public void setBranch(Object branch) {
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

    public Object getComment() {
        return comment;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }
}