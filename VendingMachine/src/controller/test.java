/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Random;

/**
 *
 * @author Dell
 */
public class test {
    public static void main(String[] args){
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
        String fileName = currentWorkingDir.normalize().toString();
        System.out.println(fileName);
        double a=100;
        System.out.printf("%-1.0f %.0f\n",a,a);
        Hashtable<Integer,Integer> list=  new Hashtable<>();
        list.put(1, 2);
        if (list.containsKey(1)){
            System.out.println(list.get(1));
        }
        int rollTimes=100;
        float winRate=0.1f;
        for (int i = 0; i < rollTimes; i++) {
            Random random = new Random();
            int randomNumber = random.nextInt(99) + 1;
            int luckyDomain = (int) (winRate * 100);
            System.out.println(randomNumber);
            if (randomNumber <= luckyDomain) {
                System.out.println("Congratulation! You win one more product from us!");
            }
        }
        while (true){
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1;
            if (randomNumber==100) break;
        }
    }
}
