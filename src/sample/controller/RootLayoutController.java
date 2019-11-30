package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sample.Main;
import sample.util.DBUtil;

import java.io.IOException;
import java.sql.SQLException;

public class RootLayoutController {

    @FXML
    private BorderPane rootLayout;

    public void handleProductionOffice(ActionEvent actionEvent) throws IOException {
        try {
            AnchorPane productionOfficeInterface = FXMLLoader.load(Main.class.getResource("view/ProductionOffice.fxml"));
            rootLayout.setCenter(productionOfficeInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCustomer(ActionEvent actionEvent) {
        try {
            AnchorPane customerInterface = FXMLLoader.load(Main.class.getResource("view/Customer.fxml"));
            rootLayout.setCenter(customerInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDealer(ActionEvent actionEvent) {
        try {
            AnchorPane dealerView = FXMLLoader.load(Main.class.getResource("view/Dealer.fxml"));
            rootLayout.setCenter(dealerView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleMarketingOffice(ActionEvent actionEvent) {
        try {
            AnchorPane marketingOfficeInterface = FXMLLoader.load(Main.class.getResource("view/MarketingOffice.fxml"));
            rootLayout.setCenter(marketingOfficeInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleAbout(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("Automobile Sales Application");
        alert.setContentText("1. The company’s production office needs an interface to allow new vehicles to be recorded in the database and to assign them to various dealers’ inventories \n\n" +
                "2. A dealer’s office needs an interface to record sales of vehicles in their inventory\n\n" +
                "3. The company’s marketing office must be able to run the analysis queries listed in the Queries section above (except first query)\n\n" +
                "4. Online customers need an interface to find dealers and check products, inventories, and prices");
        alert.show();
    }

    public void handleClose(ActionEvent actionEvent) {
        try {
            DBUtil.dbDisconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

}