package com.example.houserentalsapp;
public class DataHelper {

    String location,description,price,deposit,contact,img;

    public DataHelper() {

    }

    public DataHelper(String location, String price, String contact, String description, String deposit, String key) {
        this.location = location;
        this.description = description;
        this.price = price;
        this.deposit = deposit;
        this.contact = contact;
        this.img = "https://firebasestorage.googleapis.com/v0/b/house-rentals-app.appspot.com/o/images%2F" + key + "?alt=media";

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
