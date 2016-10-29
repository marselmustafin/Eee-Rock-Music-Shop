package ru.itis.kpfu.marsel_mustafin.models;

public class Product {

    private String name;
    private float price;
    private String description;


    Product(String name, float price, String description){
        this.setName(name);
        this.setPrice(price);
        this.setDescription(description);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
