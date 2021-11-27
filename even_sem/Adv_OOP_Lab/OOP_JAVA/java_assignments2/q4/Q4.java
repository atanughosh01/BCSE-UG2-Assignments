/* 4. Consider a wrapper class for a numeric basic type. Check the support for
the following: conversion from i) basic type to object ii) object to basic type
iii) basic type to String iv) String (holding numeric data) to numeric object
v) object to String. */

//package java_assignment2.q4

// desiging the class with main() method to implement the functionalities
class Q4 {
    public static void main(String[] args) {
        // testing the wrapper class of Integer Data type
        // (i) basic data type to object auto boxing
        int a = 100;
        Integer object1 = a;
        System.out.println("Basic Data Type to Integer Object : " + object1);

        // (ii)object to basic data type auto unboxing
        a = object1;
        System.out.println("Integer Object to Basic Data Type : " + a);

        // (iii) basic data type to string
        String b = Integer.toString(a); // using the static method toString() present in Integer wrapper class
        String c = b + "@ju";
        String[] arrOfStr = c.split("@", 0);
        for (String w : arrOfStr) { // to test whether conversion has been taken place properly or not
            System.out.println("Basic Data Type to Splitted String : " + w);
        }

        // (iv)String to numeric object
        Integer object2 = Integer.parseInt(arrOfStr[0]);
        object2 += 25; // to test whether conversion has been taken place properly or not
        System.out.println("String to Basic Data Type Via Wrapper Class Function : " + object2);

        // (v) object to string
        arrOfStr[0] = object2.toString();
        arrOfStr[0] += "abc"; // to test whether conversion has been taken place properly or not
        System.out.println("Object to String Conversion : " + arrOfStr[0]);
    }
}
