package com.example.alexandrun.flowers.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alexandrun on 4/13/2018.
 */

public class Order implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("description")
    private String description;

    @SerializedName("price")
    private int price;

    @SerializedName("deliver_to")
    private String deliverTo;

    public Order() {}

    protected Order(Parcel in) {
        id = in.readInt();
        description = in.readString();
        price = in.readInt();
        deliverTo = in.readString();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    @Override
    public String toString() {
        return id + "\n" + description + "\n" + price + "\n" + deliverTo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(description);
        parcel.writeInt(price);
        parcel.writeString(deliverTo);
    }
}
