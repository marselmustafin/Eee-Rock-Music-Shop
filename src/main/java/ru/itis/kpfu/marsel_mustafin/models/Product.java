package ru.itis.kpfu.marsel_mustafin.models;

public class Product {

    private int id;
    private String bandName;
    private String albumName;
    private int price;
    private String description;
    private int quantity;
    private int imgId;

    public Product(String bandName, String albumName, String description, int quantity, int price, int imgId) {
        this.setBandName(bandName);
        this.setAlbumName(albumName);
        this.setDescription(description);
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setImgId(imgId);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getId() {
        return id;
    }

    public String getBandName() {
        String bandName = this.bandName;
        return bandName;
    }

    public String getAlbumName() {
        String albumName = this.albumName;
        return albumName;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        String description = this.description;
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getImgId() {
        return imgId;
    }
}

