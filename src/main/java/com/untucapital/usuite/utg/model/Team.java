package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name="teamManagement")
public class Team extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String imageUrl;

    public Team() {
    }

    public Team(String name, String position, String description, String imageUrl) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUpload) {
        this.imageUrl = imageUpload;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", postion='" + position + '\'' +
                ", description='" + description + '\'' +
                ", imageUpload='" + imageUrl + '\'' +
                '}';
    }
}
