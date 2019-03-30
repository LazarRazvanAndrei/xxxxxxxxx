package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Car;
import ro.sda.dealership.model.Client;
import ro.sda.dealership.model.Order;
import ro.sda.dealership.model.OrderStatus;
import ro.sda.dealership.storage.CarDAO;
import ro.sda.dealership.storage.ClientDAO;
import ro.sda.dealership.storage.OrderDAO;

import java.util.Scanner;

public class OrderMenu extends AbstractMenu {
    private OrderDAO orderDAO = new OrderDAO();
    private ClientDAO clientDAO = new ClientDAO();
    private CarDAO carDAO = new CarDAO();
    private OrderReader reader = new OrderReader();
    private OrderWriter writer = new OrderWriter();

    protected void displayOption() {
        System.out.println("1. View all orders");
        System.out.println("2. View order details");
        System.out.println("3. Edit order");
        System.out.println("4. Add new order");
        System.out.println("5. Delete order");
        System.out.println("6. Search order");
        System.out.println("0. Exit");
    }

    protected void executeComand(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(orderDAO.findAll());
                break;
            case 2:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available.");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.println("Select order to view: ");
                    displayOrderDetails();
                }
                break;
            case 3:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available.");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.print("Select order to edit: ");
                    editOrder();
                }
                break;
            case 4:
                Order newOrder = reader.read();
                if (newOrder == null) {
                    System.out.println("No order available");
                } else {
                    orderDAO.add(newOrder);
                    System.out.println("Order added");
                }
                break;
            case 5:
                if (orderDAO.findAll().isEmpty()) {
                    System.out.println("No orders available.");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.print("Select order to delete: ");
                    boolean isDeleted = orderDAO.deleteById(ConsoleUtil.readLong("Order"));
                    if (!isDeleted) {
                        System.out.println("Order not found");
                    } else {
                        System.out.println("Order deleted");
                    }
                }
                break;
            case 6:
                if(orderDAO.findAll().isEmpty()){
                    System.out.println("No orders available.");
                } else {
                    writer.writeAll(orderDAO.findAll());
                    System.out.println("Select order to view: ");
                    displayOrderByClient();
                }
                break;
            case 0:
                System.out.println("Exiting to Main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void editOrder() {
        System.out.println("Select order to edit");
        Scanner scanner = new Scanner(System.in);
        Order foundOrder = orderDAO.findById(ConsoleUtil.readLong("Order"));
        if (foundOrder == null) {
            System.out.println("Order not found");
        } else {
            Client foundClient = clientDAO.findById(ConsoleUtil.readLong("Client"));
            if (foundClient == null) {
                System.out.println("Client not found");
            } else {
                Car foundCar = carDAO.findById(ConsoleUtil.readLong("Car"));
                if (foundCar == null) {
                    System.out.println("Car not found");
                } else {
                    System.out.println("Enter the new price: ");
                    Double newOrderPrice = scanner.nextDouble();
                    if (newOrderPrice < 0) {
                        System.out.println("Price error !");
                    } else {
                        System.out.println("Enter new status (1 = ACCEPTED, 2 = PLACED, 3 = PAYED, 4 = DELIVERED, 5 = CANCELED): ");
                        int newOrderCode = scanner.nextInt();  // de gandit cum sa facem sa citeasca in format STATUS
                        OrderStatus newOrderStatus = null;
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
                        if (newOrderStatus == null) {
                            System.out.println("Order status not found");
                        } else {
                            foundOrder.setClient(foundClient);
                            foundOrder.setCar(foundCar);
                            foundOrder.setPrice(newOrderPrice);
                            foundOrder.setStatus(newOrderStatus);
                            orderDAO.update(foundOrder);
                            System.out.println("Order updated");
                        }
                    }
                }
            }
        }
    }

    private void displayOrderDetails() {
        System.out.println("Choose order by Id: ");
        Long id = ConsoleUtil.readLong("Order");
        Order searchedOrder = orderDAO.findById(id);
        if (searchedOrder == null) {
            System.out.println("Order not found");
        } else {
            System.out.println("Order details are: ");
            writer.write(searchedOrder);
        }
    }

    private void displayOrderByClient(){
        System.out.println("Search order by Client: ");
        Order searchedOrder = orderDAO.findById(ConsoleUtil.readLong("Order"));
        if(searchedOrder == null){
            System.out.println("Order not found");
        }else{
            System.out.println("The searched order is: ");
            writer.write(searchedOrder);
        }
    }
}