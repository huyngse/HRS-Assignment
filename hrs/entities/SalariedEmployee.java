package hrs.entities;

import hrs.services.InputService;

public class SalariedEmployee extends Employee {

    //ATTRIBUTE
    private double commissionRate;
    private double grossSales;
    private double basicSalary;

    //GETTER AND SETTER
    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    //CONSTRUCTOR
    public SalariedEmployee() {
    }

    public SalariedEmployee(double commissionRate, double grossSales,
            double basicSalary, String ssn, String firstName, String lastName,
            String birthDate, String phone, String email) {
        super(ssn, firstName, lastName, birthDate, phone, email);
        this.commissionRate = commissionRate;
        this.grossSales = grossSales;
        this.basicSalary = basicSalary;
    }

    //OVERRIDE
    @Override
    public void display() {
        super.display();
        System.out.println("Commission rate: " + commissionRate);
        System.out.println("Gross sales: " + grossSales);
        System.out.println("Basic Salary: " + basicSalary);
    }

    @Override
    public void input() {
        super.input();
        commissionRate = InputService.inputDouble("Commission rate: ", 0, Double.MAX_VALUE);
        grossSales = InputService.inputDouble("Gross sales: ", 0, Double.MAX_VALUE);
        basicSalary = InputService.inputDouble("Basic salary: ", 0, Double.MAX_VALUE);
    }

}
