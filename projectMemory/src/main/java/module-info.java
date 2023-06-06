module com.example.projectmemory {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectmemory to javafx.fxml;
    exports com.example.projectmemory;
}