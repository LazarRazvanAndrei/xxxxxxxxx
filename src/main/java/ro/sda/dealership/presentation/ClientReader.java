package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Client;

import java.util.Scanner;

public class ClientReader implements ConsoleReader<Client> {

    public Client read() {
        Client client = new Client();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Client name: ");
        String name = scanner.nextLine();
        System.out.print("Client phone number: ");
        String phoneNumber = scanner.nextLine().trim();
        System.out.println("Social ID: ");
        String socialId = scanner.nextLine().trim();
        System.out.println("Address: ");
        String address = scanner.nextLine();
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        client.setSocialId(socialId);
        client.setAdress(address);
        return client;
    }
}
