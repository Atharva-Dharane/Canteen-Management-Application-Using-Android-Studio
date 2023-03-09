package com.example.canteen.canteen_management.Models;

public class Review {

   String message;
    public Review(){


    }

    public Review(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
