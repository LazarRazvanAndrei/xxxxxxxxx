package ro.sda.dealership.presentation;

public class MainMenu extends AbstractMenu {

    protected void displayOption() {
        System.out.println("Main Menu");
        System.out.println("1. Cars");
        System.out.println("2. Clients");
        System.out.println("3. Orders");
        System.out.println("4. Stocks");
        System.out.println("0. Exit");
    }

    protected void executeComand(Integer option) {
        switch (option) {
            case 1:
                System.out.println("Cars menu");
                CarMenu productMenu = new CarMenu();
                productMenu.displayMenu();
                break;
            case 2:
                System.out.println("Clients menu");
                ClientMenu clientMenu = new ClientMenu();
                clientMenu.displayMenu();
                break;
            case 3:
                System.out.println("Orders menu");
                OrderMenu orderMenu = new OrderMenu();
                orderMenu.displayMenu();
                break;
            case 4:
                System.out.println("Stocks menu");
                StockMenu stockMenu = new StockMenu();
                stockMenu.displayMenu();
                break;
            case 0:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid option");
        }
    }
}
