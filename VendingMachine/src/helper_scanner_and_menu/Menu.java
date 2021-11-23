package helper_scanner_and_menu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
import java.util.*;

public class Menu extends ArrayList<String> {

    String message = "";
    public static Scanner scanner = new Scanner(System.in);

    public Menu(String message) {
        super();
        this.message = message;
    }

    public int getUserChoice() {
        //Print User Choices
        System.out.println(message);
        for (int i = 0; i < this.size(); i++) {
            System.out.println((i + 1) + " - " + this.get(i));
        }
        //Get User Choice
        int choice = -1;
        do {
            System.out.print("Input your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (!(choice <= this.size() && choice >= 1)) {
                    System.out.println("Please enter value between 1 and " + this.size());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!(choice <= this.size() && choice >= 1));
        return choice;
    }
}
