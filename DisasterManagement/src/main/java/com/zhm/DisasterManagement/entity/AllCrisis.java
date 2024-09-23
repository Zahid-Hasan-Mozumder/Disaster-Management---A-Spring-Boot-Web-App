package com.zhm.DisasterManagement.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "all_crisis")
public class AllCrisis {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private String title;
    private String description;
    private String location;
    private Date postingDate;
    private String severity;
    private String requiredHelp;
    private String status;

    // constructors
    public AllCrisis() {}

    public AllCrisis(String image, String title, String description, String location, Date postingDate, String severity, String requiredHelp, String status) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.location = location;
        this.postingDate = postingDate;
        this.severity = severity;
        this.requiredHelp = requiredHelp;
        this.status = status;
    }

    public AllCrisis(NewCrisis newCrisis, String status) {
        this.image = newCrisis.getImage();
        this.title = newCrisis.getTitle();
        this.description = newCrisis.getDescription();
        this.location = newCrisis.getLocation();
        this.postingDate = newCrisis.getPostingDate();
        this.severity = newCrisis.getSeverity();
        this.requiredHelp = newCrisis.getRequiredHelp();
        this.status = status;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getRequiredHelp() {
        return requiredHelp;
    }

    public void setRequiredHelp(String requiredHelp) {
        this.requiredHelp = requiredHelp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString() method

    @Override
    public String toString() {
        return "AllCrisis{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", postingDate=" + postingDate +
                ", severity='" + severity + '\'' +
                ", requiredHelp='" + requiredHelp + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
