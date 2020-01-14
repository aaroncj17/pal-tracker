package io.pivotal.pal.tracker;

import java.time.LocalDate;

public class TimeEntry {
    private long id;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {

        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;

    }

    public TimeEntry(long id, long projectId, long userId, LocalDate date, int hours) {

        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;

    }

    public TimeEntry() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public String toString(){

        return "TimeEntry : { id :" +this.id+
                ",projectId :" + this.projectId +
                ",userId :" + this.userId +
                ",date : " +this.date+
                ",hours : " +this.hours +
                "}";

    }

    @Override
    public int hashCode()
    {
        return (int) getId()*16;
    }

    @Override
    public boolean equals(Object timeEntryobj)
    {

        TimeEntry timeEntry = (TimeEntry) timeEntryobj;

        if(id ==timeEntry.getId() && projectId == timeEntry.getProjectId() && userId == timeEntry.getUserId() &&  date.equals(timeEntry.getDate()) && hours == timeEntry.getHours())
            return true;
        else
            return false;
    }


}