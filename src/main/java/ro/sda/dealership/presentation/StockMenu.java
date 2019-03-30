package ro.sda.dealership.presentation;

import ro.sda.dealership.model.Stock;
import ro.sda.dealership.storage.StockDAO;

import java.util.Scanner;

public class StockMenu extends AbstractMenu {
    private StockDAO stockDAO = new StockDAO();
    private StockReader reader = new StockReader();
    private StockWriter writer = new StockWriter();

    protected void displayOption() {
        System.out.println("1. View all stocks");
        System.out.println("2. View stock details");
        System.out.println("3. Edit stock");
        System.out.println("4. Add new stock");
        System.out.println("5. Delete stock");
        System.out.println("0. Exit");
    }

    protected void executeComand(Integer option) {
        switch (option) {
            case 1:
                writer.writeAll(stockDAO.findAll());
                break;
            case 2:
                if (stockDAO.findAll().isEmpty()) {
                    System.out.println("No stocks available.");
                } else {
                    writer.writeAll(stockDAO.findAll());
                    System.out.print("Select stock to view: ");
                    displayStockDetails();
                }
                break;
            case 3:
                if (stockDAO.findAll().isEmpty()) {
                    System.out.println("No stocks available.");
                } else {
                    writer.writeAll(stockDAO.findAll());
                    System.out.print("Select stock to edit: ");
                    editStock();
                }
                break;
            case 4:
                Stock newStock = reader.read();
                if (newStock == null) {
                    System.out.println("No cars available");
                } else {
                    stockDAO.add(newStock);
                    System.out.println("Stock added");
                }
                break;
            case 5:
                if (stockDAO.findAll().isEmpty()) {
                    System.out.println("No stocks available.");
                } else {
                    writer.writeAll(stockDAO.findAll());
                    System.out.print("Select stock to delete: ");
                    boolean isDeleted = stockDAO.deleteById(ConsoleUtil.readLong("Stock"));
                    if (!isDeleted) {
                        System.out.println("Stock not found");
                    } else {
                        System.out.println("Stock deleted");
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

    private void editStock() {
        System.out.println("Select by id a stock to edit");
        Long stockID = new Scanner(System.in).nextLong();
        System.out.println("Enter new quantity: ");
        Integer quantity = new Scanner(System.in).nextInt();
        System.out.println("Enter new location: ");
        String location = new Scanner(System.in).nextLine();
        Stock stock = stockDAO.findById(stockID);
        stock.setQuantity(quantity);
        stock.setLocation(location);
        stockDAO.update(stock);
    }

    private void displayStockDetails() {
        System.out.println("Choose stock by ID: ");
        Scanner scanner = new Scanner(System.in);
        Long stockID = scanner.nextLong();
        Stock searchedStock = stockDAO.findById(stockID);
        writer.write(searchedStock);
    }
}
