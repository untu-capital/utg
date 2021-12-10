package com.untucapital.usuite.utg.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Chirinda Nyasha Dell - 10/12/2021
 */

@Entity
@Table(name = "industries")
public class Industry extends AbstractEntity {

    private String name;

    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
