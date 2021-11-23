/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author Dell
 */
public class ProductList extends ArrayList<Product> {

    public ProductList() {
        super();
        readFromFile();
    }

    public void addProduct(Product product) {
        int size = this.size();
        //Set ID of new Product is the id of last product+1, if list is empty then ID=1
        if (size == 0) {
            product.setID(1);
        } else {
            int ID = this.get(size - 1).getID() + 1;
            product.setID(ID);
        }
        this.add(product);
    }

    public void readFromFile() {
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
        String fileName = currentWorkingDir.normalize().toString() + "\\src\\file\\productList.txt";
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        String line = null;
        StringTokenizer stringTokenizer = null;
        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);
            //read lines from file
            while ((line = bufferedReader.readLine()) != null) {
                line.trim();
                if (line.length() > 0) {
                    stringTokenizer = new StringTokenizer(line, ";");
                    String name = stringTokenizer.nextToken();
                    double price = Double.parseDouble(stringTokenizer.nextToken().trim());
                    int quantity= Integer.parseInt(stringTokenizer.nextToken().trim());
                    Product product = new Product(name, price,quantity);
                    addProduct(product);
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException ex) {
            Logger.getLogger(ProductList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Product getProduct(int ID){
        for (Product product :this){
            if (product.getID()==ID) return product;
        }
        return null;
    }
    public void buyProduct(int ID, int quantity){
        Product product =getProduct(ID);
        product.setQuantity(product.getQuantity()-quantity);
    }
    public void viewAllProduct(){
        System.out.println("-------Choose your product-------");
        System.out.println("ID       Name        Price         Quantity");
        for (Product product:this){
            System.out.printf("%-9d%-12s%.0f%14d\n",product.getID(),product.getName(),product.getPrice(),product.getQuantity());
        }
    }
}
