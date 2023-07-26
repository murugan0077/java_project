package com.zoho.Mcart;

class Product {
    private int product_id;
    private String brand_name;
    private String product_name;
    private int quantity;
    private double price;

    public Product(int product_id, String brand_name,  String product_name, int quantity, double price) {
        this.product_id = product_id;
        this.brand_name = brand_name;
        this.product_name = product_name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

   

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return ""+String.format("\t\t%-5s %-15s %-30s %-8s %-10s",product_id,brand_name,product_name,quantity,price);
    }

}
