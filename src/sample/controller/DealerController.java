package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DealerController {

    @FXML
    private AnchorPane dealerInterface;
    @FXML
    private TextField dealer_name;
    @FXML
    private TextField cust_id;
    @FXML
    private TextField vehicle_vin;
    @FXML
    private TextField sale_date;
    @FXML
    private TextField sale_price;
    @FXML
    private Label report_text;

    public void EnterNewSale(javafx.event.ActionEvent actionEvent) {
        try {
            Class.forName(DBUtil.JDBC_DRIVER);
            try {
                Connection connection = DriverManager.getConnection(DBUtil.CONN_STRING, DBUtil.USER_NAME, DBUtil.PASS);
                PreparedStatement statement = connection.prepareStatement("INSERT INTO SALE (Dealer_ID, Customer_ID, VIN, Sale_Price, Sale_Date) VALUES (?, ?, ?, ?, ?)");
                statement.setString(1, dealer_name.getText());
                statement.setString(2, cust_id.getText());
                statement.setString(3, vehicle_vin.getText());
                statement.setString(4, sale_price.getText());
                statement.setDate(5, new Date(new SimpleDateFormat("DD-MM-YYYY", Locale.US).parse(sale_date.getText()).getTime()));
                if (statement.executeUpdate() == 0) {
                    report_text.setText("Sale Entry Successful");
                } else {
                    report_text.setText("Sale Entry Failed");
                }
            } catch (Exception e) {
                report_text.setText("Sale Entry Failed");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            report_text.setText("Sale Entry Failed");
            e.printStackTrace();
        }
    }
}