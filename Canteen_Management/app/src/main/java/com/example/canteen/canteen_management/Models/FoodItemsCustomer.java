package com.example.canteen.canteen_management.Models;

import android.os.Parcelable;

import java.io.Serializable;

public class FoodItemsCustomer implements Serializable {
    String name;
    String price;
    boolean available;
    int count;

    public FoodItemsCustomer(){

    }

    public FoodItemsCustomer(String name, String price, boolean available, int count) {
        this.name = name;
        this.price = price;
        this.available = available;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
