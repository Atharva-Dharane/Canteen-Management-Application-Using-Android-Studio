package com.example.canteen.canteen_management.Models;

public class Orders {
    String foodname;
    int foodprice;
    int foodcount;
    String tablenumber;
    String id;
    public Orders(){}

    public Orders(String foodname, int foodprice, int foodcount, String tablenumber) {
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.foodcount = foodcount;
        this.tablenumber = tablenumber;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(int foodprice) {
        this.foodprice = foodprice;
    }

    public int getFoodcount() {
        return foodcount;
    }

    public void setFoodcount(int foodcount) {
        this.foodcount = foodcount;
    }

    public String getTablenumber() {
        return tablenumber;
    }

    public void setTablenumber(String tablenumber) {
        this.tablenumber = tablenumber;
    }
}
