package com.mizuho.ordermanagement.model;

public class Order {
    private long id;
    private double price;
    private char side;
    private long size;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSide(char side) {
        this.side = side;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Order(long id, double price, char side, long size){
        this.id = id;
        this.price = price;
        this.side = side;
        this.size = size;
    }

    public long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public char getSide() {
        return side;
    }

    public long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", side=" + side +
                ", size=" + size +
                '}';
    }
}
