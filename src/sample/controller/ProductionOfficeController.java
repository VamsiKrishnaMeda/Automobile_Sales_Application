package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sample.util.DBUtil;

import java.sql.*;
import java.text.ParseException;

public class ProductionOfficeController {

    @FXML
    private TextField vin_field;
    @FXML
    private TextField model_name_field;
    @FXML
    private TextField body_style_field;
    @FXML
    private TextField year_field;
    @FXML
    private TextField color_field;
    @FXML
    private TextField engine_field;
    @FXML
    private TextField transmission_field;
    @FXML
    private TextField tag_price_field;
    @FXML
    private TextField manufacture_date_field;

    @FXML
    private TextField inventory_dealer_id_field;
    @FXML
    private TextField inventory_vin_field;
    @FXML
    private TextField inventory_date_field;
    @FXML
    private TextField is_sold_field;

    public void newVehicle(ActionEvent actionEvent) throws ParseException {
        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Vehicle VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, vin_field.getText());
                statement.setString(2, model_name_field.getText());
                statement.setString(3, body_style_field.getText());
                statement.setInt(4, Integer.parseInt(year_field.getText()));
                statement.setString(5, color_field.getText());
                statement.setString(6, engine_field.getText());
                statement.setString(7, transmission_field.getText());
                statement.setInt(8, Integer.parseInt(tag_price_field.getText()));
                statement.setDate(9, Date.valueOf(manufacture_date_field.getText()));
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearVehicle(ActionEvent actionEvent) {
        vin_field.clear();
        model_name_field.clear();
        body_style_field.clear();
        year_field.clear();
        color_field.clear();
        engine_field.clear();
        transmission_field.clear();
        tag_price_field.clear();
        manufacture_date_field.clear();
    }

    public void assignVehicle(ActionEvent actionEvent) throws ParseException{
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            try {
                Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@citdb.nku.edu:1521:csc450", "medav1", "csc222");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO Inventory VALUES (?, ?, ?, ?)");
                statement.setString(1, inventory_vin_field.getText());
                statement.setInt(2, Integer.parseInt(inventory_dealer_id_field.getText()));
                statement.setString(3, is_sold_field.getText());
                statement.setDate(4, Date.valueOf(inventory_date_field.getText()));
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clearAssign(ActionEvent actionEvent) {
        inventory_dealer_id_field.clear();
        inventory_vin_field.clear();
        inventory_date_field.clear();
        is_sold_field.clear();
    }

}