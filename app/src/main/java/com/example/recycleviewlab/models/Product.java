package com.example.recycleviewlab.models;

public class Product {
    private int id;
    private String name;
    private float price;
    private String image;


    public Product() {
    }
    public Product( String name, float price, String image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id= id;
    }

    public void setName(String name) {
        this.name= name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
