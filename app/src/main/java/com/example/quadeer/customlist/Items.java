package com.example.quadeer.customlist;

public class Items {

    String product, price, quantity;


    public Items(String product, String price, String quantity) {
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
}
