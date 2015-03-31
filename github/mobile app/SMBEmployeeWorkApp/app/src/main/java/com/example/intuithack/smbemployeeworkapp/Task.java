package com.example.intuithack.smbemployeeworkapp;


import java.util.Date;
import java.util.List;

/**
 * Created by anshulika.ks on 30/03/15.
 */
public class Task {
    private String title;
    private long duration;
    private boolean isHeader;
    private Date date;
    private TaskPriority taskPriority;
    private String description;
    private List<String> referenceImagePath;
    private List<String> techSpecs;

    public Task(String title, long duration, boolean isHeader, TaskPriority taskPriority, Date date) {
        this.title = title;
        this.duration = duration;
        this.isHeader = isHeader;
        this.taskPriority = taskPriority;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setIsTilte(boolean isTilte) {
        this.isHeader = isTilte;
    }

    public void setIsHeader(boolean isHeader) {
        this.isHeader = isHeader;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getReferenceImagePath() {
        return referenceImagePath;
    }

    public void setReferenceImagePath(List<String> referenceImagePath) {
        this.referenceImagePath = referenceImagePath;
    }

    public List<String> getTechSpecs() {
        return techSpecs;
    }

    public void setTechSpecs(List<String> techSpecs) {
        this.techSpecs = techSpecs;
    }
}
