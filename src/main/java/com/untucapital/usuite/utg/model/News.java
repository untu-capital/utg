package com.untucapital.usuite.utg.model;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private String imageUpLoad;


    public News() {
    }

    public News(String title, Date date, String details, String imageUpLoad) {
        this.title = title;
        this.date = date;
        this.details = details;
        this.imageUpLoad = imageUpLoad;
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

    public String getImageUpLoad() {
        return imageUpLoad;
    }

    public void setImageUpLoad(String imageUpLoad) {
        this.imageUpLoad = imageUpLoad;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", date=" + date +
                ", details='" + details + '\'' +
                ", imageUpLoad='" + imageUpLoad + '\'' +
                '}';
    }
}
