package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Order;

import java.util.List;

public class OrderWriter implements ConsoleWriter<Order> {
    public void write(Order order){
        System.out.println("Order Id: " + order.getId());
        System.out.println("Client Id: " + order.getClient().getId());
        System.out.println("Client name: " + order.getClient().getName());
        System.out.println("Car model: " + order.getCar().getCarModel());
        System.out.println("Car color: " + order.getCar().getCarColor());
        System.out.println("Order date: " + order.getOrderDate());
        System.out.println("Price: " + order.getPrice());
        System.out.println("Order Status: " + order.getStatus());
    }

    void writeAll(List<Order> orders){
        if(orders.size() == 0){
            System.out.println("No orders available");
        }else{
            System.out.println("Order list: ");
            for(Order order : orders){
                writeSummary(order);
            }
        }
    }

    private void writeSummary(Order order){
        System.out.print("Order Id: " + order.getId());
        System.out.print(", Client Id: " + order.getClient().getId());
        System.out.print(", Client name: " + order.getClient().getName());
        System.out.print(", Car model: " + order.getCar().getCarModel());
        System.out.print(", Car color: " + order.getCar().getCarColor());
        System.out.print(", Order date: " + order.getOrderDate());
        System.out.print(", Order price: " + order.getPrice());
        System.out.println(", Order Status: " + order.getStatus());
    }
}
