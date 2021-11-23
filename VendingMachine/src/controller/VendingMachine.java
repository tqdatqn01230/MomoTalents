/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper_scanner_and_menu.Menu;
import helper_scanner_and_menu.MyScanner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Order;
import model.Revenue;
import model.Product;
import model.OrderDetail;
/**
 *
 * @author Dell
 */
public class VendingMachine {
    private ProductList productList;
    private RevenueList revenueList;
    private Menu moneyChoices;
    private double currentMoney=0;
    private List<Double> changeMoney;
    private OrderList orderList;
    private OrderDetailList detailList;
    public VendingMachine() {
        productList = new ProductList();
        revenueList=new RevenueList();
        revenueList.addRevenue();
        orderList= new OrderList();
        detailList = new OrderDetailList();
        moneyChoices = new Menu("Please Choose money face value");
        moneyChoices.add("10.000");
        moneyChoices.add("20.000");
        moneyChoices.add("50.000");
        moneyChoices.add("100.000");
        moneyChoices.add("200.000");
        moneyChoices.add("Exit");
        changeMoney= new ArrayList<Double>();
        changeMoney.add(5_000d);
        changeMoney.add(10_000d);
        changeMoney.add(20_000d);
        changeMoney.add(50_000d);
        changeMoney.add(100_000d);
        changeMoney.add(200_000d);
    }
    public void insertMoney(){
        int userChoice=0;
        do{
           userChoice=moneyChoices.getUserChoice();
           double facevalue=0;
           switch (userChoice){
               case 1: facevalue=10_000;break;
               case 2: facevalue=20_000;break;
               case 3: facevalue=50_000;break;
               case 4: facevalue=100_000;break;
               case 5: facevalue=200_000;break;
           }
            if (userChoice==moneyChoices.size()){
               if (currentMoney==0){
                   boolean choice=MyScanner.getBoolean("Are you sure you want to exit buying?");
                   if (choice) break;
                   else continue;
               }else break;
           }
           int quantity= MyScanner.getInt("Please enter number of banknotes:", 1,1000000);
           currentMoney+= quantity*facevalue;
            System.out.printf("Your money: %.0f\n",currentMoney);
        }while (true);
        if (currentMoney>0) chooseProducts();
    }
    public void goToNextDay(){
        revenueList.addRevenue();
    }
    public void updateProduct(){
        productList.readFromFile();
    }
    public void chooseProducts(){
        Revenue currentDay= revenueList.get(revenueList.size()-1);
        Order order = new Order(currentDay.getDay());
        order.setID(orderList.lastID());
        do{
            boolean check = MyScanner.getBoolean("Do you want to continue buying?");
            if (!check) break;
            productList.viewAllProduct();
            System.out.printf("Your money: %.0f\n",currentMoney);
            int ID= MyScanner.getInt("Enter ID of product you want: ", 0,productList.size());
            if (ID==0){
                boolean confirm = MyScanner.getBoolean("You really want to exit buying?");
                if (confirm) break;
                else continue;
            }
            Product product = productList.getProduct(ID);
            if (product.getQuantity()==0){
                System.out.println("Sorry, this product is out of stock!");
                continue;
            }           
            if (product.getPrice()>currentMoney){
                System.out.println("Sorry, you don't have enough money");
                continue;
            }
            //Limit number of product customer can buy depend on their money
            int maxQuan =(int)(currentMoney/product.getPrice()); 
            int quantity= MyScanner.getInt("Enter quantity:", 1,maxQuan);
            
            Product p = new Product(product.getID(),product.getName(), product.getPrice(), quantity);
            double pay=order.addProduct(p,currentDay.getWinRate() , product);
            currentMoney-=pay;
        }while (true);
        if (order.getOrderDetails().size()>0){
            double total=order.viewOrder();
            order.setChange(currentMoney);
            orderList.addOrder(order);
            for (OrderDetail a:order.getOrderDetails().values()){
                detailList.addOrderDetail(a);
            }
            revenueList.updateBudget(total);
        }
        if (currentMoney>0) {
            refund();
        }
    }
    private void refund(){
        System.out.println("Here your refund: ");
            int i = changeMoney.size()-1;
            while (currentMoney>0){
                double money = changeMoney.get(i);
                if (currentMoney>=money){
                    int quantity = (int) (currentMoney/money);
                    System.out.printf("Face value:%.0f   Quantity:%d\n",money,quantity);
                    currentMoney-=money*quantity;
                    i--;
                }else i--;
            }
    }
    
}
