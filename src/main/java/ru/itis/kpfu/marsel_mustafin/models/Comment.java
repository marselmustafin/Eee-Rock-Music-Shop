package ru.itis.kpfu.marsel_mustafin.models;

public class Comment {

    private int id;
    private String author;
    private String text;
    private int productId;

    public Comment(String author, String text, int productId){
        this.author = author;
        this.text = text;
        this.productId = productId;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public int getProductId() {
        return productId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
