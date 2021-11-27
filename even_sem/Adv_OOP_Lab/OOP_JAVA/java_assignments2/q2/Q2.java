/* 2. Design a Metric class that supports Kilometre to Mile conversion 
with distance in Kilometre as argument and Mile to Kilometre conversion
with distance in mile as argument. Assume, one Mile equals 1.5 Kilometre */

//package java_assignment2.q2

// importing required packages
import java.util.*;
import java.io.*;

//wrapper class to hold the value in Kilometre
class Kilometre {
    // attribute to hold the value in Kilometre
    protected double distance;

    // constructor to initialize the distance to 'd' instance of 'Double' wrapper
    // class
    public Kilometre(Double d) {
        distance = d;
    }

    // public method to retrive the value
    public double returnDistance() {
        return distance;
    }

    // using toString() method to convert the 'distance' into String
    public String toString() {
        return Double.toString(distance);
    }
}

// wrapper class to hold the value in Miles
class Mile {
    // attribute to hold the value in Mile
    protected double distance;

    // constructor to initialize the distance to 'd' instance of 'Double' wrapper
    // class
    public Mile(Double d) {
        distance = d;
    }

    // public method to retrive the value
    public double returnDistance() {
        return distance;
    }

    // using toString() method to convert the 'distance' into String
    public String toString() {
        return Double.toString(distance);
    }
}

// designing the class Metric
class Metric {
    // initializing necessary attributes
    protected Kilometre km = null;
    protected Mile mil = null;

    // constructor to initialise the Metrics object with kilometer value
    public Metric(Kilometre obj) {
        km = new Kilometre(obj.returnDistance());
    }

    // constructor to initialise the Metrics object with Miles value
    public Metric(Mile obj) {
        mil = new Mile(obj.returnDistance());
    }

    // method for conversion 
    public void toConvert() {// type driven conversion
        if (km == null) {
            km = new Kilometre(mil.returnDistance() * 1.5);
            System.out.println("Distance in Kilometre is : " + km);
        } else {
            mil = new Mile(km.returnDistance() / 1.5);
            System.out.println("Distance in Mile is : " + mil);
        }
    }
}

// desiging the class with main() method to implement the functionalities
class Q2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int choice;
            System.out.println("-----------------------------------");
            System.out.println("1) Convert Kilometres to Miles");
            System.out.println("2) Convert Miles to Kilometres");
            System.out.println("3) Exit");
            System.out.println("-----------------------------------");
            System.out.print("Enter Your Choice : ");

            choice = sc.nextInt();
            switch (choice) {
            case 1:
                System.out.print("Enter the distance (in KM) you want to convert to Miles : ");
                double dist1 = sc.nextDouble();
                Kilometre km = new Kilometre(dist1);
                Metric obj1 = new Metric(km);
                obj1.toConvert();
                break;
            case 2:
                System.out.print("Enter the distance (in Miles) you want to convert to KM : ");
                double dist2 = sc.nextDouble();
                Mile mil = new Mile(dist2);
                Metric obj2 = new Metric(mil);
                obj2.toConvert();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Enter either 1, 2 or 3");
            }
        }
    }
}
