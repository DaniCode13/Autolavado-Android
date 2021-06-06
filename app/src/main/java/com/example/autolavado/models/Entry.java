package com.example.autolavado.models;

import java.io.Serializable;

public class Entry  {
//    private int id;
    private String price;
    private String description;

    public Entry() {
    }

    public Entry(String price, String description) {
        this.price = price;
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
