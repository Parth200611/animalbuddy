package com.mountreachsolution.animalbuddy.Rescuser.POJO;

public class POJOAllRequest {
    String id,username,location,detail,image;

    public POJOAllRequest(String id, String username, String location, String detail, String image) {
        this.id = id;
        this.username = username;
        this.location = location;
        this.detail = detail;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
