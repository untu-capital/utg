package com.untucapital.usuite.utg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table( name="ManagementTeam")
public class Team extends AbstractEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String position;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String imageUpload;

    public Team() {
    }

    public Team(String name, String position, String description, String imageUpload) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.imageUpload = imageUpload;
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

    public String getImageUpload() {
        return imageUpload;
    }

    public void setImageUpload(String imageUpload) {
        this.imageUpload = imageUpload;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", postion='" + position + '\'' +
                ", description='" + description + '\'' +
                ", imageUpload='" + imageUpload + '\'' +
                '}';
    }
}
