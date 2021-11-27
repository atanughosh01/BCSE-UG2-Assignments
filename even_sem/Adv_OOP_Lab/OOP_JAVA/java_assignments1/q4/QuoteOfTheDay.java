/*Assignment 4:
Create a class diagram and Java code for the following system and scenario, taking into account the possibility of
future extensions. "The system is a command line utility that prints a short 'quote of the day' on the user's terminal
when run. To begin with the quote is selected randomly from a set of hard-coded strings within the program itself,
but that might change later on -- the quotes might be based on the user's history, the time of day, the date, etc..
Scenario
1. User types "java QuoteOfTheDay" on the command line.
2. System prints out a quote of the day, with an attribution.
Examples:
> javac QuoteOfTheDay
God helps them that help themselves. -- Benjamin Franklin
> javac QuoteOfTheDay
Happiness is not a reward - it is consequence. Suffering is not a punishment - it is a result. -- Robert Green
Ingersoll
> javac QuoteOfTheDay
Future. That period of time in which our affairs prosper, our friends are true and our happiness is assured. --
Ambrose Bierce
> javac QuoteOfTheDay
Honesty is the first chapter of the book of wisdom. --Thomas Jefferson
Note:
● user's history: within a fixed time duration it will not print any similar type of quote (considering dif erent
execution)
● the time of day: morning, noon evening , night
● the date: on 15th August -- Independence day, 5th September -- Teachers’ day */

//package java_assignments1.q4;

import java.util.*;
import java.lang.Math;
//import java.util.ArrayList;

//designing the Quote Generator class
class QuoteGenerator {
    private ArrayList<String> arr = new ArrayList<String>();

    QuoteGenerator() {
        arr.add("God helps them that help themselves. -- Benjamin Franklin");
        arr.add("Happiness is not a reward - it is consequence. Suffering is not a punishment - it is a result. -- Robert Green Ingersoll");
        arr.add("Future. That period of time in which our affairs prosper, our friends are true and our happiness is assured. -- Ambrose Bierce");
        arr.add("Honesty is the first chapter of the book of wisdom. --Thomas Jefferson");
        arr.add("Be yourself; everyone else is already taken.― Oscar Wilde");
        arr.add("Be the change that you wish to see in the world. ― Mahatma Gandhi");
        arr.add("A room without books is like a body without a soul. ― Marcus Tullius Cicero");
        arr.add("If you tell the truth, you don't have to remember anything. ― Mark Twain");
        arr.add("If you want to know what a man's like, take a good look at how he treats his inferiors, not his equals.― J.K. Rowling");
        arr.add("To live is the rarest thing in the world. Most people exist, that is all.― Oscar Wilde");
        arr.add("Always forgive your enemies, nothing annoys them so much. ― Oscar Wilde");
        arr.add("Without music, life would be a mistake. ― Friedrich Nietzsche");
        arr.add("Life isn't about getting and having, it's about giving and being. –Kevin Kruse");
        arr.add("Whatever the mind of man can conceive and believe, it can achieve. –Napoleon Hill");
        arr.add("Strive not to be a success, but rather to be of value. –Albert Einstein");
    }

    public String giveQuote() {
        double rand = Math.random();
        int idx = (int) (rand * 10) % arr.size();
        String quote = arr.get(idx);
        return quote;
    }
}

// class QuoteOfTheDay
class QuoteOfTheDay {
    public static void main(String args[]) {
        QuoteGenerator qg = new QuoteGenerator();
        Date d = new Date();
        System.out.print("Printing current date & time...  ");
        System.out.println(d);
        System.out.print("Quote : ");
        String s = qg.giveQuote();
        System.out.println(s);
    }
}
