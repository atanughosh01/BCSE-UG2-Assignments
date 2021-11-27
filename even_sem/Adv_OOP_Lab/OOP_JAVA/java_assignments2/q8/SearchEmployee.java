
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.String;
import java.util.Scanner;

public class SearchEmployee extends JFrame implements ActionListener {
    // JFrame f = new JFrame("Employee Search");
    private JLabel label;
    private JTextField textField;
    private JButton button;
    private JTextArea field;

    public SearchEmployee() {
        setTitle("Employee Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
        setLocation(300, 100);
        setLayout(null);
        // f.setResizable(false);

        label = new JLabel("Employee Code");
        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setBounds(100, 50, 200, 20);
        getContentPane().add(label);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 15));
        textField.setSize(100, 20);
        textField.setLocation(230, 50);
        getContentPane().add(textField);

        button = new JButton("Search");
        button.setBounds(250, 80, 100, 30);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        getContentPane().add(button);
        button.addActionListener(this);

        field = new JTextArea();
        field.setSize(600, 300);
        field.setLocation(50, 120);
        field.setFont(new Font("Arial", Font.PLAIN, 15));
        field.setEditable(false);
        field.setLineWrap(true);
        getContentPane().add(field);

    }

    String searchFile(String id) throws IOException {
        FileReader f;
        Scanner sc;
        String res = "NotFound";
        id = String.format("%4s", id);
        try {
            // f = new FileReader(System.getProperty("user.dir") + "/java_assignments/D20210416/q08/Employee1.txt");
        	f = new FileReader("Employee1.txt");
            sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // System.out.println(line);
                if (line.contains("Emp_Code:" + id)) {
                    res = line;
                }
            }
            sc.close();
            f.close();
        } catch (FileNotFoundException e) {
            res = "NotExists";
        }
        return res;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            try {
                String str = searchFile(textField.getText());
                if (str.equals("NotFound")) {
                    JOptionPane.showMessageDialog(this, "Data Not found", "Message", JOptionPane.WARNING_MESSAGE);
                    field.setText("The Employee doesn't exist for the entered employee code");
                }

                else if (str.equals("NotExists")) {
                    JOptionPane.showMessageDialog(this, "File Not Found", "Message", JOptionPane.WARNING_MESSAGE);
                } else {
                    FileWriter fr = new FileWriter("Data.txt", true);
                    JOptionPane.showMessageDialog(this, "Data Found!", "Message", JOptionPane.INFORMATION_MESSAGE);
                    fr.write(str);
                    fr.write(System.lineSeparator());
                    field.setText(str);
                    fr.close();
                }

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}