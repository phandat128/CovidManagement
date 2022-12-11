module com.example.covidmanagement2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.covidmanagement2 to javafx.fxml;
    exports com.covidmanagement2;
}