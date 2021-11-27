/* 1. Design a BankAcct class with account number, balance and interest rate
 * as attribute. Interest rate is same for all account. Support must be there to
 * initialize, change and display the interest rate. Also supports are to be
 * there to return balance and calculate interest.
 */

//package java_assignment2.q1

// importing required packages
import java.util.*;
import java.io.*;

// designing the class BankAcct
class BankAcct {
    // defining necessary attributes
    protected int accountNumber;
    protected double balance;
    protected static double interestRate;

    // constructor to initialize the attributes
    public BankAcct() {
        accountNumber = 0;
        balance = 0;
        interestRate = 0;
    }

    // overloaded constructor to initialise attributes during creation of objects
    public BankAcct(int accNo, double bal, double intR) {
        accountNumber = accNo;
        balance = bal;
        interestRate = intR;
    }

    // method to get account details
    public void getAccountDetails() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Bank Account Number : ");
        accountNumber = sc.nextInt();
        System.out.print("Enter Current Bank Balance : ");
        balance = sc.nextDouble();
        System.out.println("Account Details are Successfully Entered");
    }

    // static method to get interest rate
    public static void getInitialInterestRate() {
        // defined static method because it needs to be modified for the entire class

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Interest Rate (in %) : ");
        interestRate = sc.nextDouble();
        System.out.println("Interest Rate is Successfully Entered");
    }

    // method to modify previously entered interest rate
    public static void getModifiedInterestRate() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new Interest Rate (in %) : ");
        interestRate = sc.nextDouble();
        System.out.println("Interest Rate is Successfully Modified");
    }

    // method to display current interest rate
    public static void displayCurrentInterestRate() {
        System.out.println("Current Interest Rate is " + interestRate + " %");
    }

    // method to display updated balance
    public double dsplayCalculatedBalance(int year, int months) {
        double fractionalYear = (float) months / (float) 12;
        double time = year + fractionalYear;
        double interestAmount = (balance * time * interestRate) / 100;
        System.out.println("Interest Amount over total time period is " + interestAmount);
        return balance + interestAmount;
    }
}

// desiging the class with main() method to implement the functionalities
class Q1 {
    public static void main(String[] args) {
        BankAcct ba = new BankAcct();
        Scanner sc = new Scanner(System.in);
        while (true) {
            int choice;
            // Creating the menu
            System.out.println("_____________________________________________");
            System.out.println(" 1) Enter Account Details");
            System.out.println(" 2) Enter Interest Rate");
            System.out.println(" 3) Modify Previously Entered Interest Rate");
            System.out.println(" 4) Display Current Interest Rate");
            System.out.println(" 5) Calculate Balance and Total Interest");
            System.out.println(" 6) Exit");
            System.out.println("_____________________________________________");
            System.out.print("\nEnter Your Choice : ");

            choice = sc.nextInt();
            // switch statement to take choice from user and execute specific method
            switch (choice) {
                case 1:
                    ba.getAccountDetails();
                    break;
                case 2:
                    BankAcct.getInitialInterestRate();
                    break;
                case 3:
                    BankAcct.getModifiedInterestRate();
                    break;
                case 4:
                    BankAcct.displayCurrentInterestRate();
                    break;
                case 5:
                    System.out.print("Enter Number of Years : ");
                    int year = sc.nextInt();
                    System.out.print("Enter Number of Months : ");
                    int months = sc.nextInt();
                    double balance = ba.dsplayCalculatedBalance(year, months);
                    System.out.println("Total Amount of Balance is " + balance);
                    break;
                case 6:
                    System.exit(0);
                default:
            }
        }
    }
}
