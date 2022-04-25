package com.untucapital.usuite.utg.untu_capital.model;


import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "faqs")
public class Faqs extends AbstractEntity {

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private  String description;

    public Faqs() {

    }

    public Faqs(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Faqs{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
