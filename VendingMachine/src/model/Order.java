/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Dell
 */
public class Order {
    private int ID;
    private int day;
    //Key is product ID, value is product
    private Hashtable<Integer,OrderDetail> orderDetails;
    //amount of money
    private double change;
    public Order(int day) {
        this.day = day;
        orderDetails = new Hashtable<Integer,OrderDetail>();
        change=0;
    }

    public Order(int day, double amount, double change) {
        this.day=day;
        this.change = change;
        orderDetails = new Hashtable<Integer,OrderDetail>();
    }
    // key is productID, value is OrderDetail
    public Hashtable<Integer, OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public int getDay() {
        return day;
    }
    
    public double addProduct(Product product,float winRate,Product productInList){
        int a= orderDetails.size();
        int b= product.getID();
        if (orderDetails.containsKey(product.getID())){
            return orderDetails.get(product.getID()).addProduct(product, winRate, productInList);
        }else{
            Product newProduct = new Product(product.getName(), product.getPrice(), 0);
            newProduct.setID(product.getID());
            orderDetails.put(product.getID(), new OrderDetail(ID, newProduct));
            return orderDetails.get(product.getID()).addProduct(product, winRate, productInList);
        }
    }
    public double viewOrder() {
        Collection<Integer> list  = orderDetails.keySet();
        System.out.println("----------Your Order----------");
        System.out.println("No       Name        Price         Quantity       Prize      Discount");
        int no = 1;
        double total=0;
        for ( Integer key : list) {
            OrderDetail a= orderDetails.get(key);
            Product b= a.getProduct();
            System.out.printf("%-9d%-12s%.0f%14d%12d%9d\n",no,b.getName(),b.getPrice(),b.getQuantity(),a.getPrize(),a.getDiscount());
            no++;
            total+=(b.getQuantity()*b.getPrice()) - (b.getPrice()*a.getDiscount()*0.5);
        }
        System.out.printf("Total: %.0f\n", total);
        return total;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setChange(double change) {
        this.change = change;
    }

    public int getID() {
        return ID;
    }

    public double getChange() {
        return change;
    }

}
