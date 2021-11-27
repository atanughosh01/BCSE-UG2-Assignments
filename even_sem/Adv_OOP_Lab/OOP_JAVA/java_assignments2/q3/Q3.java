/* 3. Take a String input that contains multiple words. Do the following: i)
number of times ‘a’ appears ii) number of times “and” appears iii) whether
it starts with “The” or not iv) put the String into an array of characters v)
display the tokens in the String (tokens are the substrings separated by
space or @ or .) */

//package java_assignment2.q3

// importing required packages
import java.util.*;

// desiging the class with main() method to implement the functionalities
class Q3 {
    // method to count the number of times 'a' appears in the string
    public static void numberOfA(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a')
                count++;
        }
        System.out.println("Total number of 'a'-s present in the String : " + count);
    }

    // method to count the number of times 'and' appears in the string
    public static void numberOfAnd(String s) {
        StringTokenizer st = new StringTokenizer(s);
        int count = 0;
        while (st.hasMoreTokens()) {
            String s1 = new String(st.nextToken());
            if (s1.equals("and"))
                count++;
        }
        System.out.println("Total number of 'and'-s present in the String : " + count);
    }

    // method to check wheather the string starts with 'The' or not
    public static void checkStartingWord(String s) {
        if (s.startsWith("The"))
            System.out.println("The String Starts With 'The'. ");
        else
            System.out.println("The String DOES NOT Start With 'The'. ");
    }

    // method to convert the string into an array of characters
    public static void convertToCharArray(String s) {
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println("Character at position " + i + " is : " + array[i]); // just print the characters of the
        }
    }

    // method to display the tokens in the string
    public static void displayTokens(String s) {
        StringTokenizer st = new StringTokenizer(s, "@.");
        System.out.println("Tokens Present in the String are : ");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

    // desiging the class with main() method to implement the functionalities
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = new String();
        while (true) {
            int choice;

            System.out.println("-----------------------------------------------------");
            System.out.println("1) Enter a New String");
            System.out.println("2) Count Total Number of 'a'-s in the String");
            System.out.println("3) Count Total Number of 'and'-s in the String");
            System.out.println("4) Check Whether String Starts With 'The' or Not");
            System.out.println("5) Put the String Into an Array of Characters");
            System.out.println("6) Display the Tokens in the String");
            System.out.println("7) Exit the Menu");
            System.out.println("-----------------------------------------------------");
            System.out.print("\nEnter Your Choice : ");

            choice = sc.nextInt();

            switch (choice) {
            case 1:
                Scanner scn = new Scanner(System.in);
                System.out.print("Enter the String : ");
                s = scn.nextLine();
                break;
            case 2:
                numberOfA(s);
                break;
            case 3:
            numberOfAnd(s);
                break;
            case 4:
            checkStartingWord(s);
                break;
            case 5:
            convertToCharArray(s);
                break;
            case 6:
            displayTokens(s);
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Enter a value out of 1, 2, 3, 4, 5, 6, 7");
            }
        }
    }
}