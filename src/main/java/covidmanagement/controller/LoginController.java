package covidmanagement.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class LoginController {

    public void moveToMainPage(ActionEvent event){
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
