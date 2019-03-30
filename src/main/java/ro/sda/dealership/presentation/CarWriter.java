package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Car;

import java.util.List;

public class CarWriter implements ConsoleWriter<Car> {
    public void write(Car car){
        System.out.println("Id: " + car.getId());
        System.out.println("Car model: " + car.getCarModel());
        System.out.println("Car color: " + car.getCarColor());
        System.out.println("Car price: " + car.getCarPrice());
    }

    public void writeAll(List<Car> cars){
        if(cars.size() == 0){
            System.out.println("No cars available");
        }else{
            System.out.println("Cars list: ");
            for(Car car : cars){
                writeSummary(car);
            }
        }
    }

    private void writeSummary(Car car){
        System.out.print("Id: " + car.getId());
        System.out.print(", Car model: " + car.getCarModel());
        System.out.println(", Car color: " + car.getCarColor());
        System.out.println("Car price: " + car.getCarPrice());
    }
}
