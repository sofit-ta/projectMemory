package com.example.projectmemory;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.projectmemory.FieldController.timer;

public class CreatingAScene {
    public Image backImage = new Image(getClass().getResourceAsStream("/com/example/projectmemory/backimage.jpg"));
    public ArrayList<String> easy_cards = new ArrayList<>();
    public ArrayList<String> medium_cards = new ArrayList<>();
    public ArrayList<String> hard_cards = new ArrayList<>();
    public static ArrayList<Image> front = new ArrayList<>();
    public static ArrayList<ImageView> cards = new ArrayList<>();
    public static ArrayList<Integer> opened_cards = new ArrayList<>();
    public static ArrayList<Integer> guessed_cards = new ArrayList<>();
    int max = 4;
    int amount_of_cards;
    int type_of_cards ;
    int number_of_step = 0;
    public CreatingAScene(int amount_of_cards,int type_of_cards){
        this.amount_of_cards =amount_of_cards;
        this.type_of_cards =type_of_cards;
    }
    public void start(Stage stage)  {
        list_of_cards();

        Collections.shuffle(front);
        front.add(backImage);
        if (front.size()-1 == 24) {
            max = max + 2;
        } else if (front.size()-1 == 32) {
            max = max + 4;
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < (front.size()-1); i++) {
            cards.add(createCard());
            cards.get(i).setX((x + 1) * 10 + (x * 140));
            cards.get(i).setY(y);
            int finalI = i;
            cards.get(i).setOnMouseClicked(event -> {
                number_of_step++;
                if (number_of_step==1) {
                    Timer.timeline.play();
                }
                CardsPart.handleKeyPress(finalI).handle(null);
                try {
                    CardsPart.rotate_two_cards();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                check_if_ends(stage);
            });
            if (x == max - 1) {
                y = y + 150;
                x = 0;
            } else {
                x++;
            }
        }
        stage.setScene(createScene());
        stage.show();
    }
    private void check_if_ends(Stage stage){
        if (guessed_cards.size()==(amount_of_cards)){
            System.out.println("it is here");
            stage.close();
            Timer.timeline.stop();
            Timer.stage2.close();
            timer.showLeaderboard(type_of_cards,amount_of_cards);
            //здесь вызывайте следующюю сцену/метод (таймер) + передаете кол-во карт (amount_of_cards) и уровень (type_of_cards)
        }
    }
    private void list_of_cards(){
        easy_cards.addAll(List.of("/com/example/projectmemory/e1.jpg","/com/example/projectmemory/e2.jpg","/com/example/projectmemory/e3.jpg","/com/example/projectmemory/e4.jpg","/com/example/projectmemory/e5.jpg","/com/example/projectmemory/e6.jpg","/com/example/projectmemory/e7.jpg",
                "/com/example/projectmemory/e8.jpg","/com/example/projectmemory/e9.jpg","/com/example/projectmemory/e10.jpg","/com/example/projectmemory/e11.jpg","/com/example/projectmemory/e12.jpg","/com/example/projectmemory/e13.jpg","/com/example/projectmemory/e14.jpg","/com/example/projectmemory/e15.jpg","/com/example/projectmemory/e16.jpg"));
        medium_cards.addAll(List.of( "/com/example/projectmemory/m1.jpg","/com/example/projectmemory/m2.jpg","/com/example/projectmemory/m3.jpg","/com/example/projectmemory/m4.jpg","/com/example/projectmemory/m5.jpg","/com/example/projectmemory/m6.jpg","/com/example/projectmemory/m7.jpg",
                "/com/example/projectmemory/m8.jpg","/com/example/projectmemory/m9.jpg","/com/example/projectmemory/m10.jpg","/com/example/projectmemory/m11.jpg","/com/example/projectmemory/m12.jpg","/com/example/projectmemory/m13.jpg","/com/example/projectmemory/m14.jpg","/com/example/projectmemory/m15.jpg","/com/example/projectmemory/m16.jpg"));
        hard_cards.addAll(List.of( "/com/example/projectmemory/h1.jpg","/com/example/projectmemory/h2.jpg","/com/example/projectmemory/h3.jpg","/com/example/projectmemory/h4.jpg","/com/example/projectmemory/h5.jpg","/com/example/projectmemory/h6.jpg","/com/example/projectmemory/h7.jpg",
                "/com/example/projectmemory/h8.jpg","/com/example/projectmemory/h9.jpg","/com/example/projectmemory/h10.jpg","/com/example/projectmemory/h11.jpg","/com/example/projectmemory/h12.jpg","/com/example/projectmemory/h13.jpg","/com/example/projectmemory/h14.jpg","/com/example/projectmemory/h15.jpg","/com/example/projectmemory/h16.jpg"));
        Image curr_image;
        if (type_of_cards==1){
            for(int i=0; i<(amount_of_cards/2); i++){
                curr_image = new Image(getClass().getResourceAsStream(easy_cards.get(i)));
                front.add(curr_image);
                front.add(curr_image);
                System.out.println(curr_image);
                System.out.println(easy_cards.get(i));
                System.out.println(i);
            }
        }else if(type_of_cards==2){
            for(int i=0; i<(amount_of_cards/2); i++){
                curr_image = new Image(getClass().getResourceAsStream(medium_cards.get(i)));
                front.add(curr_image);
                front.add(curr_image);
                System.out.println(curr_image);
            }
        }else{
            for(int i=0; i<(amount_of_cards/2); i++){
                curr_image = new Image(getClass().getResourceAsStream(hard_cards.get(i)));
                front.add(curr_image);
                front.add(curr_image);
                System.out.println(curr_image);
            }
        }
    }
    private ImageView createCard() {

        return new ImageView(backImage);
    }
    private Scene createScene() {
        Group root = new Group();
        root.getChildren().addAll(cards);
        Scene scene = new Scene(root, max*150+10, 610, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());

        return scene;
    }

}