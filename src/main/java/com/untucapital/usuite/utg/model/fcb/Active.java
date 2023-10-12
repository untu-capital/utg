package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Chirinda Nyasha Dell - 7/12/2021
 */

@Entity
@Table(name = "fcb_active_searches")
public class Active extends AbstractEntity {

    private String counterparty;

    private String branch;

    private String amount;

    private String court_ref;

    @JsonProperty(value = "event_type")
    private String eventType;

    private String currency;

    private String comment;

    private String event_date;

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

    public String getCourt_ref() {
        return court_ref;
    }

    public void setCourt_ref(String court_ref) {
        this.court_ref = court_ref;
    }

    public java.lang.String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(java.lang.String event_date) {
        this.event_date = event_date;
    }
}