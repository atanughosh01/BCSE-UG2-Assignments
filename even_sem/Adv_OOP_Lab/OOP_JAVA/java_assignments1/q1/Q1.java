/*Assignment 1:
Design a STUDENT class to store roll, name, course, admission date and marks in 5 subjects taken from user.
Create an array of STUDENT objects. Provide methods corresponding to admission date and receiving marks,
preparing mark sheet. Support must be there to show the number of students who have taken admission.

Inherit Student class and override the input method so as to input the department of each student. Search and
display a sorted list of students of one department or students based on scoring criteria. Create an arraylist of
students and remove a student based on certain criterion and then call gc() and check for free memory.

NOTE: Student roll/ID should be fixed length which include department name, admission year and roll and it
should be auto generated (no random roll no will be accepted). Consider at least four departments. 1st four
characters: dept, 2nd two characters : year, last three characters: roll no
Example: if a student is in dept. Of Computer Science and Engg.
1st admission : BCSE18001 (considering max 100 students in each dept.)
2nd admission : BCSE1 8002
if a student is in dept. Of Electronics and Telecommunication Engg.
1st admission : ETCE18001 (considering max 100 students in each dept.)
3rd admission : ETCE18003*/

//package java_assignments1.q1;

// import necessary packages to use built-in classes as required
import java.util.*;
import java.time.*;
import java.time.format.*;

class Student {
    // data members of class Student
    protected String roll;
    protected String name;
    protected String course;
    protected LocalDate admission_date;
    protected float marks[];

    // default constructor to initialize with dummy values
    Student() {
        roll = "NA";
        marks = new float[5];
        for (float m : marks)
            m = 0;
    }

    // input method to get values for Student object
    public boolean getAdmission() {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter name: ");
        name = sc.nextLine();

        System.out.print("Enter course: ");
        course = sc.nextLine();

        System.out.print("Enter admission date (DD-MM-YYYY): ");
        String input = sc.nextLine();

        try {
            // parse the string to check if it has a valid date format
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            admission_date = LocalDate.parse(input, format);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid Date Format.\n");
            return false;
        }
    }

    // method to compute and return total marks
    public float returnTotalMarks() {
        float total = 0;
        for (float m : marks)
            total = total + m;
        return total;
    }

    // method to accept marks in 5 subjects out of 100
    public void receiveMarks() {
        System.out.print("Enter marks in 5 subjects: ");
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 5; i++)
            marks[i] = sc.nextFloat();
    }

    // method to print the marksheet of a student
    public void printMarksheet() {
        System.out.println("Roll: " + roll + "		Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("Admission Date: " + admission_date);

        System.out.println("Marks in 5 subjects: ");
        for (float m : marks)
            System.out.print(m + " ");

        float total_marks = returnTotalMarks(); // compute total marks
        System.out.println("\nTotal marks = " + total_marks + "	Percentage = " + (total_marks / 5));
    }

}

// class designed for a Student enrolled in one of the available Departments
class RegisteredStudent extends Student implements Comparable<RegisteredStudent> {
    private String dept;
    static HashMap<String, Integer> hm; // hashmap used to store last roll no of any student in a department

    // initialise the hashmap with default values
    static {
        hm = new HashMap<String, Integer>();
        hm.put("CSE", 0);
        hm.put("ETCE", 0);
        hm.put("CHE", 0);
        hm.put("CIV", 0);
    }

    // default constructor to initialize with dummy values
    RegisteredStudent() {
        super();
        dept = "NA";
    }

    // method to check if a department exists
    static boolean checkDept(String dept_name) {
        if (hm.containsKey(dept_name))
            return true;
        else
            return false;
    }

    // method to generate roll no based on department and admission date
    private void assignRoll(String dept_name) {
        roll = dept_name + Integer.toString(YearMonth.from(admission_date).getYear()).substring(2, 4);
        int id = hm.get(dept_name);
        id = id + 1; // increment id by 1

        if (id < 10)
            roll = roll + "00" + id; // add two zeroes to the count portion of roll number
        else if (id < 100)
            roll = roll + "0" + id; // add one zero to the count portion of roll number
        else
            roll = roll + id;

        if (dept_name.length() == 3)
            roll = "B" + roll; // add the letter 'B' to the whole roll number

        hm.put(dept_name, id); // update the value in hashmap
    }

    // override the input method to get input for the department
    @Override
    public boolean getAdmission() {
        if (!super.getAdmission()) // if personal details are invalid, admission not possible
            return false;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter department name: ");

        dept = sc.nextLine();
        if (checkDept(dept)) {
            assignRoll(dept); // if department exists, assign a roll number
            System.out.println("Roll number allotted:- " + roll);
            return true;
        } else {
            System.out.println("Department does not exist.");
            return false;
        }
    }

