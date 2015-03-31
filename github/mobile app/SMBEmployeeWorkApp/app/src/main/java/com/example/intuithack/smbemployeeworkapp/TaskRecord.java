package com.example.intuithack.smbemployeeworkapp;

/**
 * Created by anshulika.ks on 31/03/15.
 */
public class TaskRecord {
    String duration;
    String timeline;
    boolean isStar;

    public TaskRecord(String duration, String timeline, boolean isStar) {
        this.duration = duration;
        this.timeline = timeline;
        this.isStar = isStar;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public boolean isStar() {
        return isStar;
    }

    public void setIsStar(boolean isStar) {
        this.isStar = isStar;
    }
}
