package com.mountreachsolution.animalbuddy.Rescuser.POJO;

public class POJOGetHistory {
    String id,Rusername,location,detail,username,name,image,status;

    public POJOGetHistory(String id, String rusername, String location, String detail, String username, String name, String image, String status) {
        this.id = id;
        Rusername = rusername;
        this.location = location;
        this.detail = detail;
        this.username = username;
        this.name = name;
        this.image = image;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRusername() {
        return Rusername;
    }

    public void setRusername(String rusername) {
        Rusername = rusername;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
