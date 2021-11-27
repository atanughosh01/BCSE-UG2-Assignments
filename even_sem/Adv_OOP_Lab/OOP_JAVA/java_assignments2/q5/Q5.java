/* 5. Each customer of a bank has customer id, name, and current loan amount
and phone number. One can change the attributes like name, phone
number. A customer may ask for loan of certain amount. It is granted
provided the sum of current loan amount and asked amount does not
exceed credit limit (fixed amount for all customer). A customer may be a
privileged amount. For such customers credit limit is higher. Once a loan is
sanctioned necessary updates should be made. Any type of customer
should be able to find his credit limit, current loan amount and amount of
loan he can seek. Design and implement the classes. */

//package java_assignment2.q5

// importing required packages
import java.util.*;

class CustomerBankAcct {
    // attributes
    protected int customerId;
    protected String name;
    protected int currentLoanAmnt;
    protected String phoneNumber;
    protected boolean isPrivileged;
    protected ArrayList<Integer> loans = new ArrayList<Integer>();
    public static final int privCeditLimit = 5000000;
    public static final int nonPrivCreditLimit = 2000000;
    public static int idGen = 1910;

    CustomerBankAcct() {
        currentLoanAmnt = -1;
        isPrivileged = false;
    }

    // public method to get the customer details
    public int getCustomerDetails() {
        Scanner sc = new Scanner(System.in);
        // taking name input
        System.out.print("Enter Name of Customer : ");
        name = sc.nextLine();
        // taking phone number input
        System.out.print("Enter Phone Number of Customer : ");
        phoneNumber = sc.nextLine();
        // sanity checking of phone numner
        if (phoneNumber.length() != 10) {
            System.out.println("Error! Please Enter a Valid Phone Number");
            return 0;
        }
        // generating the customer id
        idGen += 1;
        customerId = idGen;
        //
        System.out.println("Type of Membership You Want is -");
        System.out.println("1) Normal/Non-Privileged Membership\n2) Privileged Membership");
        System.out.print("Enter Your Choice : ");
        int choice = Integer.parseInt(sc.nextLine());
        if (choice == 1) {
            isPrivileged = false;
        } else if (choice == 2) {
            isPrivileged = true;
        } else {
            System.out.println("Error ! Please Select '1' or '2'.");
        }
        System.out.println("Account Creation Successfull !\nAccount Number : " + customerId);
        return 1;
    }

    // public method to show the credit limits
    public static void showCreditLimits() {
        System.out.println("Credit Limit for Normal Non-Privileged Customer : " + nonPrivCreditLimit);
        System.out.println("Credit Limit for Privileged Customer : " + privCeditLimit);
    }

    public void showLoanDetails() {
        int sum = 0;
        System.out.println("Loans Taken so Far--------");
        for (int i = 0; i < loans.size(); i++) {
            System.out.println(loans.get(i));
            sum += loans.get(i);
        }
        int availableLoanAmnt = isPrivileged == true ? privCeditLimit - sum : nonPrivCreditLimit - sum;
        if (isPrivileged) {
            System.out.println("Maximum Credit Limit Allowance " + privCeditLimit);
        } else {
            System.out.println("Maximum Credit Limit Allowance " + nonPrivCreditLimit);
        }
        if (currentLoanAmnt == -1) {
            System.out.println("No Loan Issued So Far !");
        } else {
            System.out.println("Last Amount of Issued Loan : " + currentLoanAmnt);
        }
        System.out.println("Total Amount of Loan Taken : " + sum);
        System.out.println("Loan Amount Available for Issue : " + availableLoanAmnt);

        if (loans.size() > 0) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Do You Want to See the Loan History (y/N) ? : ");
            String choice = sc.nextLine();
            if (choice.equals("y") || choice.equals("Y")) {
                for (int i =0; i<loans.size(); i++){
                    System.out.printf("Loan #%d : %d\n", i+1, loans.get(i));
                }
            }
        }
    }

    // public method to get a loan
    public void getLoanDetails() {
        int sum = 0;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < loans.size(); i++) {
            sum += loans.get(i);
        }
        int availableLoanAmnt = isPrivileged == true ? privCeditLimit - sum : nonPrivCreditLimit - sum;
        System.out.println("Loan Amount Available for Issue : " + availableLoanAmnt);
        System.out.print("Enter the amount of loan : ");
        currentLoanAmnt = Integer.parseInt(sc.nextLine());
        if (currentLoanAmnt < 0) {
            System.out.println("Error ! Please Enter Valid Loan Amount");
            return;
        } else if (currentLoanAmnt <= availableLoanAmnt) {
            loans.add(currentLoanAmnt);
            System.out.println("Your Loan Was Issued Successfully !");
        } else {
            System.out.println("Your Entered Loan Amount Exceeds Your Avaiable Loan Amount");
            currentLoanAmnt = loans.get(loans.size() - 1);
        }
    }

    // method to show the customer details
    public void showCustomerDetails() {
        System.out.println("Name of the Customer : " + name);
        System.out.println("Id of the Customer : " + customerId);
        if (isPrivileged) {
            System.out.println("Membership Type : PRIVILEGED");
        } else {
            System.out.println("Membership Type : NON-PRIVILEGED");
        }
        System.out.println("Phone Number of the Customer : " + phoneNumber);
    }

    // public method to return customer id
    public int returnCustomerId() {
        return customerId;
    }

}

class Bank {
    protected ArrayList<CustomerBankAcct> list = new ArrayList<CustomerBankAcct>();

    // public method to add a account
    public void addAccount() {
        CustomerBankAcct obj = new CustomerBankAcct();
        int res = obj.getCustomerDetails();
        if (res == 1) // else it will unnecessarily keep on increasing the list size
            list.add(obj);
    }       

    // public method to show a account based on id
    public void showAccount(int id) {
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).returnCustomerId() == id) {
                list.get(i).showCustomerDetails();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("No Such Customer was Found !");
        }
    }

    // public method to show loan details of a customer
    public void showAccountLoanDetails(int id) {
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).returnCustomerId() == id) {
                list.get(i).showLoanDetails();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("No Such Customer was Found !");
        }
    }

    // method to get a loan for a customer based on id
    public void getLoan(int id) {
        boolean flag = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).returnCustomerId() == id) {
                list.get(i).getLoanDetails();
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("No Such Customer was Found !");
        }
    }
}

class Q5 {
    public static void main(String[] args) {
        Bank obj = new Bank();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---------------------------------------");
            System.out.println("1) Add Customer Account");
            System.out.println("2) View Account by Id ");
            System.out.println("3) Show Account Loan Details");
            System.out.println("4) Issue Loan");
            System.out.println("5) Exit");
            System.out.println("---------------------------------------");
            System.out.print("Enter Your Choice : ");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    obj.addAccount();
                    break;
                case 2:
                    System.out.print("Enter Customer Id : ");
                    int id = Integer.parseInt(sc.nextLine());
                    obj.showAccount(id);
                    break;
                case 3:
                    System.out.print("Enter Customer Id : ");
                    id = Integer.parseInt(sc.nextLine());
                    obj.showAccountLoanDetails(id);
                    break;
                case 4:
                    System.out.print("Enter Customer Id : ");
                    id = Integer.parseInt(sc.nextLine());
                    obj.getLoan(id);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Enter a value out of 1, 2, 3, 4, 5");
            }
        }
    }
}
