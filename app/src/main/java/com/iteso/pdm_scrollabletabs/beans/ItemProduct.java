package com.iteso.pdm_scrollabletabs.beans;

/**
 * Created by hsm-y on 03/03/2018.
 */

public class ItemProduct {
    private String title;
    private String store;
    private String location;
    private String phone;
    private String description;
    private int image;

    public ItemProduct(){
        this.title = "";
        this.store = "";
        this.location = "";
        this.phone = "";
        this.description = "";
        this.image = 0;
    }

    public ItemProduct(String title, String store, String location, String phone, String description, int image) {
        this.title = title;
        this.store = store;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Product: " + title +
                "\nStore: " + store +
                "\nLocation: " + location +
                "\nPhone: " + phone +
                "\nDescription: " + description;
    }
}
