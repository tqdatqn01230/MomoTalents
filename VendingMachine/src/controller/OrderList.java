/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;
import model.Product;
/**
 *
 * @author Dell
 */
public class OrderList extends ArrayList<Order>{
    public void addOrder(Order order){
        if (this.size()==0){
            order.setID(1);        
        }else{
            order.setID(this.get(this.size()-1).getID()+1);
        }
        this.add(order);
        writeToFile();
    }
    public int lastID(){
        if (this.size()==0) return 1;
        else  return this.size()+1;
    }
    private void writeToFile(){
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
        String fileName = currentWorkingDir.normalize().toString() + "\\src\\file\\orderList.txt";
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try{
            fileWriter = new FileWriter(fileName);
            printWriter= new PrintWriter(fileWriter);
            for (Order a:this){
                printWriter.println(a.getID()+"; "+a.getDay()+"; "+a.getChange()+"; "+a.getOrderDetails().size());
            }
            fileWriter.close();
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(OrderList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
