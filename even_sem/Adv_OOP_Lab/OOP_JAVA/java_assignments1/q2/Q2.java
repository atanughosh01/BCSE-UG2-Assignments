/*Assignment 2:
Design a system for the following scenario:
1. An item list contains item code, name, rate, and quantity for several items.
2. Whenever a new item is added in the list uniqueness of item code is to be checked. Register 
a new product with its price.
3. Time to time rate of the items may change.
4. Whenever an item is issued or received existence of the item is checked and quantity is updated.
5. In case of issue, availability of quantity is also to be checked.
6. User may also like to know price/quantity available for an item.
7. Find how many items cost more than a given amount. The amount will be a parameter.
8. Remember that the methods have to return an error code if for example an invalid item code is given
 
 NOTE:
● The system should be maintained by two types of user, one is Stock entry operator (SEO) and other
 is Shopkeeper (SK) and SEO will be the first operator in default case.
● The SEO primarily maintain first 3 operations but SEO users can also maintain all 
operations (1 to 8)
● SK users can only operates on 4 to 8.
● System should be used for a specific shop type. Ex. Electronics, Book, Grocer etc.. You can design 
your system for any one.
● Item Code should be auto generated that includes item name and entry order(1,2,3...)
Example: for Electronics shop
Item name entry order Item Code
Laptop 3 LAP003
Mobile 2 MOB002
Monitor 10 MON010
Mouse 1 MOU001 */

// package java_assignments1.q2;

import java.io.*;
import java.util.*;
import java.util.Scanner;

class Item {
    // Data members
    private static int id = 0;
    private String code;
    private String name;
    private double rate;
    private int qty;
    private String strcode;
    private int numcode;

    // Constructor
    public Item(String nm, double r, int q) {
        name = nm;
        rate = r;
        qty = q;
        strcode = name.substring(0, 3);
        strcode = strcode.toUpperCase();
        String s = String.format("%03d", qty);
        code = strcode + s;
        id++;
    }

    // Set rate
    public void setRate(double r) {
        rate = r;
    }

    // Set quantity
    public void setQty(int q) {
        qty = q;
    }

    // Get quantity
    public int getQty() {
        return qty;
    }

    // Get rate
    public double getRate() {
        return rate;
    }

    // Get code
    public String getCode() {
        return code;
    }

    // Function to display item
    public void display() {
        System.out.println("Code :" + code + "\nName: " + name + "\nRate: " + rate + "\nQuantity: " + qty);
    }
}

class ItemList {
    ArrayList<Item> itemList;

    // Constructor
    public ItemList() {
        itemList = new ArrayList<>();
    }

    // Function to add an item
    public void addItem() {
        Scanner sc = new Scanner(System.in);
        String name;
        System.out.println("Enter name of item");
        name = sc.nextLine();
        double rate;
        int qty;

        do {
            System.out.println("Enter rate");
            rate = sc.nextDouble();
            if (rate <= 0)
                System.out.println("Invalid rate");
        } while (rate <= 0);

        do {
            System.out.println("Enter qty");
            qty = sc.nextInt();
            if (qty <= 0)
                System.out.println("Invalid quantity");
        } while (qty <= 0);

        Item i = new Item(name, rate, qty);
        i.display();
        itemList.add(i);
    }

    // Function to check if an item is in list
    public int check(String id) {
        int pos = -1;
        for (Item i : itemList) {
            if (i.getCode().equals(id))
                return ++pos;
            pos++;
        }
        return -1;
    }

    // Function to change rate
    public void changeRate() {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        String code;
        int pos;
        double rate;
        do {
            System.out.println("Enter item code");
            code = sc.nextLine();
            pos = check(code);
            if (pos == -1)
                System.out.println("Invalid code");
        } while (pos == -1);

        do {
            System.out.println("Enter new rate");
            rate = sc.nextDouble();
            if (rate <= 0)
                System.out.println("Invalid rate");
        } while (rate <= 0);

        Item i = itemList.get(pos);
        i.setRate(rate);
        itemList.set(pos, i);
    }

