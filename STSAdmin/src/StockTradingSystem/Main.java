package StockTradingSystem;

import StockTradingSystem.controller.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.InputStream;

public class Main extends Application {
    public Stage stage;
    public Stage floatStage;

    private double x0, y0, x_stage, y_stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        gotoAdminLoginUI();
        stage.show();
    }

    public void createChangePasswordUI() throws Exception {
        floatStage = new Stage();
        floatStage.setTitle("Change Password");
        floatStage.setResizable(false);
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream("fxml/ChangePasswordUI.fxml");
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("fxml/ChangePasswordUI.fxml"));
        AnchorPane page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        floatStage.setScene(scene);
        floatStage.show();
        ChangePasswordUIController changePasswordUI = loader.getController();
        changePasswordUI.setApp(this);
    }

    public void gotoAdminLoginUI() throws Exception {
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        AdminLoginUIController loginUI = (AdminLoginUIController)replaceSceneContent("fxml/AdminLoginUI.fxml");
        loginUI.setApp(this);
        stage.getScene().setOnMouseDragged(event -> {
            stage.setX(x_stage + event.getScreenX() - x0);
            stage.setY(y_stage + event.getScreenY() - y0);
        });
        stage.getScene().setOnDragEntered(null);
        stage.getScene().setOnMousePressed(event -> {
            x0 = event.getScreenX();
            y0 = event.getScreenY();
            x_stage = stage.getX();
            y_stage = stage.getY();
        });
    }


    public void gotoAdminUI() throws Exception {
        stage.setResizable(true);
        AdminUIControlller AdminUI = (AdminUIControlller)replaceSceneContent("fxml/AdminUI.fxml");
        AdminUI.setApp(this);
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {

        launch(args);
    }
}