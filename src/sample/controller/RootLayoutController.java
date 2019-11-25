package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import sample.Main;

import java.io.IOException;

public class RootLayoutController {

    @FXML
    private BorderPane rootLayout;
    private AnchorPane productionOfficeView;

    public void handleProductionOffice(ActionEvent actionEvent) throws IOException {
        try {
            productionOfficeView = FXMLLoader.load(Main.class.getResource("view/ProductionOffice.fxml"));
            rootLayout.setCenter(productionOfficeView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleCustomer(ActionEvent actionEvent) {
        try {
            AnchorPane customerView = FXMLLoader.load(Main.class.getResource("view/Customer.fxml"));
            rootLayout.setCenter(customerView);
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
            AnchorPane marketingOfficeView = FXMLLoader.load(Main.class.getResource("view/MarketingOffice.fxml"));
            rootLayout.setCenter(marketingOfficeView);
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
        System.exit(0);
    }

}