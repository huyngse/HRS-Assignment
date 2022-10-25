package hrs.main;

import hrs.entities.Department;
import hrs.services.DepartmentService;
import hrs.services.EmployeeService;
import hrs.services.MenuService;
import hrs.utils.EmployeeValidation;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        //INITIALIZATION
        MenuService menu = new MenuService(
                "EMPLOOYEE MANAGEMENT SYSTEM",
                "Please choose function you'd like to do: "
        );
        menu.addOptions(
                "Add an employee",
                "Display employees",
                "Classify employees",
                "Search employee by (department, emp's name)",
                "Update an employee",
                "Delete an employee",
                "Report",
                "Quit"
        );
        Vector<Department> departmentList = new Vector<> ();
        EmployeeService.setDepartmentList(departmentList);
        EmployeeValidation.setDepartmentList(departmentList);
        DepartmentService.setDepartmentList(departmentList);

        //RUN MENU
        int choice;
        do {
            menu.display();
            choice = menu.getUserChoice();
            switch (choice) {
                case 1: {
                    EmployeeService.addEmployee();
                    break;
                }
                case 2: {
                    EmployeeService.displayEmployees();
                    break;
                }
                case 3: {
                    EmployeeService.classifyEmployees();
                    break;
                }
                case 4: {
                    EmployeeService.searchEmployee();
                    break;
                }
                case 5: {
                    EmployeeService.updateEmployee();
                    break;
                }
                case 6: {
                    EmployeeService.removeEmployee();
                    break;
                }
                case 7: {
                    DepartmentService.report();
                    break;
                }
                case 8: {
                    DepartmentService.SaveToFile();
                }
            }
            System.out.println("");
        } while (choice != 8);
    }
}
