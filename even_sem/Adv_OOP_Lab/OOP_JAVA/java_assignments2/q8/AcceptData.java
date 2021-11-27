
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class AcceptData extends JFrame implements ActionListener {
    private Container C;
    private JLabel title, name, emp_code, salary, grade, dept_code_emp;
    private JTextField name_field, emp_code_field, salary_field;
    private JComboBox<String> dept_code_drop_down, grade_label;
    private JTextArea display_text;
    private JButton save, clear;
    private String dep_code_choices[] = { "Sales", "Market", "Finance", "HumanResource", "R&D" };
    private String grades[] = { "A", "B", "C" };

    public AcceptData() {

        setTitle("EMP_REG");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        C = getContentPane();
        C.setLayout(null);

        title = new JLabel("EMPLOYEE REGISTRATION");
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setSize(700, 30);
        title.setLocation(250, 30);
        C.add(title);

        name = new JLabel("Name");
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(80, 20);
        name.setLocation(100, 100);
        C.add(name);

        name_field = new JTextField();
        name_field.setFont(new Font("Arial", Font.PLAIN, 15));
        name_field.setSize(200, 20);
        name_field.setLocation(185, 100);
        C.add(name_field);
        setVisible(true);

        emp_code = new JLabel("Emp. Code");
        emp_code.setFont(new Font("Arial", Font.PLAIN, 20));
        emp_code.setSize(100, 20);
        emp_code.setLocation(100, 140);
        C.add(emp_code);

        emp_code_field = new JTextField();
        emp_code_field.setFont(new Font("Arial", Font.PLAIN, 15));
        emp_code_field.setSize(200, 20);
        emp_code_field.setLocation(205, 140);
        C.add(emp_code_field);

        salary = new JLabel("Salary");
        salary.setFont(new Font("Arial", Font.PLAIN, 20));
        salary.setSize(80, 20);
        salary.setLocation(100, 180);
        C.add(salary);

        salary_field = new JTextField();
        salary_field.setFont(new Font("Arial", Font.PLAIN, 15));
        salary_field.setSize(200, 20);
        salary_field.setLocation(190, 180);
        C.add(salary_field);

        dept_code_emp = new JLabel("Department Code");
        dept_code_emp.setFont(new Font("Arial", Font.PLAIN, 20));
        dept_code_emp.setSize(200, 20);
        dept_code_emp.setLocation(100, 220);
        C.add(dept_code_emp);

        grade = new JLabel("Grade");
        grade.setFont(new Font("Arial", Font.PLAIN, 20));
        grade.setSize(80, 20);
        grade.setLocation(100, 260);
        C.add(grade);

        save = new JButton("Save");
        save.setFont(new Font("Arial", Font.PLAIN, 10));
        save.setSize(200, 30);
        save.setLocation(200, 450);
        save.addActionListener(this);
        C.add(save);

        clear = new JButton("Clear");
        clear.setFont(new Font("Arial", Font.PLAIN, 10));
        clear.setSize(200, 30);
        clear.setLocation(450, 450);
        clear.addActionListener(this);
        C.add(clear);

        grade_label = new JComboBox<String>(grades);
        grade_label.setFont(new Font("Arial", Font.PLAIN, 10));
        grade_label.setSize(100, 20);
        grade_label.setLocation(180, 260);
        C.add(grade_label);

        dept_code_drop_down = new JComboBox<String>(dep_code_choices);
        dept_code_drop_down.setFont(new Font("Arial", Font.PLAIN, 10));
        dept_code_drop_down.setSize(200, 20);
        dept_code_drop_down.setLocation(300, 220);
        C.add(dept_code_drop_down);

        display_text = new JTextArea();
        display_text.setFont(new Font("Arial", Font.PLAIN, 20));
        display_text.setSize(300, 30);
        display_text.setLocation(300, 500);
        display_text.setLineWrap(true);

        C.add(display_text);

    }

    public void addToFile(Employee emp) throws IOException {
        // FileWriter f = new FileWriter(System.getProperty("user.dir") + "/java_assignments/D20210416/q08/Employee1.txt", true);
        FileWriter f = new FileWriter("Employee1.txt", true);
        f.write(emp.toString());
        f.write(System.lineSeparator());
        f.close();
    }

    public boolean checkUniqueEmpId(String id) throws IOException {
        boolean res = true;
        try {
            FileReader f = new FileReader("Employee1.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String temp = line.substring(9, 13);
                if (temp.equals(id)) {
                    res = false;
                }
            }
            sc.close();
            f.close();
            return res;
        } catch (FileNotFoundException f) {
           // File file = new File(System.getProperty("user.dir") + "/java_assignments/D20210416/q08/Employee1.txt");
        	File file = new File("Employee1.txt");
            file.createNewFile();
            return true;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
            int x = JOptionPane.showConfirmDialog(this, "Do you want to save?", "Message", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (x == JOptionPane.YES_OPTION) {
                Employee emp = new Employee();
                emp.setEmp_name(name_field.getText());
                if (emp_code_field.getText().length() != 4) {
                    JOptionPane.showMessageDialog(this, "Id must be 4 digit", "Message", JOptionPane.WARNING_MESSAGE);
                    // System.exit(0);
                } else {
                    emp.setEmp_code(emp_code_field.getText());
                    emp.setBasic_salary(salary_field.getText());
                    emp.setDept_code(dept_code_drop_down.getSelectedItem().toString());
                    emp.setEmp_name(name_field.getText());
                    emp.setGrade(grade_label.getSelectedItem().toString());
                    try {
                        if (checkUniqueEmpId(emp_code_field.getText())) {
                            addToFile(emp);
                            display_text.setText("Data Submitted Successfully");
                        } else {
                            JOptionPane.showMessageDialog(this, "Duplicate Employee Id", "Message",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                }
            }
        } else if (e.getSource() == clear) {
            String empty = "";
            name_field.setText(empty);
            emp_code_field.setText(empty);
            salary_field.setText(empty);
            dept_code_drop_down.setSelectedIndex(0);
            grade_label.setSelectedIndex(0);
            display_text.setText("Correct Data OR add new data");

        }

    }
}