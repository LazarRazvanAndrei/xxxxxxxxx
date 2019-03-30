package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Client;

import java.util.List;

public class ClientWriter implements ConsoleWriter<Client> {
    public void write(Client client) {
        System.out.println("Id: " + client.getId());
        System.out.println("Name: " + client.getName());
        System.out.println("Phone number: " + client.getPhoneNumber());
        System.out.println("Address: " + client.getAdress());
        System.out.println("Social ID: " + client.getSocialId());
    }

    void writeAll(List<Client> clients) {
        if (clients.size() == 0) {
            System.out.println("No clients available");
        } else {
            System.out.println("Clients list: ");
            for (Client client : clients) {
               writeSummary(client);
            }
        }
    }

    private void writeSummary(Client client) {
        System.out.print("Id: " + client.getId());
        System.out.print(", Name: " + client.getName());
        System.out.print("Phone number: " + client.getPhoneNumber());
        System.out.print("Address: " + client.getAdress());
        System.out.print("Social ID: " + client.getSocialId());
    }
}
