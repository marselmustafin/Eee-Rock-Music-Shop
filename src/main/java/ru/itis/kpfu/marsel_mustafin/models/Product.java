package ru.itis.kpfu.marsel_mustafin.models;

public class Product{

    private int id;
    private String bandName;
    private String albumName;
    private int price;
    private String description;
    private int quantity;

    public Product(String bandName, String albumName, String description, int quantity, int price) {
        this.bandName = bandName;
        this.albumName = albumName;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getBandName() {
        return bandName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }
}

