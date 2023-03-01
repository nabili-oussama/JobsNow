package com.ocr1.jobsnow;

public class JobOffer {
    private String ID;
    private String title;
    private String description;
    private String duration;
    private String remuneration;

    public JobOffer() {
        // Required empty constructor
    }

    public JobOffer(String title, String description, String duration, String remuneration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.remuneration = remuneration;
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(String remuneration) {
        this.remuneration = remuneration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}