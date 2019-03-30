package ro.sda.dealership.storage;

import ro.sda.dealership.common.Utils;
import ro.sda.dealership.model.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {

    private Connection conn = null;
    private Statement stmt = null;

    public void initialize() {
        try {
            Class.forName(Utils.JDBC_DRIVER);

            System.out.println("Creating connection ...");
            conn = DriverManager.getConnection(
                    Utils.DB_URL,
                    Utils.User,
                    Utils.Pass);
            System.out.println("Create statement ...");
            stmt = conn.createStatement();
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addCar(Car car) {
        try {

            int rows = stmt.executeUpdate(
                    "insert into car(id, color, model) " +
                            "values(" + car.getId() + ", '" + car.getCarColor() +
                            "', '" + car.getCarModel() + "')");
            System.out.println("Rows inserted = " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Car> findAll() {
        List<Car> cars = new ArrayList<Car>();
        ResultSet rs = null;

        try {
            rs = stmt.executeQuery("select * from car");
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getLong("id"));
                car.setCarColor(rs.getString("color"));
                car.setCarModel(rs.getString("model"));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }


    public Car findById(Long id) {

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery("select * from car where id = " + id);
            if (resultSet.next()) {
                Car car = new Car();
                car.setId(resultSet.getLong("id"));
                car.setCarColor(resultSet.getString("color"));
                car.setCarModel(resultSet.getString("model"));
                return car;
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        return null;
    }

    public void update(Car car) {
        int rows = 0;
        try {
            rows = stmt.executeUpdate("update car set color  = '" + car.getCarColor()
                    + "' where id = " + car.getId());

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        System.out.println("updated rows: " + rows);
    }

    public void deleteAll() {
//        List<Car> cars = findAll();
//        for (Car car : cars) {
//            deleteById(car.getId());
//        }

        int rows;
        try {
            rows = stmt.executeUpdate("delete from car");
        }catch (SQLException sql){
            sql.printStackTrace();
        }
    }


    public void deleteById(Long id) {
        int rows = 0;

        try {
            rows = stmt.executeUpdate("delete from car where id = " + id);
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        System.out.println("Deleted rows: " + rows);
    }

}
