package com.untucapital.usuite.utg.untuSite.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.untucapital.usuite.utg.model.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table( name = "news")
public class News extends AbstractEntity {
    @Column( nullable = false)
    private String title;

    @Column( nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    @Column( nullable = false, length=2000)
    private String details;

    @Column( nullable = true)
    private String imageUrl;


    public News() {
    }

    public News(String title, Date date, String details, String imageUrl) {
        this.title = title;
        this.date = date;
        this.details = details;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", details='" + details + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
