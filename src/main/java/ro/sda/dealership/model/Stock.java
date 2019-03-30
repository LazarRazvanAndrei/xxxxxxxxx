package ro.sda.dealership.model;

public class Stock extends Entity {
    public static final String DEFAULT_LOCATION = "Oradea";
    private Car car;
    private Integer quantity;
    private String location;

    public Stock() {
    }

    public Stock(Car car, Integer quantity, String location) {
        this.car = car;
        this.quantity = quantity;
        this.location = location;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
