
import java.util.Scanner;

public class GUI {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("1. Add new employee\n2. Search for employee using ID\nAnything else - to exit\nEnter: ");
        sc.skip("\\s*");
        int ch = sc.nextInt();
        if (ch == 1) {
            AcceptData a = new AcceptData();
            a.setVisible(true);
        } else if (ch == 2) {
            SearchEmployee b = new SearchEmployee();
            b.setVisible(true);
        }
        sc.close();
    }
}