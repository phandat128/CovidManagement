package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Main;
import covidmanagement.model.KhaiBaoModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class XemKhaiBaoController implements Initializable {
    @FXML
    TextField declareSpotField, nameField, traceField, benhNenField;
    @FXML
    RadioButton BHYTYes, BHYTNo, symptomYes, symptomNo, covidContactYes, covidContactNo, countryContactYes, countryContactNo, symptomContactYes, symptomContactNo;

    @FXML
    DatePicker declareDate;
    @FXML
    Button OK;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OK.setDisable(false);
        declareDate.valueProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue == null) return;
            if (!currentValue.isEqual(newValue)) OK.setDisable(false);
        });
        declareSpotField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                OK.setDisable(false);
            }
        });
        nameField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                OK.setDisable(false);
            }
        });
    }
    public void setField(String declareSpotField, LocalDate declareDate, int nameField, String traceField,
                         boolean BHYT, boolean symptom, boolean covidContact, boolean countryContact, boolean symptomContact,
                         String benhNenField) {
        this.nameField.setText(String.valueOf(nameField));
        this.declareDate.setValue(declareDate);
        this.declareSpotField.setText(declareSpotField);
        this.traceField.setText(traceField);
        this.benhNenField.setText(benhNenField);
        if (BHYT) {
            this.BHYTYes.setSelected(true);
            this.BHYTNo.setSelected(false);
        }
        else {
            this.BHYTNo.setSelected(true);
            this.BHYTYes.setSelected(false);
        }
        if (symptom) {
            this.symptomYes.setSelected(true);
            this.symptomNo.setSelected(false);
        }
        else {
            this.symptomNo.setSelected(true);
            this.symptomYes.setSelected(false);
        }

        if (covidContact) {
            this.covidContactYes.setSelected(true);
            this.covidContactNo.setSelected(false);
        }
        else {
            this.covidContactNo.setSelected(true);
            this.covidContactYes.setSelected(false);
        }

        if (countryContact) {
            this.countryContactYes.setSelected(true);
            this.countryContactNo.setSelected(false);
        }
        else {
            this.countryContactNo.setSelected(true);
            this.countryContactYes.setSelected(false);
        }

        if (symptomContact) {
            this.symptomContactYes.setSelected(true);
            this.symptomContactNo.setSelected(false);
        }
        else {
            this.symptomContactNo.setSelected(true);
            this.symptomContactYes.setSelected(false);
        }
    }
    public void switchToSearchView(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("khaibao/timkiemkhaibao-view.fxml"));
        Parent componentScene = fxmlLoader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(componentScene);
        stage.setScene(scene);
        stage.show();
    }
}
