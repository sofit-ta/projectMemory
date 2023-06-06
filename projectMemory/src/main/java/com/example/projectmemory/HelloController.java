package com.example.projectmemory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button firstLevelButton;

    @FXML
    private Button secondLevelButton;

    @FXML
    private Label menuLabel;

    @FXML
    private Button thirdLevelButton;


    @FXML
    public void onMouse1Clicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) firstLevelButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu_type.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        FieldController fieldController = fxmlLoader.getController();
        fieldController.setValue(1);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    public void onMouse2Clicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) secondLevelButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu_type.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        FieldController fieldController = fxmlLoader.getController();
        fieldController.setValue(2);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void onMouse3Clicked(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) thirdLevelButton.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu_type.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage = new Stage();
        FieldController fieldController = fxmlLoader.getController();
        fieldController.setValue(3);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root1));
        stage.show();
    }
}

