package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Client;
import ro.sda.dealership.model.Order;
import ro.sda.dealership.storage.ClientDAO;
import java.util.Scanner;

public class ClientMenu extends AbstractMenu {
    private ClientDAO clientDAO = new ClientDAO();
    private ClientReader reader = new ClientReader();
    private ClientWriter writer = new ClientWriter();

    protected void displayOption() {
        System.out.println("1.View all clients");
        System.out.println("2.View client details");
        System.out.println("3.Edit client");
        System.out.println("4.Add new client");
        System.out.println("5.Delete client");
        System.out.println("6.Search client");
        System.out.println("0.Exit");
    }

    protected void executeComand(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(clientDAO.findAll());
                break;
            case 2:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available.");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.println("Select client to view: ");
                    displayClientDetails();
                }
                break;
            case 3:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available.");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.print("Select client to edit: ");
                    editClient();
                }
                break;
            case 4:
                Client newClient = reader.read();
                clientDAO.add(newClient);
                System.out.println("Client added");
                break;
            case 5:
                if (clientDAO.findAll().isEmpty()) {
                    System.out.println("No clients available.");
                } else {
                    writer.writeAll(clientDAO.findAll());
                    System.out.print("Select a client to delete: ");
                    Long id = ConsoleUtil.readLong("Client");
                    boolean isDeleted = clientDAO.deleteById(id);
                    if (!isDeleted) {
                        System.out.println("Client not found");
                    } else {
                        System.out.println("Client deleted");
                    }
                }
                break;
            case 0:
                System.out.println("Exiting to Main menu");
                break;
            default:
                System.out.println("Invalid option");
        }
    }

    private void editClient() {
        Scanner scanner = new Scanner(System.in);
        Client foundClient = clientDAO.findById(ConsoleUtil.readLong("Client"));
        if (foundClient == null) {
            System.out.println("Client not found");
        } else {
            System.out.println("Enter the new name: ");
            foundClient.setName(scanner.next());
            System.out.println("Enter the new phone number: ");
            String phoneNumber = scanner.next().trim();
            foundClient.setPhoneNumber(phoneNumber);
            System.out.print("Enter new adress: ");
            foundClient.setAdress(scanner.nextLine());
            clientDAO.update(foundClient);
            System.out.println("Client updated");
        }
    }

    private void displayClientDetails() {
        Client foundClient = clientDAO.findById(ConsoleUtil.readLong("Client"));
        if (foundClient == null) {
            System.out.println("Client not found");
        } else {
            System.out.println("Client details are: ");
            writer.write(foundClient);
        }
    }
}

