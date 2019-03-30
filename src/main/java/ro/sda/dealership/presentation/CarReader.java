package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Car;

import java.util.Scanner;

public class CarReader implements ConsoleReader<Car> {

    public Car read(){
        Car car = new Car();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Car model: ");
        String model = scanner.nextLine();
        System.out.println("Car color: ");
        String color = scanner.nextLine();
        System.out.println("Car price: ");
        Double price = ConsoleUtil.getPrice();
        car.setCarModel(model);
        car.setCarColor(color);
        car.setPriceCar(price);
        return car;
    }
}
