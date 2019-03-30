package ro.sda.dealership.storage;

import ro.sda.dealership.model.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockDAO extends GenericDAO<Stock> {
    private static List<Stock> stocks = new ArrayList<Stock>();

    protected List<Stock> getItems() {
        return stocks;
    }

    public Stock findByCarIdAndLocation(Long id, String location){
        for (Stock stock : getItems()){
            if(stock.getCar().getId().equals(id) && stock.getLocation().equals(location)){
                return stock;
            }
        }
        return null;
    }
}