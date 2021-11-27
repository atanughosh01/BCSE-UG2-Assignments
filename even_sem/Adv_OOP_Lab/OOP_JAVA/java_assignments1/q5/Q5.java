/*Assignment 5:
Indexing a book. Write a program that reads in a text file from standard input and compiles an alphabetical index
of which words/phrases appear on which lines, as in the following input. Ignore case and punctuation. For each
word maintain a list of location on which it appears. Try to use HashTable and/or HashMap class (of java.util).
Note: key of HashMap : word or phrase
Value of HashMap can be single or multiple(if multiple time occurs). */

//package java_assignments1.q5;

import java.util.*;
import java.io.*;

class Index {

    private Map<String, ArrayList<Integer>> pos;

    Index(BufferedReader txt) {
        Scanner sc = new Scanner(txt);
        pos = new HashMap<String, ArrayList<Integer>>();
        int ln = 1;
        try {
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                line = line.toLowerCase();
                String words[] = line.split("\\W+");
                for (String w : words) {

                    w = w.toLowerCase();
                    ArrayList<Integer> list = pos.get(w);

                    if (list == null) {
                        list = new ArrayList<>();
                        list.add(ln);
                    } else if (!(list.contains((Integer) ln)))
                        list.add(ln);
                    pos.put(w, list);
                }
                ln++;
            }
        } finally {
            sc.close();
        }
    }

    public String toString() {
        TreeMap<String, ArrayList<Integer>> sorted = new TreeMap<>(pos);
        return sorted.toString();
    }
}

public class Q5 {
    public static void main(String[] args) {
        // int count=0;
        try {

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter file path :");
            String src = sc.nextLine();
            BufferedReader br = new BufferedReader(new FileReader(src));
            String index_str = new Index(br).toString();
            index_str = index_str.replace("],", "]\n");
            // count++;
            System.out.println(index_str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}