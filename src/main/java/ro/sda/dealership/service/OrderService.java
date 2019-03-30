package ro.sda.dealership.service;

import ro.sda.dealership.model.Car;
import ro.sda.dealership.storage.OrderDAO;
import ro.sda.dealership.model.Order;
import ro.sda.dealership.model.Client;
import ro.sda.dealership.model.OrderStatus;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static ro.sda.dealership.model.OrderStatus.*;


public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private StockService stockService = new StockService();

    public List<Order> getAllOrders(){
        return orderDAO.findAll();
    }

    public List<Order> getOrdersForClient(Long clientId){
        return orderDAO.findAllByClientId(clientId);
    }

    public List<Order> getOrdersByClient(String clientName){
        return orderDAO.findAllByClientName(clientName);
    }

    public List<Order> getOrdersBetweenDates(Timestamp start, Timestamp end) {
        return orderDAO.findAllBetweenDates(start, end);
    }

    public Order save(Order order){
        Order updatedOrder = null;
        if(order.getId() == null){
            updatedOrder = orderDAO.add(order);
        }else{
            orderDAO.update(order);
            updatedOrder = order;
        }
        return updatedOrder;
    }

    public boolean carInStock(Car car){
        if(!stockService.isInStock(car)){
            return false;
        }
        return true;
    }

    public void placeOrder(Client client, Car car){
        Order order = new Order();
        if(carInStock(car)){
            order.setClient(client);
            order.setCar(car);
            order.setPrice(car.getCarPrice());
            order.setOrderDate(new Timestamp(new Date().getTime()*1000000));
            order.setStatus(PLACED);
            save(order);
        }else{
            throw new ProductNotInStockException("The car is not in stock.");
        }
    }

    public void acceptOrder(Order order){
        order.setStatus(ACCEPTED);
        save(order);
    }

    public void deliverOrder(Order order){
        order.setStatus(DELIVERED);
        save(order);
    }

    public void payOrder(Order order){
        order.setStatus(PAYED);
        save(order);
    }

    public void cancelOrder(Order order){
        switch(order.getStatus()){
            case PLACED:
            case ACCEPTED:
            case DELIVERED:
                returnToStock(order);
                break;
            case PAYED:
                returnToStock(order);
                returnMoney(order);
                break;
        }
        order.setStatus(OrderStatus.CANCELED);
        save(order);
    }

    private void returnToStock(Order order){
        stockService.returnToStock(order.getCar());
    }

    private void returnMoney(Order order){
        //Return money
    }


}
