package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Car;
import ro.sda.dealership.service.CarService;
import ro.sda.dealership.storage.CarDAO;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarMenu extends AbstractMenu {
    private CarDAO carDAO = new CarDAO();
    private CarReader reader = new CarReader();
    private CarWriter writer = new CarWriter();


    protected void displayOption() {
        System.out.println("1. View all cars");
        System.out.println("2. View car details");
        System.out.println("3. Edit car");
        System.out.println("4. Add new car");
        System.out.println("5. Delete car");
        System.out.println("0. Exit");
    }

    protected void executeComand(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(carDAO.findAll());
                break;
            case 2:
                if (carDAO.findAll().isEmpty()) {
                    System.out.println("No cars available.");
                } else {
                    writer.writeAll(carDAO.findAll());
                    System.out.print("Select car to view: ");
                    displayCarDetails();
                }
                break;
            case 3:
                if (carDAO.findAll().isEmpty()) {
                    System.out.println("No cars available.");
                } else {
                    writer.writeAll(carDAO.findAll());
                    System.out.print("Select car to edit: ");
                    editCar();
                }
                break;
            case 4:
               Car newCar = reader.read();
               carDAO.addCar(newCar);
                break;
            case 5:
                if (carDAO.findAll().isEmpty()) {
                    System.out.println("No cars available.");
                } else {
                    writer.writeAll(carDAO.findAll());
                    System.out.print("Select car to delete: ");
                    Long id = ConsoleUtil.readLong("Car");
                    carDAO.deleteById(id);
                }
            case 0:
                System.out.println("Exiting to Main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void editCar(){
        System.out.println("Select car to edit");
        Scanner scanner = new Scanner(System.in);
        Long id = ConsoleUtil.readLong("Car");
        Car foundCar = carDAO.findById(id);
        if (foundCar == null) {
            System.out.println("Car not found");
        } else {
            System.out.println("Enter new model: ");
            foundCar.setCarModel(scanner.next());
            System.out.println("Enter new color: ");
            foundCar.setCarColor(scanner.next());
            System.out.print("Enter new price: ");
            try {
                foundCar.setPriceCar(scanner.nextDouble());
            } catch (InputMismatchException e) {
                System.out.println("Price not changed");
            }
            carDAO.update(foundCar);
            System.out.println("Car updated");
        }
    }

    private void displayCarDetails(){
        System.out.println("Choose car by Id: ");
        Long id = ConsoleUtil.readLong("Car");
        Car searchedCar = carDAO.findById(id);
        if (searchedCar == null) {
            System.out.println("Car not found");
        } else {
            System.out.println("Car details: ");
            writer.write(searchedCar);
        }
    }

    public static void main(String[] args) {


        CarService service = new CarService();
        service.initializeDao();

        Car newCar = new Car();
        newCar.setId(2L);
        newCar.setCarColor("galbena");
        newCar.setCarModel("break");
//        service.save(newCar);
//
//        List<Car> cars  =service.getAllCars();
//        for (Car car: cars){
//            System.out.println(car.getId() + " Culoare: " + car.getCarColor()+ " model" + car.getCarModel());
//        }
        newCar.setCarColor("rosu");

        //service.update(newCar);
        //service.delete(2L);
        //service.update(newCar);
        service.getAllCars();
    }
}
