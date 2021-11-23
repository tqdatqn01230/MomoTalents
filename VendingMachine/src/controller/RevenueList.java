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
import model.Revenue;
/**
 *
 * @author Dell
 */
public class RevenueList extends ArrayList<Revenue>{
    public void addRevenue(){
        Revenue revenue = new Revenue(0, 0.1f);
        if (this.size()==0){
            revenue.setDay(1);
        }else{
            Revenue previousRevenue = this.get(this.size()-1);
            int nextDay= previousRevenue.getDay()+1;
            revenue.setDay(nextDay);
            if (previousRevenue.getBudget()<50000){
                float winRate= previousRevenue.getWinRate()*1.5f;
                if (winRate>1) winRate=1;
                revenue.setWinRate(winRate);
            }
        }
        this.add(revenue);
        if (this.size()>=1) writeToFile();
    }
    public Revenue getCurrentRevenue(){
        return this.get(this.size()-1);
    }
    //Update budget of current Day
    public void updateBudget(double income){
        Revenue currentDay= this.get(this.size()-1);
        currentDay.addBudget(income);
        writeToFile();
    }
    private void writeToFile(){
        Path currentWorkingDir = Paths.get("").toAbsolutePath();
        String fileName = currentWorkingDir.normalize().toString() + "\\src\\file\\revenueList.txt";
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try{
            fileWriter = new FileWriter(fileName);
            printWriter = new PrintWriter(fileWriter);
            for (int i=0;i<this.size();i++){
                Revenue a = this.get(i);
                printWriter.println(a.getDay()+"; "+a.getBudget()+"; "+a.getWinRate());
            }
            fileWriter.close();
            printWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(RevenueList.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
