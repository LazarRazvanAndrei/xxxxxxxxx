package ro.sda.dealership.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AbstractMenu {

    public void displayMenu() {
        Integer option = Integer.MAX_VALUE;
        while (option != 0) {
            displayOption();
            option = readOption();
            executeComand(option);
        }
    }

    protected abstract void displayOption();//Specific petru fiecare menu

    protected abstract void executeComand(Integer option);

    private Integer readOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your option is: ");
        try {
            return scanner.nextInt();
        } catch(InputMismatchException e){
            return -1;
        }
    }
}
