package com.mountreachsolution.animalbuddy.Rescuser.POJO;

public class POJOFeedback {
    String id,username,feedback,date,time;

    public POJOFeedback(String id, String username, String feedback, String date, String time) {
        this.id = id;
        this.username = username;
        this.feedback = feedback;
        this.date = date;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
