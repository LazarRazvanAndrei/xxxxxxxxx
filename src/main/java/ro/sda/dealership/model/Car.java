package ro.sda.dealership.model;

public class Car extends Entity{
    private String carModel;
    private Double priceCar;
    private String carColor;
    private Integer engineCapacity;
    private String bodyType;
    private String transmission;
    private String driveType; // 2wd / 4wd // awd



    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Double getCarPrice() {
        return priceCar;
    }

    public void setPriceCar(Double priceCar) {
        this.priceCar = priceCar;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public Integer getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Integer engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }
}
