package hrs.services;

import java.util.Scanner;

public class InputService {
    //HANDLE INPUT EXCEPTION
    private static Scanner sc = new Scanner(System.in);
    //ENTER INTEGER WITH MIN MAX BOUND
    public static int inputInt(String message, int min, int max) {
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
            }
        }
        return value;
    }
    //ENTER INTEGER
    public static int inputInt(String message) {
        boolean isValid = false;
        int value = 0;
        while (!isValid) {
            System.out.println(message);
            try {
                value = Integer.parseInt(sc.nextLine());
                isValid = true;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return value;
    }
    //ENTER DOUBLE WITH MIN MAX BOUND
    public static double inputDouble(String message, double min, double max) {
        boolean isValid = false;
        double value = 0;
        while (!isValid) {
            System.out.print(message);
            try {
                value = Double.parseDouble(sc.nextLine());
                if (value >= min && value <= max) {
                    isValid = true;
                } else {
                    System.out.println("Value is out of bound!");
                }
            } catch (Exception e) {
                System.out.println("Value is not valid!");
            }
        }
        return value;
    }
    //ENTER NON EMPTRY STRING
    public static String inputString(String message) {
        boolean isValid = false;
        String value = "";
        while (!isValid) {
            System.out.print(message);
            value = sc.nextLine();
            if (!value.isEmpty()) {
                isValid = true;
            } else {
                System.out.println("Value can not be empty!");
            }
        }
        return value;
    }
}
