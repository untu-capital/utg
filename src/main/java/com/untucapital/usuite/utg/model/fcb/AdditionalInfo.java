package com.untucapital.usuite.utg.model.fcb;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fcb_additional_info")
public class AdditionalInfo extends AbstractEntity {

    @JsonProperty(value = "INFO")
    private String INFO;

    public String getINFO() {
        return INFO;
    }

    public void setINFO(String INFO) {
        this.INFO = INFO;
    }

}
