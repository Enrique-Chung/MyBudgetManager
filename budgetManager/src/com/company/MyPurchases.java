package com.company;
/*This function deals with all the purchases made.
* */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyPurchases {
    private File file;
    private FileWriter write;
    private double balance;
    private ArrayList<Item> purchases = new ArrayList<>();



    public MyPurchases() throws IOException {

        this.file = new File("purchases.txt");
        this.write = new FileWriter(file, true);

        Scanner scanner = new Scanner(file);
        if (!scanner.hasNext()){
            write.write("Balance: 0\n" );
            write.close();
            this.balance = 0;
        }else{
            String amount = fileBalance();
            this.balance = Double.parseDouble(amount);}
    }

    /*
    * This function adds income to your balance.
    * Time complexity: O(1)
    * Parameters: null*/
    public void addIncome(){
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter income: ");
            String amount = scanner.nextLine();
            double converted = Double.parseDouble(amount);
            double current = this.getBalance();
            this.setBalance(current + converted);
            System.out.println("Income was added!");}
        catch(Exception e){
            System.out.println("Please enter a number");
        }

    }/*
     * This function gets your balance.
     * Time complexity: O(1)
     * Parameters: null*/
    public double getBalance() {
        return balance;
    }

    /*
     * This function retrieves the balance that is currently saved in the file.
     * Time complexity: O(n) where n is the number of lines in the file
     * Parameters: null*/
    private String fileBalance() throws FileNotFoundException {
        String bal = "";
        Scanner scan = new Scanner(this.file);
        while(scan.hasNext()){
            bal = scan.nextLine();
        }
        String[] x = bal.split(" ");
        String balance = null;
        for (String s : x) {
            balance = s;
        }
        return balance;
    }
    /*
     * This function gets the number of purchases.
     * Time complexity: O(1)
     * Parameters: null*/
    public int size(){
        return this.purchases.size();
    }
    /*
     * This function adds sets the balance.
     * Time complexity: O(1)
     * Parameters: null*/
    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    /*
     * This function shows all purchases of a type.
     * Time complexity: O(n) where n is the number of purchases
     * Parameters: The type of purchase you want to get*/
    public void getPurchases(String type){
        ArrayList<Item> specificPurchases = new ArrayList<>();
        if (!(type.equals("All"))){
            for (Item purchase : this.purchases) {
                if (purchase.getType().equals(type)) {
                    specificPurchases.add(purchase);
                }
            }
        }
        else{
            specificPurchases = this.purchases;
        }
        System.out.println(type + ":");
        System.out.println();
        if (specificPurchases.size() == 0){
            System.out.println("The purchase list is empty!");
        }else{
            for (Item specificPurchase : specificPurchases) {
                System.out.println(specificPurchase.getName() + " $" + specificPurchase.getCost());
            }

            System.out.println("Total sum: $" + calculateCost(specificPurchases));
        }
    }
    /*
     * This function adds an item to your purchase.
     * Time complexity: O(1)
     * Parameters: the type of the item*/
    public void addItem(String type) {
        try{
            Scanner x = new Scanner(System.in);
            System.out.println();
            System.out.println("Enter purchase name: ");
            String name = x.nextLine();
            System.out.println("Enter its price: ");
            String cost = x.nextLine();
            double convertedAmount = Double.parseDouble(cost);
            this.balance -= convertedAmount;
            this.purchases.add(new Item(cost, name, type));
            System.out.println("Purchase was added!");
            System.out.println();}
        catch(Exception e){
            System.out.println("Please enter a number as the price");

        }
    }

    /*
     * This function calculates the total amount of all your recent purchases.
     * Time complexity: O(n) where n is the number of purchases
     * Parameters: null*/
    private double calculateCost(ArrayList<Item> purchases) {
        double totalCost = 0;
        for (Item purchase : purchases) {
            String amount = purchase.getCost();
            double convertedAmount = Double.parseDouble(amount);
            totalCost += convertedAmount;
        }
        return totalCost;
    }
    /*
     * This function saves all your recent purchases into the file.
     * Time complexity: O(n) where n is the number of purchases
     * Parameters: null*/
    public void save() throws IOException {
        FileWriter writer = new FileWriter(this.file,true);
        for (Item purchase : this.purchases) {
            writer.write(purchase.getName() + ",$" + purchase.getCost() + "," +
                    purchase.getType() + "\n");
        }
        writer.write("Balance:  ");

        writer.write((Math.round(this.balance * 100.0) / 100.0) + "\n");
        writer.close();
    }

    /*
     * This function shows all purchases saved in the file.
     * Time complexity: O(n) where n is the number of purchases  in the file
     * Parameters: null*/
    public void load() throws FileNotFoundException {
        Scanner scan = new Scanner(this.file);
        while (scan.hasNext()) {
            String x = scan.nextLine();
            if (!repBalance(x)){
                System.out.println(x);
            }
        }
    }

    /*
     * This function checks if the string represents the balance.
     * Time complexity: O(1)
     * Parameters: String */
    private Boolean repBalance(String string){
        String[] x = string.split(" ");
        return x[0].equals("Balance:");
    }
    /*
     * This function calls an analysis to show results retrieved from the spendings made.
     * Time complexity: O(n) where n is the number of purchases
     * Parameters: null*/
    public void analyse() throws FileNotFoundException {
        Analyser analyse = new Analyser(this.file);
        analyse.trackspendings();

    }

}
