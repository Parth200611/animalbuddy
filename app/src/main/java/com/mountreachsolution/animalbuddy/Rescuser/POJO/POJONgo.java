package com.mountreachsolution.animalbuddy.Rescuser.POJO;

public class POJONgo {
    String id,name,mobil,address;

    public POJONgo(String id, String name, String mobil, String address) {
        this.id = id;
        this.name = name;
        this.mobil = mobil;
        this.address = address;
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

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
