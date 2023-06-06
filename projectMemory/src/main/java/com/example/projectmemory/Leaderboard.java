package com.example.projectmemory;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class Leaderboard  {
    private final double timeFromFirstCode;
    private final TextField timeField = new TextField();

    public Leaderboard(double time) {
        this.timeFromFirstCode = time;

    }

    public void setTimeField(double time) {
        long timeInSeconds = (long) time;
        long hours = timeInSeconds / 3600;
        long minutes = (timeInSeconds % 3600) / 60;
        long seconds = timeInSeconds % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeField.setText(formattedTime);
    }

    private final ObservableList<Player> easyPlayers = FXCollections.observableArrayList();
    private final ObservableList<Player> mediumPlayers = FXCollections.observableArrayList();
    private final ObservableList<Player> hardPlayers = FXCollections.observableArrayList();



    public void start(Stage primaryStage,int level,int numberOfCards) {
        TableView<Player> easyTable = new TableView<>();
        easyTable.setEditable(false);

        TableColumn<Player, String> easyNameColumn = new TableColumn<>("Имя");
        easyNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Player, Double> easyTimeColumn = new TableColumn<>("Время");
        easyTimeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty().asObject());

        TableColumn<Player, Integer> easyCardColumn = new TableColumn<>("Кол-во карт");
        easyCardColumn.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty().asObject());




        TableView<Player> mediumTable = new TableView<>();
        mediumTable.setEditable(false);

        TableColumn<Player, String> mediumNameColumn = new TableColumn<>("Имя");
        mediumNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Player, Double> mediumTimeColumn = new TableColumn<>("Время");
        mediumTimeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty().asObject());

        TableColumn<Player, Integer> mediumCardColumn = new TableColumn<>("Кол-во карт");
        mediumCardColumn.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty().asObject());



        TableView<Player> hardTable = new TableView<>();
        hardTable.setEditable(false);

        TableColumn<Player, String> hardNameColumn = new TableColumn<>("Имя");
        hardNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Player, Double> hardTimeColumn = new TableColumn<>("Время");
        hardTimeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty().asObject());

        TableColumn<Player, Integer> hardCardColumn = new TableColumn<>("Кол-во карт");
        hardCardColumn.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty().asObject());



        TextField nameField = new TextField();
        Label nameLabel = new Label("Имя:");
        VBox nameBox = new VBox(nameLabel, nameField);
        nameBox.setSpacing(10);



        setTimeField(this.timeFromFirstCode); // Устанавливаем значение времени на кнопке
        Label timeLabel = new Label("Время:");
        VBox timeBox = new VBox(timeLabel, timeField);
        timeBox.setSpacing(10);



