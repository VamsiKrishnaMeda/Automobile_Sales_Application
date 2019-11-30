package sample.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import sample.util.DBUtil;

public class DbConnectionController {

    public void handleConnect(ActionEvent actionEvent) {

        boolean connectionSuccess = true;
        try {
            DBUtil.dbConnect();
        } catch (Exception e) {
            connectionSuccess = false;
            e.printStackTrace();
        }
        if (connectionSuccess) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Program Information");
            alert.setHeaderText("Interface Information");
            alert.setContentText("Choose the Interface you want to use from the Interface Dropdown Menu\n\n" +
                    "The following Interfaces are available:\n" +
                    "1. Customer Interface\n" +
                    "2. Dealer Interface\n" +
                    "3. Marketing Office Interface\n" +
                    "4. Production Office Interface\n");
            alert.show();
        }

    }

}