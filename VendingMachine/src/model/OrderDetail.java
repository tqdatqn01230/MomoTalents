/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Dell
 */
public class OrderDetail {
    private int OrderID;
    private Product product;
    private int prize;
    private int discount;
    public OrderDetail(int OrderID, Product product) {
        this.OrderID = OrderID;
        this.product = product;
        this.prize=0;
        this.discount=0;
    }
    public double addProduct(Product p, float winRate, Product productInList) {
        int rollTimes = (p.getQuantity() + (product.getQuantity() % 3)) / 3;
        for (int i = 0; i < rollTimes; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            int luckyDomain = (int) (winRate * 100);
            if (randomNumber <= luckyDomain) {
                System.out.println("Congratulation! You win one more product from us!");
                prize++;
            }
        }
        double oldTotalPrice = product.getPrice() * product.getQuantity();
        product.setQuantity(p.getQuantity() + product.getQuantity());
        System.out.println("-------"+this.OrderID);
        double discount = 0;
        if (product.getQuantity()+prize > productInList.getQuantity()) {
            int residual = product.getQuantity()+prize - productInList.getQuantity();
            prize -= residual;
            this.discount+=residual;
            discount = residual * p.getPrice() * 0.5;
            System.out.println("Sorry, we don't have enough product for you so we will give you discount instead!");
        }
        productInList.subQuantity(p.getQuantity()+prize);
        return product.getQuantity() * p.getPrice()- oldTotalPrice - discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
    
    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public void setPrize(int prize) {
        this.prize = prize;
    }
    public int getOrderID() {
        return OrderID;
    }
    public Product getProduct() {
        return product;
    }
    
    public int getPrize() {
        return prize;
    }
    
}
