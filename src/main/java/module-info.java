module com.example.covidmanagement2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens covidmanagement to javafx.fxml;
    exports covidmanagement;
    exports covidmanagement.controller;
    opens covidmanagement.controller to javafx.fxml;
}