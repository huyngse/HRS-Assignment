package hrs.entities;
import hrs.services.EmployeeService;
import java.util.Vector;
public class Department {
    //ATTRIBUTE
    private String departmentName;
    private Vector<Employee> employeeList = new Vector<>();
    
    //GETTER AND SETTER
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Vector<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Vector<Employee> employeeList) {
        this.employeeList = employeeList;
    }
    
    //CONSTRUCTOR
    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
    
    //DISPLAY ALL EMPLOYEE
    public void display() {
        System.out.println("[" + departmentName + "]");
        EmployeeService.displayEmployeeList(employeeList, "Employee");
    }
}
