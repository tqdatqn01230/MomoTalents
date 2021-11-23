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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyScanner {

    public static Scanner scanner = new Scanner(System.in);
    public static boolean getBoolean(String message) {

        String input;
        do {
            //Print Message
            System.out.print(message + "(T/F, Y/N, 1/0): ");
            input = scanner.nextLine().trim().toUpperCase();
            if (input.length()==0) continue;
            char character = input.charAt(0);
            //Check for Right character
            if (character == 'T' || character == 'Y' || character == '1') {
                return true;
            } else if (character == 'F' || character == 'N' || character == '0') {
                return false;
            }
            System.out.println("Please enter right format!");
        } while (true);
    }

    public static double getDouble(String message, double min, double max) {
        double enterNumber = -1;
        do {
            System.out.print(message);
            try {
                //Read number from Console
                enterNumber = Double.parseDouble(scanner.nextLine().trim());
                //Wrong Value of Number
                if (enterNumber < min || enterNumber > max) {
                    System.out.println("Value enter must between " + min + " and " + max);
                }
            } catch (NumberFormatException exception) {
                System.out.println(exception);
            }
            //x=Double.parseDouble(scanner.nextLine().trim());
        } while (enterNumber < min || enterNumber > max);
        return enterNumber;
    }

    public static double getDouble(String message, double max) {
        return getDouble(message, 0, max);
    }

    public static int getInt(String message, int min, int max) {
        int enterNumber = -1;
        do {
            System.out.print(message);
            try {
                //Read number from Console
                enterNumber = Integer.parseInt(scanner.nextLine().trim());
                //Wrong Value of Number
                if (enterNumber < min || enterNumber > max) {
                    System.out.println("Value enter must between " + min + " and " + max);
                }
            } catch (NumberFormatException exception) {
                System.out.println(exception);
            }
        } while (enterNumber < min || enterNumber > max);
        return enterNumber;
    }

    public static int getInt(String message, int max) {
        return getInt(message, 0, max);
    }

    public static String getNonBlankString(String message, int minLength) {
        
        String output = "";
        do {
            //Read String from Console
            System.out.print(message);
            output = scanner.nextLine().trim();
            //Enter Blank String
            if (output.length() <= minLength) {
                System.out.println("Please Enter String length larger than " + minLength);
            }
        } while (output.length() <= minLength);
        return output;
    }

    public static String getUserNameAndPassword(String message, int minLength) {
        
        String output = "";
        do {
            //Read String from Console
            System.out.print(message);
            output = scanner.nextLine().trim();
            //Enter Blank String
            if (output.length() <= minLength || output.contains(" ")) {
                System.out.println("Please Enter String length larger than " + minLength + " and no space");
            }
        } while (output.length() <= minLength || output.contains(" "));
        return output;
    }
    public static Date getDate(String message) {
        Date date = null;
        do {
            System.out.print(message);
            try {
                date = new SimpleDateFormat("MM-dd-yyyy").parse(scanner.nextLine());
            } catch (ParseException ex) {
                Logger.getLogger(MyScanner.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (date == null);
        return date;
    }
}
