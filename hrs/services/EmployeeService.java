package hrs.services;

import hrs.entities.Department;
import hrs.entities.Employee;
import hrs.entities.HourlyEmployee;
import hrs.entities.SalariedEmployee;
import hrs.utils.EmployeeValidation;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

public class EmployeeService {

    private static Vector<Department> departmentList;
    //general list contain all employee from all department
    //Employee associate with department name
    private static Vector<Employee> generalList = new Vector<>();
    private static final MenuService addMenu;
    private static final MenuService displayMenu;
    private static final MenuService searchMenu;

    //INITIALIZATION
    static {
        addMenu = new MenuService(
                "ADD AN EMPLOYEE",
                "Please choose type of employee: ");
        addMenu.addOptions("Hourly employee", "Salaried employee");
        displayMenu = new MenuService(
                "DISPLAY EMPLOYEE",
                "Please choose an option to display: ");
        displayMenu.addOptions("Displays order by SNN", "Displays order by name");
        searchMenu = new MenuService(
                "SEARCH EMPLOYEE",
                "Please choose an option to search: ");
        searchMenu.addOptions("Search by department name", "Search by employee name");
    }

    public static void setDepartmentList(Vector<Department> list) {
        departmentList = list;
    }

    private static void refreshGeneralList() {
        generalList.clear();
        Vector<Employee> employeeList;
        for (Department de : departmentList) {
            employeeList = de.getEmployeeList();
            for (Employee x : employeeList) {
                generalList.add(x);
            }
        }
    }

    private static int findSsn(String ssn) {
        for (int i = 0; i < generalList.size(); i++) {
            if (generalList.get(i).getSsn().equals(ssn)) {
                return i;
            }
        }
        return -1;
    }

    public static void displayEmployeeList(Vector<Employee> list, String entityName) {
        if (list.isEmpty()) {
            System.out.println("There is no " + entityName);
        } else {

            for (int i = 0; i < list.size(); i++) {
                System.out.println("____________________");
                System.out.println(entityName + " #" + (i + 1));
                list.get(i).display();
            }
        }
    }

    //ADD AN EMPLOYEE
    public static void addEmployee() {
        int choice;
        addMenu.display();
        choice = addMenu.getUserChoice();
        Employee e = null;
        //ADDITIONAL ATTRIBUTES
        switch (choice) {
            case 1: {
                e = new HourlyEmployee();
                e.input();
                break;
            }
            case 2: {
                e = new SalariedEmployee();
                e.input();
                break;
            }
        }
        //Add employee to department
        String deName = InputService.inputString("Department: ");
        DepartmentService.addToDepartment(deName, e);
        System.out.println("Employee added successfully");
    }

