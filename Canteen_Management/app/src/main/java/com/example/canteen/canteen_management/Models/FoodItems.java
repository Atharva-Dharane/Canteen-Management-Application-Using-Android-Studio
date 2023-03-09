package com.example.canteen.canteen_management.Models;

import java.io.Serializable;

public class FoodItems implements Serializable {
    String name;
    String price;

    boolean available;

    public FoodItems(){

    }

    public FoodItems(String name, String price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
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
}
