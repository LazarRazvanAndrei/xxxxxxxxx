package ro.sda.dealership.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);
    private static final Integer MAX_RETRIES = 2;

    public static Long readLong(String item){
        Long result = null;
        int retries = 0;

        while (retries <= MAX_RETRIES && result == null){
            try {
                System.out.println();
                System.out.print(item + " ID: ");
                result = scanner.nextLong();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Invalid " + item + " id. Please, retry!");
            }
            retries ++;
        }
        return  result;
    }

    public  static Double getPrice(){
        Double price = 0d;
        while (true){
            try {
                price = scanner.nextDouble();
            }catch (InputMismatchException e){
                scanner.nextLine();
            }
            if (price <= 0)
                System.out.println("Incorrect price. Insert again: ");
            else break;
        }
        return price;
    }
}