//        ComboBox<Integer> cardField = new ComboBox<>();
//        cardField.getItems().addAll(16, 24, 32);
//        cardField.setPromptText("Кол-во карт:");
//        VBox cardBox = new VBox(new Label("Кол-во карт:"), cardField);
//        cardBox.setSpacing(10);

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Введите номер уровня: ");
//        System.out.println("1 легкий");
//        System.out.println("2 средний");
//        System.out.println("3 сложный");
//        int level = Integer.parseInt(scanner.nextLine());
//
//        System.out.println("Количество карт:");
//        System.out.println("8");
//        System.out.println("16");
//        System.out.println("32");
//        int numberOfCards = Integer.parseInt(scanner.nextLine());


        Button addButton = new Button("Добавить");
        addButton.setOnAction(event -> {
            try {
                String name = nameField.getText();
                //int cardNumber = cardField.getValue(); // получаем выбранное значение из ComboBox
                int cardNumber = numberOfCards;
                long hours = Long.parseLong(timeField.getText().substring(0,2));
                long minutes = Long.parseLong(timeField.getText().substring(3,5));
                long seconds = Long.parseLong(timeField.getText().substring(6,8));
                double time = hours * 3600 + minutes * 60 + seconds;

                Player player = new Player(name, time , cardNumber); // Создаем нового игрока с указанным временем

                switch (cardNumber) {
                    case 16 -> {
                        if ((easyPlayers.size() < 10) && (numberOfCards == 16) && (level == 1)) {
                            easyPlayers.add(player);
                            easyTable.setItems(easyPlayers); // Обновляем таблицу
                            easyPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            File theseFile = new File("players(easy16).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            easyPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile1(easyTable);

                        }
                        else if ((mediumPlayers.size() < 10) && (numberOfCards == 16) && (level == 2)) {
                            mediumPlayers.add(player);
                            mediumTable.setItems(mediumPlayers); // Обновляем таблицу
                            mediumPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            File theseFile = new File("players(medium16).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            mediumPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile2(mediumTable);


                        }
                        else if ((hardPlayers.size() < 10) && (numberOfCards == 16) && (level == 3)) {
                            hardPlayers.add(player);
                            hardTable.setItems(hardPlayers); // Обновляем таблицу
                            hardPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            File theseFile = new File("players(hard16).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            hardPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile3(hardTable);
                        }
                    }
                    case 24 -> {
                        if ((easyPlayers.size() < 10) && (numberOfCards == 24) && (level == 1)) {
                            easyPlayers.add(player);
                            easyTable.setItems(easyPlayers); // Обновляем таблицу
                            easyPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            File theseFile = new File("players(easy24).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            easyPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile4(easyTable);
                        }
                        else if ((mediumPlayers.size() < 10) && (numberOfCards == 24) && (level == 2)) {
                            mediumPlayers.add(player);
                            mediumTable.setItems(mediumPlayers); // Обновляем таблицу
                            mediumPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            File theseFile = new File("players(medium24).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            mediumPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile5(mediumTable);
                        }
                        else if ((hardPlayers.size() < 10) && (numberOfCards == 24) && (level == 3)) {
                            hardPlayers.add(player);
                            hardTable.setItems(hardPlayers); // Обновляем таблицу
                            hardPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            hardPlayers.add(player);
                            hardTable.setItems(hardPlayers); // Обновляем таблицу
                            hardPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            File theseFile = new File("players(hard24).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            hardPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile6(hardTable);

                        }
                    }
                    case 32 -> {
                        if ((easyPlayers.size() < 10) && (numberOfCards == 32) && (level == 1)) {
                            easyPlayers.add(player);
                            easyTable.setItems(easyPlayers); // Обновляем таблицу
                            easyPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            easyPlayers.add(player);
                            easyTable.setItems(easyPlayers); // Обновляем таблицу
                            easyPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            File theseFile = new File("players(easy32).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            easyPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile7(easyTable);
                        }
                        else if ((mediumPlayers.size() < 10) && (numberOfCards == 32) && (level == 2)) {
                            mediumPlayers.add(player);
                            mediumTable.setItems(mediumPlayers); // Обновляем таблицу
                            mediumPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            File theseFile = new File("players(medium32).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            mediumPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile8(mediumTable);
                        }
                        else if ((hardPlayers.size() < 10) && (numberOfCards == 32) && (level == 3)) {
                            hardPlayers.add(player);
                            hardTable.setItems(hardPlayers); // Обновляем таблицу
                            hardPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));

                            File theseFile = new File("players(hard32).txt");
                            FileWriter writer = new FileWriter(theseFile, true);
                            writer.write(name + "-" + time + "-" + cardNumber + System.getProperty("line.separator"));
                            writer.close();

                            hardPlayers.sort((p1, p2) -> Double.compare(p1.getTime(), p2.getTime()));
                            fillTableFromFile9(hardTable);
                        }
                    }
                }


                nameField.clear();
                timeField.clear();


            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Неверные данные");
                alert.setHeaderText(null);
                alert.setContentText("Пожалуйста введите корректноые значения.");
                alert.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));



        // Получаем начальное значение количества карт
        if (numberOfCards == 16) {
            if (level == 1) {
                root.getChildren().addAll(new Label("Лидерборд для уровня Легкий (" + numberOfCards + " карт)"), easyTable);
                createLeaderboard(easyTable, easyPlayers, numberOfCards); // передаем список игроков для легкого уровня
                fillTableFromFile1(easyTable);

            }
            else if (level == 2) {
                root.getChildren().addAll(new Label("Лидерборд для уровня Средний (" + numberOfCards + " карт)"), mediumTable);
                createLeaderboard(mediumTable, mediumPlayers, numberOfCards); // передаем список игроков для среднего уровня
                fillTableFromFile2(mediumTable);
            }
            else if (level == 3){
                root.getChildren().addAll(new Label("Лидерборд для уровня Сложный (" + numberOfCards + " карт)"), hardTable);
                createLeaderboard(hardTable, hardPlayers, numberOfCards); // передаем список игроков для сложного уровня
                fillTableFromFile3(hardTable);
            }
        } else if (numberOfCards == 24) {
            if (level == 1) {
                root.getChildren().addAll(new Label("Лидерборд для уровня Легкий (" + numberOfCards + " карт)"), easyTable);
                createLeaderboard(easyTable, easyPlayers, numberOfCards); // передаем список игроков для легкого уровня
                fillTableFromFile4(easyTable);
            }
            else if (level == 2) {
                root.getChildren().addAll(new Label("Лидерборд для уровня Средний (" + numberOfCards + " карт)"), mediumTable);
                createLeaderboard(mediumTable, mediumPlayers, numberOfCards); // передаем список игроков для среднего уровня
                fillTableFromFile5(mediumTable);
            }
            else if (level == 3){
                root.getChildren().addAll(new Label("Лидерборд для уровня Сложный (" + numberOfCards + " карт)"), hardTable);
                createLeaderboard(hardTable, hardPlayers, numberOfCards); // передаем список игроков для сложного уровня
                fillTableFromFile6(hardTable);
            }

        } else if (numberOfCards == 32) {
            if (level == 1) {
                root.getChildren().addAll(new Label("Лидерборд для уровня Легкий (" + numberOfCards + " карт)"), easyTable);
                createLeaderboard(easyTable, easyPlayers, numberOfCards); // передаем список игроков для легкого уровня
                fillTableFromFile7(easyTable);
            }
            else if (level == 2) {
                root.getChildren().addAll(new Label("Лидерборд для уровня Средний (" + numberOfCards + " карт)"), mediumTable);
                createLeaderboard(mediumTable, mediumPlayers, numberOfCards); // передаем список игроков для среднего уровня
                fillTableFromFile8(mediumTable);
            }
            else if (level == 3){
                root.getChildren().addAll(new Label("Лидерборд для уровня Сложный (" + numberOfCards + " карт)"), hardTable);
                createLeaderboard(hardTable, hardPlayers, numberOfCards); // передаем список игроков для сложного уровня
                fillTableFromFile9(hardTable);
            }
        }
        root.getChildren().addAll(nameBox, timeBox, addButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Leaderboard");
        primaryStage.show();
    }




    private void createLeaderboard(TableView<Player> table, ObservableList<Player> players, int numberOfCards) {
        TableColumn<Player, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Player, String> timeColumn = new TableColumn<>("Время");
        timeColumn.setCellValueFactory(cellData -> Bindings.format("%02d:%02d:%02d", (int)cellData.getValue().getTime()/3600, (int)(cellData.getValue().getTime()%3600)/60, (int)cellData.getValue().getTime()%60));


        TableColumn<Player, Integer> cardColumn = new TableColumn<>("Кол-во карт");
        cardColumn.setCellValueFactory(cellData -> cellData.getValue().cardNumberProperty().asObject());

        table.getColumns().addAll(nameColumn, timeColumn, cardColumn);
        table.setItems(players); // устанавливаем список игроков для таблицы
    }
    private void fillTableFromFile1(TableView<Player> table) {
        try {
            File theseFile = new File("players(easy16).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();

            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillTableFromFile7(TableView<Player> table) {
        try {
            File theseFile = new File("players(easy32).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();

            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillTableFromFile4(TableView<Player> table) {
        try {
            File theseFile = new File("players(easy24).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();

            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillTableFromFile3(TableView<Player> table) {
        try {
            File theseFile = new File("players(hard16).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();


            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void fillTableFromFile9(TableView<Player> table) {
        try {
            File theseFile = new File("players(hard32).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();


            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillTableFromFile6(TableView<Player> table) {
        try {
            File theseFile = new File("players(hard24).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();


            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillTableFromFile2(TableView<Player> table) {
        try {
            File theseFile = new File("players(medium16).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();


            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillTableFromFile8(TableView<Player> table) {
        try {
            File theseFile = new File("players(medium32).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();


            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void fillTableFromFile5(TableView<Player> table) {
        try {
            File theseFile = new File("players(medium24).txt");
            BufferedReader reader = new BufferedReader(new FileReader(theseFile));
            List<Player> players = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                String name = parts[0];
                double time = Double.parseDouble(parts[1]);
                int cardNumber = Integer.parseInt(parts[2]);
                Player player = new Player(name, time, cardNumber);
                players.add(player);
            }
            reader.close();


            Collections.sort(players, Comparator.comparing(Player::getTime));

            table.setItems(FXCollections.observableArrayList(players));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Player {

        private final StringProperty name = new SimpleStringProperty("");
        private final DoubleProperty time = new SimpleDoubleProperty(0);
        private final IntegerProperty cardNumber = new SimpleIntegerProperty(0);

        public Player(String name, double time, int cardNumber) { // Изменяем тип аргумента time на double
            setName(name);
            setTime(time);
            setCardNumber(cardNumber);
        }

        public String getName() {
            return name.get();
        }

        public StringProperty nameProperty() {
            return name;
        }

        public void setName(String name) {
            this.name.set(name);
        }

        public double getTime() {
            return time.get();
        }

        public DoubleProperty timeProperty() {
            return time;
        }

        public void setTime(double time) {
            this.time.set(time);
        }

        public int getCardNumber() {
            return cardNumber.get();
        }

        public IntegerProperty cardNumberProperty() {
            return cardNumber;
        }

        public void setCardNumber(int cardNumber) {
            this.cardNumber.set(cardNumber);
        }

    }
}
