package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Automobile Sales Application");
        initRootLayout();
        showConnectionView();
    }

    public void initRootLayout() throws IOException {
        try {
            rootLayout = FXMLLoader.load(Main.class.getResource("view/RootLayout.fxml"));
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showConnectionView() throws IOException {
        try {
            AnchorPane dbConnectionInterface = FXMLLoader.load(Main.class.getResource("view/DbConnection.fxml"));
            rootLayout.setCenter(dbConnectionInterface);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}