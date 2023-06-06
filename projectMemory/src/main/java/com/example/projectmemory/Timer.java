package com.example.projectmemory;

import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.time.LocalTime;

public class Timer {

   public static Stage stage2;
   public static Scene scene2;
   public static Timeline timeline;
   public final LocalTime[] startTime = {LocalTime.MIN};
    public void start2() {
        Label timerLabel = new Label(String.format("%02d:%02d:%02d", startTime[0].getHour(), startTime[0].getMinute(), startTime[0].getSecond()));
        timerLabel.setStyle("-fx-font-size: 30px;");
        Pane pane = new Pane(timerLabel);

        stage2 = new Stage();
        stage2.setX(10);
        System.out.println("yes");
        scene2 = new Scene(pane, 140, 60);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            startTime[0] = startTime[0].plusSeconds(1);
            if (startTime[0].getSecond() == 59) {
                startTime[0] = LocalTime.of(startTime[0].getHour(), startTime[0].getMinute() + 1, 0);
            } else if (startTime[0].getSecond() == 0) {
                int oldMinute = startTime[0].getMinute();
                startTime[0] = startTime[0].plusMinutes(1);
                if (oldMinute != startTime[0].getMinute()) {
                    startTime[0] = LocalTime.of(startTime[0].getHour(), startTime[0].getMinute(), 0);
                }
            }
            timerLabel.setText(String.format("%02d:%02d:%02d", startTime[0].getHour(), startTime[0].getMinute(), startTime[0].getSecond()));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);


    }

    public void showLeaderboard(int level,int amount_of_cards) {
        int time = startTime[0].toSecondOfDay();
        Stage leaderboardStage = new Stage();
        Leaderboard leaderboard = new Leaderboard(time);
        leaderboard.setTimeField(time);
        leaderboard.start(leaderboardStage,level,amount_of_cards);
    }


}