    //DISPLAY ALL EMPLOYEES
    public static void displayEmployees() {
        refreshGeneralList();
        if (generalList.isEmpty()) {
            System.out.println("There is nothing to show");
            return;
        }
        int choice;
        displayMenu.display();
        choice = displayMenu.getUserChoice();
        //sort before display
        switch (choice) {
            //order by SSN
            case 1: {
                Collections.sort(generalList, new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getSsn().compareTo(o2.getSsn());
                    }
                });
                break;
            }
            //order by name
            case 2: {
                Collections.sort(generalList, new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getFirstName().compareTo(o2.getFirstName());
                    }
                });
                break;
            }
        }
        //display
        displayEmployeeList(generalList, "Employee");
    }

    //CLASSIFY EMPLOYEES
    public static void classifyEmployees() {
        refreshGeneralList();
        if (generalList.isEmpty()) {
            System.out.println("There is nothing to classify");
            return;
        }
        Vector<Employee> hourlyList = new Vector<>();
        Vector<Employee> salariedList = new Vector<>();
        for (Employee e : generalList) {
            if (e instanceof HourlyEmployee) {
                hourlyList.add((HourlyEmployee) e);
            } else if (e instanceof SalariedEmployee) {
                salariedList.add((SalariedEmployee) e);
            }
        }
        displayEmployeeList(hourlyList, "Hourly Employee");
        displayEmployeeList(salariedList, "Salaried Employee");

    }

    //SEARCH EMPLOYEES
    public static void searchEmployee() {
        refreshGeneralList();
        if (generalList.isEmpty()) {
            System.out.println("There is nothing to search");
            return;
        }
        int choice;
        searchMenu.display();
        choice = searchMenu.getUserChoice();
        switch (choice) {
            //search by department name
            case 1: {
                String name = InputService.inputString("Department name: ");
                int i = DepartmentService.findDepartment(name);
                if (i == -1) {
                    System.out.println("Department not found");
                    break;
                }
                departmentList.get(i).display();
                break;
            }
            //Search by employee name
            case 2: {
                String name = InputService.inputString("Employee name: ");
                Vector<Employee> resultList = new Vector<>();
                for (Employee e : generalList) {
                    if (e.getFirstName().equals(name)) {
                        resultList.add(e);
                    }
                }
                if (resultList.isEmpty()) {
                    System.out.println("Employee not found");
                    break;
                }
                displayEmployeeList(resultList, "Employee");
                break;
            }
        }
    }

    //UPDATE EMPLOYEE
    public static void updateEmployee() {
        refreshGeneralList();
        if (generalList.isEmpty()) {
            System.out.println("There is nothing to update");
            return;
        }
        String ssn = InputService.inputString("SSN: ");
        int i = findSsn(ssn);
        if (i == -1) {
            System.out.println("Employee not found");
            return;
        }
        Employee e = generalList.get(i);
        String firstName;
        String lastName;
        String birthDate;
        String phone;
        String email;
        boolean isValid = false;
        //First name and last name
        firstName = InputService.inputString("First name: ");
        lastName = InputService.inputString("Last name: ");
        //Birth date
        do {
            birthDate = InputService.inputString("Birth date (DD/MM/YYYY): ");
            isValid = EmployeeValidation.isValidDate(birthDate);
            if (!isValid) {
                System.out.println("Entered date is not a valid date!");
            }
        } while (!isValid);
        //Phone number
        do {
            phone = InputService.inputString("Phone number: ");
            isValid = EmployeeValidation.isValidPhone(phone);
            if (!isValid) {
                System.out.println("Phone number must contain at least 7 digits!");
            }
        } while (!isValid);
        //Email
        do {
            email = InputService.inputString("Email: ");
            isValid = EmployeeValidation.isValidEmail(email);
            if (!isValid) {
                System.out.println("Email is not valid!");
            }
        } while (!isValid);
        //ADDITIONAL ATTRIBUTES
        if (e instanceof HourlyEmployee) {
            //Hourly employee
            double rate;
            double workingHours;
            rate = InputService.inputDouble("Rate: ", 0, Double.MAX_VALUE);
            workingHours = InputService.inputDouble("Working hours: ", 0, Double.MAX_VALUE);
            ((HourlyEmployee) e).setRate(rate);
            ((HourlyEmployee) e).setWorkingHours(workingHours);
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setBirthDate(birthDate);
            e.setPhone(phone);
            e.setEmail(email);
        } else if (e instanceof SalariedEmployee) {
            //Salary employee

            double commissionRate;
            double grossSales;
            double basicSalary;
            commissionRate = InputService.inputDouble("Commission rate: ", 0, Double.MAX_VALUE);
            grossSales = InputService.inputDouble("Gross sales: ", 0, Double.MAX_VALUE);
            basicSalary = InputService.inputDouble("Basic salary: ", 0, Double.MAX_VALUE);
            ((SalariedEmployee) e).setCommissionRate(commissionRate);
            ((SalariedEmployee) e).setGrossSales(grossSales);
            ((SalariedEmployee) e).setBasicSalary(basicSalary);
            e.setFirstName(firstName);
            e.setLastName(lastName);
            e.setBirthDate(birthDate);
            e.setPhone(phone);
            e.setEmail(email);
        }
    }

    //DELETE EMPLOYEE
    public static void removeEmployee() {
        refreshGeneralList();
        if (generalList.isEmpty()) {
            System.out.println("There is nothing to update");
            return;
        }
        String ssn = InputService.inputString("SSN: ");
        int i = findSsn(ssn);
        if (i == -1) {
            System.out.println("Employee not found");
            return;
        }

        Vector<Employee> employeeList;
        Iterator itr;
        for (Department de : departmentList) {
            employeeList = de.getEmployeeList();
            itr = employeeList.iterator();
            while (itr.hasNext()) {
                if (((Employee) itr.next()).getSsn().equals(ssn)) {
                    itr.remove();
                    DepartmentService.removeEmptyDepartment();
                    System.out.println("Employee removed sucessfully");
                    return;
                }
            }
        }
    }
}
