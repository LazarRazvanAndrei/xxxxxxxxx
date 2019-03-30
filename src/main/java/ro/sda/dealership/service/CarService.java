package ro.sda.dealership.service;

import ro.sda.dealership.storage.CarDAO;
import ro.sda.dealership.model.Car;

import java.time.LocalDate;
import java.util.List;

public class CarService {

    private CarDAO carDAO = new CarDAO();

    public CarService() {
        carDAO = new CarDAO();
    }

    public void initializeDao() {
        carDAO.initialize();
    }

    public List<Car> getAllCars() {
        return carDAO.findAll();
    }

    public Car getCar(Long id) {
        return carDAO.findById(id);
    }

    public void save(Car car) {
        if (car.getId() != null) {
            carDAO.addCar(car);
        }
    }


    public void delete(Long id) {
        carDAO.deleteById(id);
    }


    public void update(Car car) {
        carDAO.update(car);
    }

    public void findById(Long id){
        carDAO.findById(2L);
    }


}



