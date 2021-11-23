/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine;

import helper_scanner_and_menu.Menu;
import controller.VendingMachine;
/**
 *
 * @author Dell
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu("-------------Vending Machine-------------");
        menu.add("Buy Product");
        menu.add("Go to next day");
        menu.add("Update Product");
        menu.add("Exit");
        VendingMachine machine = new VendingMachine();
        int userChoice=0;
        do{
            userChoice=menu.getUserChoice();
            switch(userChoice){
                case 1: machine.insertMoney();break;
                case 2: machine.goToNextDay();break;
                case 3: machine.updateProduct();
            }
        }while (userChoice<menu.size());
    }
    
}
