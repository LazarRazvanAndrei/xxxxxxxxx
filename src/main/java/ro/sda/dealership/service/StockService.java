package ro.sda.dealership.service;

import ro.sda.dealership.storage.StockDAO;
import ro.sda.dealership.model.Stock;
import ro.sda.dealership.model.Car;

public class StockService {
    private StockDAO stockDAO = new StockDAO();

    public void addCarToStock(Car car, int quantity) {
        addCarToStock(car, quantity, Stock.DEFAULT_LOCATION);
    }

    public void addCarToStock(Car car, int quantity, String location) {
        Stock stock = stockDAO.findByCarIdAndLocation(car.getId(), location);
        if (stock == null) {
            stock = new Stock(car, 1, location);
        }
        save(stock);
    }

    public Stock save(Stock stock) {
        Stock updatedStock = null;
        if (stock.getId() == null) {
            updatedStock = stockDAO.add(stock);
        } else {
            stockDAO.update(stock);
            updatedStock = stock;
        }
        return updatedStock;
    }

    public boolean isInStock(Car car) {
       return isInStock(car, Stock.DEFAULT_LOCATION);
    }


    public boolean isInStock(Car car, String location){
        Stock stock = stockDAO.findByCarIdAndLocation(car.getId(), location);
        if(stock != null && stock.getQuantity() > 0){
            return true;
        }
        return false;
    }

    public void deliverFromStock(Car car, String location, int quantity){
        Stock stock = stockDAO.findByCarIdAndLocation(car.getId(), location);
        if(stock != null && stock.getQuantity() >= quantity){
            int newQuantity = stock.getQuantity() - quantity;
            stock.setQuantity(newQuantity);
            save(stock);
        }else{
            throw new NotFoundException("Car not in stock.");
        }
    }

    public void deliverFromStock(Car car){
        deliverFromStock(car, Stock.DEFAULT_LOCATION, 1);
    }

    public void returnToStock(Car car, String location, int quantity){
        Stock stock = stockDAO.findByCarIdAndLocation(car.getId(), location);
        if(stock != null){
            int newQuantity = stock.getQuantity() + quantity;
            stock.setQuantity(newQuantity);
            save(stock);
        }else{
            throw new NotFoundException("Car not in stock.");
        }
    }

    public void returnToStock(Car car){
        returnToStock(car,Stock.DEFAULT_LOCATION, 1);
    }
}