    // method to check equality of roll of two registered students
    public boolean checkEqualityOfRoll(String r) {
        if (roll.equals(r))
            return true;
        else
            return false;
    }

    // method to check equality of department of two registered students
    public boolean checkEqualityOfDept(String d) {
        if (dept.equals(d))
            return true;
        else
            return false;
    }

    // method to compare two students based on their total marks
    @Override
    public int compareTo(RegisteredStudent rs) {
        return (Float.compare(rs.returnTotalMarks(), returnTotalMarks()));
    }
}

// class designed to maintain record of all students
class StudentList {
    private ArrayList<RegisteredStudent> list;

    // deafault contructor to initialize the list
    StudentList() {
        list = new ArrayList<RegisteredStudent>();
    }

    // method to add a new student
    public void addStudent() {
        RegisteredStudent rs = new RegisteredStudent();
        if (rs.getAdmission()) {
            rs.receiveMarks();
            list.add(rs); // add the new student to the list
            System.out.println("Student added successfully.\n");
        } else
            System.out.println("Student could not be added.\n");
    }

    // method to display total number of students
    public void displayStudentCount() {
        System.out.println("\nNo of students registered: " + list.size() + "\n");
    }

    // method to display marksheet of a particular student by roll number
    public void displayStudentMarksheet(String r) {
        for (RegisteredStudent rs : list)
            if (rs.checkEqualityOfRoll(r)) { // check if the student with given roll number is within the list
                System.out.println("\nStudent found.\n");
                rs.printMarksheet(); // print th details of the student
                return;
            }

        System.out.println("Student not found.\n");
    }

    // method to display students of a specific Department sorted by total marks
    public void displaySortedList(String dept_name) {
        if (RegisteredStudent.checkDept(dept_name)) { // check if the department exists
            ArrayList<RegisteredStudent> slist = new ArrayList<RegisteredStudent>();

            // add students belonging to a particular department
            for (RegisteredStudent rs : list)
                if (rs.checkEqualityOfDept(dept_name))
                    slist.add(rs);

            Collections.sort(slist); // sort the list with total marks as criterion

            if (slist.size() == 0) {
                System.out.println("\nThere are no students in department " + dept_name + ".\n");
                return;
            }

            System.out.println("\nSorted List of Students in department " + dept_name + "-->\n");

            for (RegisteredStudent rs : slist) {
                rs.printMarksheet(); // print the details of the student
                System.out.println("\n");
            }
        }

        else
            System.out.println("Department does not exist.\n");
    }

    // method to remove a student from the all students' list
    public void removeStudent(String r) {
        Iterator<RegisteredStudent> it = list.iterator();
        while (it.hasNext()) {
            RegisteredStudent rs = it.next();
            if (rs.checkEqualityOfRoll(r)) { // if a student with the roll number exists
                it.remove(); // remove the student from list
                System.out.println("\nStudent with roll number " + r + " removed successfully.\n");
                return;
            }
        }
        System.out.println("Student could not be found.\n");
    }
}

class Q1 {
    public static void main(String args[]) {
        StudentList s = new StudentList();
        Scanner sc = new Scanner(System.in);
        String rollno, deptname;
        char op = 'n';
        do {
            System.out.print("\nMain Menu\n-------------\n1. Add Student\n2. Display Marksheet of a Student by Roll\n3. Sort students of a Department by marks\n4. Remove a Student\n
                    5. View Total no. of students\n
                    6. Call GarbageCollector\n
                    7. Show Free Memory\n
                    --------------\n
                    Enter your choice: ");
            int ch = sc.nextInt();

            switch (ch) {
            case 1:
                s.addStudent();
                break;

            case 2:
                System.out.print("\nEnter roll number: ");
                sc.skip("\\s*");
                rollno = sc.nextLine();
                s.displayStudentMarksheet(rollno);
                break;

            case 3:
                System.out.print("\nEnter department code: ");
                sc.skip("\\s*");
                deptname = sc.nextLine();
                s.displaySortedList(deptname);
                break;

            case 4:
                System.out.print("\nEnter roll number: ");
                sc.skip("\\s*");
                rollno = sc.nextLine();
                s.removeStudent(rollno);
                break;

            case 5:
                s.displayStudentCount();
                break;

            case 6:
                System.gc();
                System.out.println("\nGarbage Collector called.\n");
                break;

            case 7:
                Runtime r = Runtime.getRuntime();
                System.out.println("\nFree Memory:- " + r.freeMemory() + "\n");
                break;

            default:
                System.out.println("\nWrong choice.");
            }

            System.out.print("\nDo you want to continue (y/n) ? ");
            op = sc.next().charAt(0);
        } while (op == 'y' || op == 'Y');
        sc.close();
    }
}
