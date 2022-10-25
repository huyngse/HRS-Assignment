package hrs.utils;

import hrs.entities.Department;
import hrs.entities.Employee;
import java.util.StringTokenizer;
import java.util.Vector;

public class EmployeeValidation {

    //SET ONE DEPARTMENT TO USE

    private static Vector<Department> departmentList;

    public static void setDepartmentList(Vector<Department> list) {
        departmentList = list;
    }

    //CHECK DATE MATCHES THE FORMAT
    public static boolean isValidDate(String s) {
        //DD/MM/YYYY
        if (!s.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
            return false;
        }
        byte day;
        byte month;
        short year;
        StringTokenizer stk = new StringTokenizer(s, "/");
        day = Byte.parseByte(stk.nextToken());
        month = Byte.parseByte(stk.nextToken());
        year = Short.parseShort(stk.nextToken());
        if (day < 1) {
            return false;
        }

        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                if (day <= 31) {
                    return true;
                }
                break;
            }
            case 4:
            case 6:
            case 9:
            case 11: {
                if (day <= 30) {
                    return true;
                }
                break;
            }
            case 2: {
                if (day <= 28) {
                    return true;
                } else if ((day <= 29) && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    //CHECK PHONE NUMBER
    public static boolean isValidPhone(String s) {
        //at least 7 digits
        return s.matches("[0-9]{7,}");
    }

    //CHECK EMAIL FORMAT
    public static boolean isValidEmail(String s) {
        //[Recipient name]@[Domain name].[Top-level  domain]
        return s.matches("[a-zA-Z0-9]{1,64}@[a-zA-Z]+\\.[a-zA-Z]+(\\.[a-zA-Z]+)?");
        // huyngse173222@fpt.edu.vn
        // [huyngse173222]   @   [fpt]   .   [edu]   (.   [vn])?
        //([a-zA-Z]||([.][a-zA-Z]))+
    }

    //CHECK UNIQUE SSN
    public static boolean isValidSSN(String s) {
        Vector<Employee> employeeList;
        //Check each department
        for (Department de : departmentList) {
            employeeList = de.getEmployeeList();
            //Check each employee
            for (Employee x : employeeList) {
                if (x.getSsn().equals(s)) {
                    return false;
                }
            }
        }
        return true;
    }
}
