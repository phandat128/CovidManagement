package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Utility;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class XemKhaiBaoController implements Initializable {
    @FXML
    TextField declareSpotField, nameField;
    @FXML
    TextArea traceField, benhNenField;
    @FXML
    RadioButton BHYTYes, BHYTNo, symptomYes, symptomNo, covidContactYes, covidContactNo, countryContactYes, countryContactNo, symptomContactYes, symptomContactNo;

    @FXML
    DatePicker declareDate;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        declareDate.setConverter(Utility.LOCAL_DATE_CONVERTER);
//        OK.setDisable(false);
//        declareDate.valueProperty().addListener((observableValue, currentValue, newValue) -> {
//            if (currentValue == null) return;
//            if (!currentValue.isEqual(newValue)) OK.setDisable(false);
//        });
//        declareSpotField.textProperty().addListener((observableValue, currentValue, newValue) -> {
//            if (currentValue.isBlank()) return;
//            if (!currentValue.equals(newValue)) {
//                OK.setDisable(false);
//            }
//        });
//        nameField.textProperty().addListener((observableValue, currentValue, newValue) -> {
//            if (currentValue.isBlank()) return;
//            if (!currentValue.equals(newValue)) {
//                OK.setDisable(false);
//            }
//        });
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
}
