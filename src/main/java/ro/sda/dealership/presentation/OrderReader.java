package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Car;
import ro.sda.dealership.model.Client;
import ro.sda.dealership.model.Order;
import ro.sda.dealership.model.OrderStatus;
import ro.sda.dealership.storage.CarDAO;
import ro.sda.dealership.storage.ClientDAO;

import java.sql.Timestamp;
import java.util.Scanner;

public class OrderReader implements ConsoleReader<Order> {
    private ClientDAO clientDAO = new ClientDAO();
    private CarDAO carDAO = new CarDAO();

    public Order read() {
        if (clientDAO.findAll().isEmpty() || carDAO.findAll().isEmpty()) {
            return null;
        }
        Order order = new Order();
        new ClientWriter().writeAll(clientDAO.findAll());
        Client selectedClient = null;
        do{
            System.out.println("Select client for order to be added: ");
            selectedClient = clientDAO.findById(ConsoleUtil.readLong("Client"));
        } while (selectedClient == null);

        new ClientWriter().writeAll(clientDAO.findAll());
        Car selectedCar = null;
        do {
            System.out.println("Select car id for order to be added: ");
            selectedCar = carDAO.findById(ConsoleUtil.readLong("Car"));
        } while (selectedCar == null);

        System.out.print("Insert the new price: ");
        Double newOrderPrice = ConsoleUtil.getPrice();

        Timestamp orderDate = new Timestamp(System.currentTimeMillis());

        System.out.println("Agent name: ");
        String agentName = new Scanner(System.in).nextLine();

        OrderStatus newOrderStatus = null;
        do {
        System.out.println("Enter new status (1 = ACCEPTED, 2 = PLACED, 3 = PAYED, 4 = DELIVERED, 5 = CANCELED): ");
        int newOrderCode = new Scanner(System.in).nextInt();
        switch (newOrderCode) {
            case 1:
                newOrderStatus = OrderStatus.ACCEPTED;
                break;
            case 2:
                newOrderStatus = OrderStatus.PLACED;
                break;
            case 3:
                newOrderStatus = OrderStatus.PAYED;
                break;
            case 4:
                newOrderStatus = OrderStatus.DELIVERED;
                break;
            case 5:
                newOrderStatus = OrderStatus.CANCELED;
                break;
        }
        } while (newOrderStatus == null);

        order.setClient(selectedClient);
        order.setCar(selectedCar);
        order.setPrice(newOrderPrice);
        order.setStatus(newOrderStatus);

        return order;
    }

}
