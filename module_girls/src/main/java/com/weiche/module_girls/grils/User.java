package com.weiche.module_girls.grils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ${chewei} on 2018/7/10.
 * params:2018/7/10
 */

public class User implements Parcelable {
    private String name;

    public User(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected User(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
    }

    public void readFromParcel(Parcel parcel){
        name = parcel.readString();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
