/*Assignment 3:
Write a program Parentheses.java that reads in a text stream from standard input
and uses a stack to determine whether or not its parentheses are properly 
balanced. For example, your program should print true for [()]{}{[()()]()} and 
false for [(]). You need to implement the stack class by yourself. */

//package java_assignments1.q3;

import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Parentheses {
    public static void main(String[] args) {
        int flag;
        do {
            checkParentheses cp = new checkParentheses();
            cp.input();
            if (cp.checkInput())
                System.out.println("match\n");
            else
                System.out.println("not match\n");

            System.out.println("press 1 to continue, 0 to exit");
            Scanner sc = new Scanner(System.in);
            flag = sc.nextInt();
        } while (flag == 1);
    }
}

class checkParentheses {
    String str;
    Stack stk = new Stack();

    public void input() {
        System.out.println("Enter stream of text to check : ");
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
    }

    public boolean checkInput() {
        int i;

        for (i = 0; i < str.length(); i++) {
            int p = i;

            if (str.charAt(p) == '(' || str.charAt(p) == '{' || str.charAt(p) == '[')
                stk.push(str.charAt(p));
            else if (str.charAt(p) == ')') {
                if (stk.isEmpty() || stk.pop() != '(') {
                    return false;
                }
            } else if (str.charAt(p) == '}') {
                if (stk.isEmpty() || stk.pop() != '{') {
                    return false;
                }
            } else if (str.charAt(p) == ']') {
                if (stk.isEmpty() || stk.pop() != '[') {
                    return false;
                }
            }
        }
        if (!stk.isEmpty())
            return false;
        return true;
    }
}

class Stack {
    static final int MAX = 1000;
    int top;
    char a[] = new char[MAX]; // Maximum size of Stack

    public boolean isEmpty() {
        return (top < 0);
    }

    Stack() {
        top = -1;
    }

    public boolean push(char x) {
        if (top >= MAX) {
            System.out.println("Stack Overflow");
            return false;
        } else {
            a[++top] = x;
            return true;
        }
    }

    public char pop() {
        if (top < 0) {
            return 0;
        } else {
            char x = a[top--];
            return x;
        }
    }
}
