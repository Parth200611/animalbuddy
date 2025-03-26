package com.mountreachsolution.animalbuddy.User.pojo;

public class POJOEvent {
    String id,name,location,date,stime,etime,dis,entry,image;

    public POJOEvent(String id, String name, String location, String date, String stime, String etime, String dis, String entry, String image) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.stime = stime;
        this.etime = etime;
        this.dis = dis;
        this.entry = entry;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getEtime() {
        return etime;
    }

    public void setEtime(String etime) {
        this.etime = etime;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
