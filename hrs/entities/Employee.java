package hrs.entities;

import hrs.services.InputService;
import hrs.utils.EmployeeValidation;

public abstract class Employee {
    //ATTRIBUTE
    private String ssn;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String phone;
    private String email;
    
    //FUNCTIONS
    public void display() {
        System.out.println("SSN: " + ssn);
        System.out.println("First name: " + firstName);
        System.out.println("Last name: " + lastName);
        System.out.println("Birth date: " + birthDate);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }
    public void input() {
        boolean isValid;
        //SSN
        do {
            ssn = InputService.inputString("SSN: ");
            isValid = EmployeeValidation.isValidSSN(ssn);
            if (!isValid) {
                System.out.println("SSN is already taken by other employee");
            }
        } while (!isValid);
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
    }
    
    //OVERRIDE
    @Override
    public String toString() {
        return "[" + ssn + ", " + firstName + " " + lastName + ", " + birthDate
                + ", " + phone + ", " + email + "]"; 
    }
    
    //GETTER AND SETTER
    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // CONSTRUCTOR
    public Employee() {
    }
    
    public Employee(String ssn, String firstName, String lastName) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String ssn, String firstName, String lastName, String birthDate, String phone, String email) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
    }

}
