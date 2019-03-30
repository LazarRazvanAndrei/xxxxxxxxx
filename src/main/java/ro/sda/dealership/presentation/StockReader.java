package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Car;
import ro.sda.dealership.model.Stock;
import ro.sda.dealership.storage.CarDAO;

import java.util.Scanner;

public class StockReader implements ConsoleReader<Stock> {
    private CarDAO carDAO = new CarDAO();

    public Stock read() {
        if (carDAO.findAll().isEmpty()) {
            return null;
        }
        Stock stock = new Stock();
        Car car;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Select car id for stock to be added: ");
            Long carID = scanner.nextLong();
            car = carDAO.findById(carID);
        } while (car == null);
        System.out.println("Car quantity: ");
        Integer quantity = scanner.nextInt();
        System.out.println("Car location: ");
        String location = scanner.nextLine();
        stock.setCar(car);
        stock.setQuantity(quantity);
        stock.setLocation(location);
        return stock;
    }
}