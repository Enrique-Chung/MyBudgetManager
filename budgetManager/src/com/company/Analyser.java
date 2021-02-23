package com.company;
/*This class is used to create analysis on your purchases
* it can be extended by adding new methods that can be used to analyse the file*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Analyser {
    private File file;
    public Analyser(File file){
        this.file = file;
    }
    private Boolean repBalance(String string){
        String[] x = string.split(" ");
        return x[0].equals("Balance:");
    }


    /*
    This method calculates all the spendings made and finds out how much you've spent on each category in spendings
    Time complexity: O(n) where n is the number of purchases
    Parameters: Null
    */
    private ArrayList<Double> getSpendings() throws FileNotFoundException {
        ArrayList<Double> spendings = new ArrayList<>();
        Scanner scan = new Scanner(this.file);
        double amount;
        String type;
        double totalSpent = 0;
        double totalFood = 0;
        double totalEssentials = 0;
        double totalEntertainment = 0;
        double totalOther = 0;
        double totalClothes = 0;
        while (scan.hasNext()) {
            String x = scan.nextLine();
            if (!repBalance(x)){
                amount = getCost(x);
                type = getType(x);
                totalSpent += amount;
                switch (type){
                    case "Food":
                        totalFood += amount;
                        break;
                    case "Essentials":
                        totalEssentials += amount;
                        break;
                    case "Entertainment":
                        totalEntertainment += amount;
                        break;
                    case "Clothes":
                        totalClothes += amount;
                        break;
                    case "Other":
                        totalOther += amount;
                        break;
                    default:
                }
            }
        }
        spendings.add(totalSpent);
        spendings.add(totalFood);
        spendings.add(totalEssentials);
        spendings.add(totalEntertainment);
        spendings.add(totalClothes);
        spendings.add(totalOther);
        return spendings;
    }
    /*
    This method interprets the values gathered from the getSpendings methods and reads them out to the user
    Time complexity: O(n) where n is the number of purchases
    Parameters: Null
    */

    public void trackspendings() throws FileNotFoundException {

        ArrayList<Double> spendings = getSpendings();
        double totalSpent = spendings.get(0);
        double foodPercentage = (spendings.get(1)/totalSpent) * 100 ;
        double essentialPercentage = (spendings.get(2)/totalSpent) * 100;
        double entertainmentPercentage = (spendings.get(3)/totalSpent) * 100;
        double clothesPercentage = (spendings.get(4)/totalSpent) * 100;
        double otherPercentage = (spendings.get(5)/totalSpent) * 100;
        System.out.println((Math.round(foodPercentage * 100.0) / 100.0) + "% of your spendings were spent on food");
        System.out.println((Math.round(essentialPercentage * 100.0) / 100.0) + "% of your spendings were spent on essentials");
        System.out.println((Math.round(entertainmentPercentage * 100.0) / 100.0) + "% of your spendings were spent on entertainment");
        System.out.println((Math.round(clothesPercentage * 100.0) / 100.0) + "% of your spendings were spent on clothes");
        System.out.println((Math.round(otherPercentage * 100.0) / 100.0) + "% of your spendings were spent on other");
        System.out.println("In total you have spent $" + totalSpent);

    }
    /*
        This method gets the type of an item in the file.
        Time complexity: O(1)
        Paramters: The item represented as a string
        */
    public String getType( String item){
        String[] splitItem = item.split(",");
        return splitItem[2];

    }
    /*
       This method gets the cost of an item in the file.
       Time complexity: O(1)
       Paramters: The item represented as a string
            */
    public double getCost(String item){
        String[] splitItem = item.split(",");
        String cost = splitItem[1].substring(1);
        return Double.parseDouble(cost);
    }
}
