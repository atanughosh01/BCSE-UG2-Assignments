
import java.io.Serializable;

public class Employee implements Serializable {
    private String emp_code;
    private String emp_name;
    private String basic_salary;
    private String grade;// A ,B or C
    private String dept_code;

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public void setEmp_name(String name) {
        emp_name = name;
    }

    public void setBasic_salary(String salary) {
        basic_salary = salary;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getEmp_code() {
        return emp_code;
    }

    @Override
    public String toString() {
        return "Emp_Code:" + emp_code + "    Name:" + emp_name + "   Salary:" + basic_salary + "  Dept_Code:"
                + dept_code + "   Grade:" + grade;
    }
}