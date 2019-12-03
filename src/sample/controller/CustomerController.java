package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.util.DBUtil;

import java.sql.*;
import java.text.ParseException;

public class CustomerController {

    @FXML
    private AnchorPane customerInterface;
    @FXML
    private TextField model_name;
    @FXML
    private TextField year;
    @FXML
    private TextField body_style;
    @FXML
    private TextField body_color;
    @FXML
    private TextField min_price;
    @FXML
    private TextField max_price;
    @FXML
    private TableView vehicle_data_table;

    public void searchVehicles(javafx.event.ActionEvent actionEvent) throws ParseException {
        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                String sql = getFilteredSQLQuery();
                PreparedStatement statement =
                        connection.prepareStatement(sql);
                ResultSet vehicle_data = statement.executeQuery();
                updateTableView(vehicle_data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void clearVehicles(javafx.event.ActionEvent actionEvent) {
        ObservableList<Vehicle> cleared = FXCollections.observableArrayList();
        vehicle_data_table.setItems(cleared);
        model_name.clear();
        year.clear();
        body_style.clear();
        body_color.clear();
        min_price.clear();
        max_price.clear();
    }

    private void updateTableView(ResultSet vehicle_data) {
        try {
            vehicle_data_table = new TableView();
            if (vehicle_data_table.getColumns().size() == 0) {
                AddVehicleDataColumns();
            }

            ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
            while (vehicle_data.next()) {
                String d = vehicle_data.getString(1);
                String br = vehicle_data.getString(2);
                String m = vehicle_data.getString(3);
                String y = vehicle_data.getString(4);
                String b = vehicle_data.getString(5);
                String c = vehicle_data.getString(6);
                String p = vehicle_data.getString(7);
                Vehicle newVehicle = new Vehicle(d, br, m, y, b, c, p);
                vehicles.add(newVehicle);
                vehicle_data_table.getItems().add(newVehicle);
            }
            //vehicle_data_table.setItems(vehicles);
            vehicle_data_table.refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void AddVehicleDataColumns() {
        TableColumn dealer = new TableColumn("Dealer");
        TableColumn brand = new TableColumn("Brand");
        TableColumn model = new TableColumn("Model");
        TableColumn year = new TableColumn("Year");
        TableColumn bodyStyle = new TableColumn("Body_Style");
        TableColumn bodyColor = new TableColumn("Color");
        TableColumn price = new TableColumn("Price");

        vehicle_data_table.getColumns().add(dealer);
        vehicle_data_table.getColumns().add(brand);
        vehicle_data_table.getColumns().add(model);
        vehicle_data_table.getColumns().add(year);
        vehicle_data_table.getColumns().add(bodyStyle);
        vehicle_data_table.getColumns().add(bodyColor);
        vehicle_data_table.getColumns().add(price);
    }

    private String getFilteredSQLQuery() {
        String sql = "SELECT DEALERSHIP.Name, MODEL.Brand_Name, VEHICLE.Model_Name, VEHICLE.Year, VEHICLE.Body_Style, VEHICLE.Color, VEHICLE.Tag_Price ";
        sql += "FROM VEHICLE ";
        sql += "INNER JOIN INVENTORY ON INVENTORY.VIN = VEHICLE.VIN ";
        sql += "INNER JOIN DEALERSHIP ON DEALERSHIP.ID = INVENTORY.DEALER_ID ";
        sql += "INNER JOIN MODEL ON MODEL.Name = VEHICLE.Model_Name AND MODEL.Year = VEHICLE.Year AND MODEL.Body_Style = VEHICLE.Body_Style";
        Boolean addWhere = true;
        if (model_name != null && model_name.getLength() > 0) {
            sql += addSuffix("Model", "'" + model_name.getText() + "'", addWhere);
            addWhere = false;
        }
        if (year != null && year.getLength() > 0) {
            sql += addSuffix("Year", year.getText(), addWhere);
            addWhere = false;
        }
        if (body_style != null && body_style.getLength() > 0) {
            sql += addSuffix("Body_Style", "'" + body_style.getText() + "'", addWhere);
            addWhere = false;
        }
        if (body_color != null && body_style.getLength() > 0) {
            sql += addSuffix("Color", "'" + body_color.getText() + "'", addWhere);
            addWhere = false;
        }
        if (min_price != null && min_price.getLength() > 0) {
            sql += addPriceSuffix(min_price.getText(), "<", addWhere);
            addWhere = false;
        }
        if (max_price != null && max_price.getLength() > 0) {
            sql += addPriceSuffix(max_price.getText(), ">", addWhere);
        }
        return sql;
    }

    private String addSuffix(String varName, String varVal, Boolean addWhere) {
        String suffix = "";
        if (addWhere) {
            suffix += " WHERE ";
        } else {
            suffix += " AND ";
        }
        suffix += varName + " = " + varVal;
        return suffix;
    }

    private String addPriceSuffix(String varValue, String comparator, Boolean addWhere) {
        String suffix = "";
        if (addWhere) {
            suffix += " WHERE ";
        } else {
            suffix += " AND ";
        }
        suffix += "Tag_Price " + comparator + " " + varValue;
        return suffix;
    }

    private class Vehicle {

        public String Dealer;
        public String Brand;
        public String Model;
        public String Year;
        public String Body;
        public String Color;
        public String Price;

        private Vehicle(String newDealer, String newBrand, String newModel, String newYear, String newBody, String newColor, String newPrice) {
            Dealer = newDealer;
            Brand = newBrand;
            Model = newModel;
            Year = newYear;
            Body = newBody;
            Color = newColor;
            Price = newPrice;
        }
    }
}