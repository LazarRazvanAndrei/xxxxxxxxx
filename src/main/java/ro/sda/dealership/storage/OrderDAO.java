package ro.sda.dealership.storage;

import ro.sda.dealership.model.Order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends GenericDAO<Order> {
    private static List<Order> orders = new ArrayList<Order>();

    @Override
    protected List<Order> getItems() {
        return orders;
    }

    public List<Order> findAllByClientId(Long clientId){
        List<Order> orders = new ArrayList<Order>();
        for (Order order : getItems()){
            if(order.getClient().getId().equals(clientId)){
                orders.add(order);
            }
        }
        return orders;
    }

    public List<Order> findAllByClientName(String clientName){
        List<Order> orders = new ArrayList<Order>();
        for (Order order : getItems()){
            if(order.getClient().getName().equals(clientName)){
                orders.add(order);
            }
        }
        return orders;
    }

    public List<Order> findAllBetweenDates(Timestamp start, Timestamp end){
        List<Order> orders = new ArrayList<Order>();
        for (Order order : getItems()){
            if(isBetween(order, start, end)){
                orders.add(order);
            }
        }
        return orders;
    }

    private boolean isBetween(Order order, Timestamp start, Timestamp end){
        return order.getOrderDate().after(start) && order.getOrderDate().before(end);
    }
}
