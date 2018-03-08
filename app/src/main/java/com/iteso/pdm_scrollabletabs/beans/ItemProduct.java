package com.iteso.pdm_scrollabletabs.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hsm-y on 03/03/2018.
 */

public class ItemProduct implements Parcelable{
    private String title;
    private String store;
    private String location;
    private String phone;
    private String description;
    private int image;
    private int code;

    public ItemProduct(Parcel in){
        image = in.readInt();
        title = in.readString();
        store = in.readString();
        location = in.readString();
        phone = in.readString();
        description = in.readString();
        code = in.readInt();
    }

    public ItemProduct(){
        this.title = "";
        this.store = "";
        this.location = "";
        this.phone = "";
        this.description = "";
        this.image = 0;
        this.code = 0;
    }

    public ItemProduct(String title, String store, String location, String phone, String description, int image, int code) {
        this.title = title;
        this.store = store;
        this.location = location;
        this.phone = phone;
        this.description = description;
        this.image = image;
        this.code = code;
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Product: " + title +
                "\nStore: " + store +
                "\nLocation: " + location +
                "\nPhone: " + phone +
                "\nDescription: " + description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(store);
        dest.writeString(location);
        dest.writeString(phone);
        dest.writeString(description);
        dest.writeInt(code);
    }
}
