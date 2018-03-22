package com.iteso.pdm_scrollabletabs.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hsm-y on 20/03/2018.
 */

public class Store implements Parcelable{

    private int id;
    private String name;
    private String phone;
    private int thumbnail;
    private double latitude;
    private double longitude;
    private City city;

    public Store() {
        this.id = 0;
        this.name = "";
        this.phone = "";
        this.thumbnail = 0;
        this.latitude = 0;
        this.longitude = 0;
        this.city = null;
    }

    protected Store(Parcel in) {
        id = in.readInt();
        name = in.readString();
        phone = in.readString();
        thumbnail = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(phone);
        dest.writeInt(thumbnail);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return name;
    }
}
