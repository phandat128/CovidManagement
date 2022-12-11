module com.example.covidmanagement2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens covidmanagement to javafx.fxml;
    exports covidmanagement;
    exports covidmanagement.controller;
    opens covidmanagement.controller to javafx.fxml;
}