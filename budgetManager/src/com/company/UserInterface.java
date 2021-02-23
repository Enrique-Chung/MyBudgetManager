package com.company;
import java.io.IOException;
import java.util.Scanner;

/* This class runs the program and acts as the user interface */
public class UserInterface {

    /*
     * This function shows what type of items you can view.
     * Time complexity: O(1)
     * Parameters: null*/
    private static void viewingOptions(){
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Essentials");
        System.out.println("5) Other");
        System.out.println("6) All");
        System.out.println("7) Back");
    }
    /*
     * This function shows what type of actions you can take.
     * Time complexity: O(1)
     * Parameters: null*/
    private static void menuOptions() {
        System.out.println("Choose your action: ");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show recent list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load saved purchases");
        System.out.println("7) Analyse");
        System.out.println("0) Exit");
    }

    /*
     * This function shows what all Item types.
     * Time complexity: O(1)
     * Parameters: null*/
    private static void typesOfPurchases() {

        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Essentials");
        System.out.println("5) Other");
        System.out.println("6) Back");
    }

    public static void run() throws IOException {
        MyPurchases purchases = new MyPurchases();
        menuOptions();
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        while (!(response.equals("0"))) {
            System.out.println();
            if (response.equals("1")) {
                purchases.addIncome();
            } else if (response.equals("2")) {
                System.out.println("Choose the type of purchase");
                typesOfPurchases();
                String resp = scanner.nextLine();
                String type;
                while (!(resp.equals("6"))) {
                    switch (resp) {
                        case "1":
                            type = "Food";
                            break;
                        case "2":
                            type = "Clothes";
                            break;
                        case "3":
                            type = "Entertainment";

                            break;
                        case "4":
                            type = "Essentials";
                            break;

                        case "5":
                            type = "Other";
                            break;
                        default:
                            System.out.println();
                            type = "wrong";
                            System.out.println("Invalid command, try again: ");

                            break;
                    }
                    if (!type.equals("wrong")) {
                        purchases.addItem(type);
                    }
                    typesOfPurchases();
                    resp = scanner.nextLine();
                }

            } else if (response.equals("3")) {
                if (purchases.size() == 0) {
                    System.out.println("The purchase list is empty!");
                } else {
                    viewingOptions();
                    String ans = scanner.nextLine();
                    String type = "";
                    while (!(ans.equals("7"))) {
                        switch (ans) {
                            case "1":
                                type = "Food";

                                break;
                            case "2":
                                type = "Clothes";

                                break;
                            case "3":
                                type = "Entertainment";

                                break;
                            case "4":
                                type = "Essentials";

                                break;
                            case "5":
                                type = "Other";
                                break;

                            case "6":
                                type = "All";
                                break;
                            default:
                                System.out.println();
                                System.out.println("Invalid command, try again: ");
                                break;
                        }
                        purchases.getPurchases(type);
                        System.out.println();
                        viewingOptions();
                        ans = scanner.nextLine();
                    }
                }
            } else if (response.equals("4")) {
                System.out.println("Balance: $" + purchases.getBalance());

            }else if (response.equals("5")){

                purchases.save();
                System.out.println("Purchases were saved!");

            } else if( response.equals("6")){
                purchases.load();
                System.out.println("Purchases were loaded!");

            } else if (response.equals("7")) {
                purchases.analyse();


            }else{
                System.out.println("Invalid Command");

            }
            System.out.println();
            menuOptions();
            response = scanner.nextLine();

        }
        System.out.println();
        System.out.println("Bye!");
    }
}
