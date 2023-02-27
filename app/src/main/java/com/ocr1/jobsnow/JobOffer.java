package com.ocr1.jobsnow;

public class JobOffer {
    private final String title;
    private final int duration;
    private final int remuneration;

    public JobOffer(String title, int duration, int remuneration) {
        this.title = title;
        this.duration = duration;
        this.remuneration = remuneration;
    }

    public String getTitle() {
        return title;
    }

    public int getDuration() {
        return duration;
    }

    public int getRemuneration() {
        return remuneration;
    }
}
