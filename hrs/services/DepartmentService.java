package hrs.services;

import hrs.entities.Department;
import hrs.entities.Employee;
import hrs.entities.HourlyEmployee;
import hrs.entities.SalariedEmployee;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

public class DepartmentService {

    private static Vector<Department> departmentList;

    public static void setDepartmentList(Vector<Department> list) {
        departmentList = list;
    }

    //FIND DEPARTMENT RETURN INDEX IN THE LIST
    public static int findDepartment(String name) {
        for (int i = 0; i < departmentList.size(); i++) {
            if (departmentList.get(i).getDepartmentName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    //ADD A EMPLOYEE TO A DEPARTMENT
    public static void addToDepartment(String deName, Employee e) {
        int i = findDepartment(deName);
        if (i == -1) {
            departmentList.add(new Department(deName));
            i = departmentList.size() - 1;
        }
        departmentList.get(i).getEmployeeList().add(e);
    }

    //REMOVE EMPTY DEPARTMENTS
    public static void removeEmptyDepartment() {
        Iterator i = departmentList.iterator();
        while (i.hasNext()) {
            if (((Department) i.next()).getEmployeeList().isEmpty()) {
                i.remove();
            }
        }
    }

    //DISPLAY A DEPARTMENT
    public static void displayDepartment(String deName) {
        int i = findDepartment(deName);
        if (i == -1) {
            System.out.println("Department not found!");
            return;
        }
        departmentList.get(i).display();
    }

    //REPORT
    public static void report() {
        if (departmentList.isEmpty()) {
            System.out.println("There is no department");
            return;
        }
        for (Department de : departmentList) {
            System.out.println("____________________");
            System.out.println("Department: " + de.getDepartmentName());
            System.out.println("Number of employees: " + de.getEmployeeList().size());
        }
    }

    //SAVE DATA TO FILE
    public static void SaveToFile() {

        try {
            File f = new File("data.txt");
            try (FileWriter fw = new FileWriter(f); PrintWriter pw = new PrintWriter(fw)) {
                if (departmentList.isEmpty()) {
                    pw.print("");
                    return;
                }
                Vector<Employee> list;
                for (Department de : departmentList) {
                    pw.println("@" + de.getDepartmentName());
                    list = de.getEmployeeList();
                    for (Employee e : list) {
                        if (e instanceof HourlyEmployee) {
                            pw.println("Hourly;" + e.getSsn() + ";" + e.getFirstName()
                                    + ";" + e.getLastName() + ";" + e.getBirthDate()
                                    + ";" + e.getPhone() + ";" + e.getEmail() + ";"
                                    + ((HourlyEmployee) e).getRate() + ";"
                                    + ((HourlyEmployee) e).getWorkingHours()
                            );
                        } else if (e instanceof SalariedEmployee) {
                            pw.println("Salaried;" + e.getSsn() + ";" + e.getFirstName()
                                    + ";" + e.getLastName() + ";" + e.getBirthDate()
                                    + ";" + e.getPhone() + ";" + e.getEmail() + ";"
                                    + ((SalariedEmployee) e).getCommissionRate() + ";"
                                    + ((SalariedEmployee) e).getGrossSales() + ";"
                                    + ((SalariedEmployee) e).getBasicSalary()
                            );
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
