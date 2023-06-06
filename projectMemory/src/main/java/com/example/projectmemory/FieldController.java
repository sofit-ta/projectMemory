package com.example.projectmemory;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FieldController {
    public static Timer timer;
    private int level;

    @FXML
    private Button firstFieldButton;

    @FXML
    private Button secondFieldButton;

    @FXML
    private Button thirdFieldButton;


    @FXML
    void onMouse1Clicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) firstFieldButton.getScene().getWindow();
        stage.close();
        try {
            CreatingAScene createscene = new CreatingAScene(16,level);
            createscene.start(stage);
            timer = new Timer();
            timer.start2();
            timer.stage2.setScene(Timer.scene2);
            timer.stage2.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onMouse2Clicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) secondFieldButton.getScene().getWindow();
        stage.close();
        try {
            CreatingAScene createscene = new CreatingAScene(24,level);
            createscene.start(stage);
            timer = new Timer();
            timer.start2();
            timer.stage2.setScene(Timer.scene2);
            timer.stage2.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void onMouse3Clicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) thirdFieldButton.getScene().getWindow();
        stage.close();
        try {
            CreatingAScene createscene = new CreatingAScene(32,level);
            createscene.start(stage);
            timer = new Timer();
            timer.start2();
            timer.stage2.setScene(Timer.scene2);
            timer.stage2.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void setValue(int value) {
        level = value;
    }
}

