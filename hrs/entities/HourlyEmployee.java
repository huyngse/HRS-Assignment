package hrs.entities;

import hrs.services.InputService;

public class HourlyEmployee extends Employee {

    //ATTRIBUTE
    private double rate;
    private double workingHours;

    //GETTER AND SETTER
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    //CONSTRUCTOR
    public HourlyEmployee() {
    }

    public HourlyEmployee(double rate, double workingHours, String ssn,
            String firstName, String lastName, String birthDate, String phone,
            String email) {
        super(ssn, firstName, lastName, birthDate, phone, email);
        this.rate = rate;
        this.workingHours = workingHours;
    }

    //OVERRIDE
    @Override
    public void display() {
        super.display();
        System.out.println("Rate: " + rate);
        System.out.println("Working hours: " + workingHours);
    }

    @Override
    public void input() {
        super.input();
        rate = InputService.inputDouble("Rate: ", 0, Double.MAX_VALUE);
        workingHours = InputService.inputDouble("Working hours: ", 0, Double.MAX_VALUE);
    }

}
