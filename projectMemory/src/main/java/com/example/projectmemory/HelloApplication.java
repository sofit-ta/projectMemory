package com.example.projectmemory;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

//public class HelloApplication extends Application {
//
//
//    public static void main(String[] args) {
//        launch();
//    }
//    public static Timer timer;
//    @Override
//    public void start(Stage stage) throws Exception {
//        Button button1 = new Button("button1");
//        Pane pane = new Pane();
//        pane.getChildren().add(button1);
//        Scene scene = new Scene(pane,400,500);
//        button1.setOnMousePressed(mouseEvent -> {
//            stage.close();
//            try {
//                CreatingAScene createscene = new CreatingAScene(32,3);
//                createscene.start(stage);
//                timer = new Timer();
//                timer.start2();
//                timer.stage2.setScene(Timer.scene2);
//                timer.stage2.show();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//            // вот здесь заканчивается вызов
//        });
//        stage.setScene(scene);
//        stage.show();
//    }
//}

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu_SB.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

