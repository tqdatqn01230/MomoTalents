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
import model.OrderDetail;
import model.Product;
/**
 *
 * @author Dell
 */
public class OrderDetailList extends ArrayList<OrderDetail>{
    public void addOrderDetail(OrderDetail a){
        this.add(a);
        writeToFile();
    }
    private void writeToFile(){
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
        String fileName = currentWorkingDir.normalize().toString() + "\\src\\file\\orderDetailList.txt";
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try{
            fileWriter = new FileWriter(fileName);
            printWriter= new PrintWriter(fileWriter);
            for (OrderDetail a:this){
                printWriter.println(a.getOrderID()+"; "+a.getProduct().getName()+"; "+a.getProduct().getPrice()+"; "
                        +a.getProduct().getQuantity()+"; "+a.getPrize()+"; "+a.getDiscount());
            }
            fileWriter.close();
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(OrderList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
