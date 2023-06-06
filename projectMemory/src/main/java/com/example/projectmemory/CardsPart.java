package com.example.projectmemory;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;


public class CardsPart {
    public static ArrayList<Image> front = CreatingAScene.front;
    public static ArrayList<ImageView> cards = CreatingAScene.cards;
    public static ArrayList<Integer> opened_cards = CreatingAScene.opened_cards;
    public static ArrayList<Integer> guessed_cards = CreatingAScene.guessed_cards;
    Image backImage = new Image(getClass().getResourceAsStream("/com/example/projectmemory/backimage.jpg"));
    public static boolean need_to_turn = true;
    public static boolean cards_are_not_matching = false;
    public static EventHandler<KeyEvent> handleKeyPress(int i) {

        RotateTransition rotator = createRotator(i);
        PauseTransition ptChangeCardFace = changeCardFace(i);
        if (need_to_turn) {
            return event -> {
                ParallelTransition parallelTransition = new ParallelTransition(rotator, ptChangeCardFace);
                parallelTransition.play();
            };
        } else {
            need_to_turn = true;
            return event -> {rotator.play();};
        }
    }
    private static RotateTransition createRotator(int i) {
        RotateTransition rotator = new RotateTransition(Duration.millis(500), cards.get(i));
        rotator.setAxis(Rotate.Y_AXIS);
        System.out.println("numberofthecards");
        System.out.println(i);

        if ((cards.get(i).getImage().equals(front.get(i)))){
            System.out.println("u");
            rotator.setFromAngle(0);
            rotator.setToAngle(180);
            need_to_turn = false;
        } else{
            if (opened_cards.size()==0){
                if (guessed_cards.contains(i)){
                    rotator.setFromAngle(0);
                    rotator.setToAngle(0);
                    need_to_turn = false;
                }else{
                    rotator.setFromAngle(0);
                    rotator.setToAngle(180);
                    opened_cards.add(i);
                }
            }else if (opened_cards.size()==1){
                if (guessed_cards.contains(i)){
                    rotator.setFromAngle(0);
                    rotator.setToAngle(0);
                    need_to_turn = false;
                }else{
                    rotator.setFromAngle(0);
                    rotator.setToAngle(180);
                    if (front.get(i).equals(front.get(opened_cards.get(0)))){
                        System.out.println("yess");
                        guessed_cards.add(i);
                        guessed_cards.add(opened_cards.get(0));
                        opened_cards.remove(0);
                    } else{
                        opened_cards.add(i);
                        need_to_turn=false;
                        cards_are_not_matching =true;
                    }
                }
            }
        }

        System.out.println("size");
        System.out.println(opened_cards.size());
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(1);

        return rotator;
    }
    public static void rotate_two_cards() throws InterruptedException {
        if (cards_are_not_matching){
            cards_are_not_matching=false;
            RotateTransition rotator0 = new RotateTransition(Duration.millis(800), cards.get(opened_cards.get(1)));

            rotator0.setAxis(Rotate.Y_AXIS);
            rotator0.setFromAngle(0);
            rotator0.setToAngle(180);
            rotator0.setInterpolator(Interpolator.LINEAR);
            rotator0.setCycleCount(1);

            PauseTransition ptChangeCardFace0 = changeCardFace(opened_cards.get(1));

            ParallelTransition parallelTransition1 = new ParallelTransition(rotator0,ptChangeCardFace0);
            parallelTransition1.play();
            parallelTransition1.setOnFinished(actionEvent -> CardsPart.secondcard());

        }}
    private static void secondcard(){
        RotateTransition rotator1 = new RotateTransition(Duration.millis(500), cards.get(opened_cards.get(0)));
        RotateTransition rotator2 = new RotateTransition(Duration.millis(500), cards.get(opened_cards.get(1)));
        rotator1.setAxis(Rotate.Y_AXIS);
        rotator1.setFromAngle(0);
        rotator1.setToAngle(180);
        rotator1.setInterpolator(Interpolator.LINEAR);
        rotator1.setCycleCount(1);
        rotator2.setAxis(Rotate.Y_AXIS);
        rotator2.setFromAngle(0);
        rotator2.setToAngle(180);
        rotator2.setInterpolator(Interpolator.LINEAR);
        rotator2.setCycleCount(1);
        PauseTransition ptChangeCardFace1 = changeCardFace(opened_cards.get(0));
        PauseTransition ptChangeCardFace2 = changeCardFace(opened_cards.get(1));
        ParallelTransition transition = new ParallelTransition(rotator1,rotator2, ptChangeCardFace1,ptChangeCardFace2);
        transition.play();
        opened_cards.remove(1);
        opened_cards.remove(0);
    }
    private static PauseTransition changeCardFace(int i) {
        PauseTransition pause = new PauseTransition(Duration.millis(500));

        if (cards.get(i).getImage()==front.get(i)) {
            pause.setOnFinished(
                    e -> cards.get(i).setImage(front.get(front.size()-1)));
        } else {
            pause.setOnFinished(
                    e -> cards.get(i).setImage(front.get(i)));
        }

        return pause;
    }

}