    // Function to change quantity
    public void changeQty() {
        Scanner sc = new Scanner(System.in);
        String code;
        int pos, qty;
        do {
            System.out.println("Enter item code");
            code = sc.nextLine();
            pos = check(code);
            if (pos == -1)
                System.out.println("Invalid code");
        } while (pos == -1);

        do {
            System.out.println("Enter new quantity");
            qty = sc.nextInt();
            if (qty <= 0)
                System.out.println("Invalid rate");
        } while (qty <= 0);

        Item i = itemList.get(pos);
        i.setQty(qty);
        itemList.set(pos, i);
    }

    // Function to issue
    public void issue() {
        Scanner sc = new Scanner(System.in);
        String code;
        int pos, qty;

        System.out.println("Enter item code");
        code = sc.nextLine();
        pos = check(code);
        if (pos == -1)
            System.out.println("Invalid code");

        // while(pos==-1);

        System.out.println("Enter quantity");
        qty = sc.nextInt();
        if (qty <= 0)
            System.out.println("Invalid quantity");

        Item i = itemList.get(pos);
        if (i.getQty() < qty)
            System.out.println("Insufficient quantity");
        else {
            System.out.println("Rate: " + i.getRate() + "\nQuantity: " + qty + "\nTotal cost: " + (qty * i.getRate()));
            i.setQty(i.getQty() - qty);
            itemList.set(pos, i);
        }
    }

    // Function to get availability
    public void viewItem() {
        Scanner sc = new Scanner(System.in);
        String code;
        int pos;
        do {
            System.out.println("Enter item code");
            code = sc.nextLine();
            pos = check(code);
            if (pos == -1)
                System.out.println("Invalid code");
        } while (pos == -1);
        Item i = itemList.get(pos);
        i.display();
    }

    // Find how many items cost more than an amt
    public void moreCount() {
        double rate;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter amt");
            rate = sc.nextDouble();
            if (rate <= 0)
                System.out.println("Invalid amt");
        } while (rate <= 0);
        int count = 0;
        for (Item i : itemList) {
            if (i.getRate() > rate)
                count++;
        }
        System.out.println("No of items above " + rate + " is " + count);

    }

}

class Q2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int ch1, ch2, ch3;
        ItemList i = new ItemList();
        do {
            System.out.println("1. SEO\n2. Shopkeeper\n3.Exit");
            System.out.print("Enter choice :");
            ch1 = sc.nextInt();
            switch (ch1) {
            case 1:
                // Functions for SEO
                do {
                    System.out.println(
                            "1. Add new item\n2. Change rate of existing item\n3. Update quantity\n4. Issue an item\n5. View price or quantity of an item\n6. Find how many items cost more than a given amount\n7. Go back");
                    System.out.print("Enter choice :");
                    ch2 = sc.nextInt();

                    switch (ch2) {
                    case 1:
                        i.addItem();
                        break;

                    case 2:
                        i.changeRate();
                        break;

                    case 3:
                        i.changeQty();
                        break;

                    case 4:
                        i.issue();
                        break;

                    case 5:
                        i.viewItem();
                        break;

                    case 6:
                        i.moreCount();
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;

                    }
                } while (ch2 != 7);
                break;

            case 2:
                // For SK
                do {
                    System.out.println(
                            "1. Update quantity\n2. Issue an item\n3. View price or quantity of an item\n4. Find how many items cost more than a given amount\n5. Go back");
                    System.out.print("Enter choice :");
                    ch2 = sc.nextInt();

                    switch (ch2) {
                    case 1:
                        i.changeQty();
                        break;

                    case 2:
                        i.issue();
                        break;

                    case 3:
                        i.viewItem();
                        break;

                    case 4:
                        i.moreCount();
                        break;

                    default:
                        System.out.println("Invalid choice");
                        break;

                    }
                } while (ch2 != 5);

                break;

            default:
                System.out.println("invalid choice");
            }

        } while (ch1 != 3);
    }
}
