package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.KhaiBaoModel;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SuaKhaiBaoController implements Initializable {
    @FXML
    TextField declareSpotField, nameField;
    @FXML
    TextArea traceField, benhNenField;
    @FXML
    RadioButton BHYTYes, BHYTNo, symptomYes, symptomNo, covidContactYes, covidContactNo, countryContactYes, countryContactNo, symptomContactYes, symptomContactNo;

    @FXML
    DatePicker declareDate;
    @FXML
    Button saveDeclare;
    int idKB;
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveDeclare.setDisable(true);
        declareDate.valueProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue == null) return;
            if (!currentValue.isEqual(newValue)) saveDeclare.setDisable(false);
        });
        declareSpotField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        nameField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        traceField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        benhNenField.textProperty().addListener((observableValue, currentValue, newValue) -> {
            if (currentValue.isBlank()) return;
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        BHYTYes.selectedProperty().addListener((observableValue, currentValue, newValue) -> {
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        symptomYes.selectedProperty().addListener((observableValue, currentValue, newValue) -> {
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        countryContactYes.selectedProperty().addListener((observableValue, currentValue, newValue) -> {
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        covidContactYes.selectedProperty().addListener((observableValue, currentValue, newValue) -> {
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
        symptomContactYes.selectedProperty().addListener((observableValue, currentValue, newValue) -> {
            if (!currentValue.equals(newValue)) {
                saveDeclare.setDisable(false);
            }
        });
    }
    public void setField(int idKB, String declareSpotField, LocalDate declareDate, int nameField, String traceField,
                        boolean BHYT, boolean symptom, boolean covidContact, boolean countryContact, boolean symptomContact,
                         String benhNenField) {
        this.idKB = idKB;
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
    public void switchToChangeView(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("khaibao/chinhsuakhaibao-view.fxml"));
        Parent componentScene = fxmlLoader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(componentScene);
        stage.setScene(scene);
        stage.show();
    }
    public void onUpdate(ActionEvent event) throws SQLException {
        final LocalDate date = declareDate.getValue();
        final String place = declareSpotField.getText();
        final String trace = traceField.getText();
        final boolean BHYT = BHYTYes.isSelected();
        final boolean symptom = symptomYes.isSelected();
        final boolean covidContact = covidContactYes.isSelected();
        final boolean countryContact = countryContactYes.isSelected();
        final boolean symptomContact = symptomContactYes.isSelected();
        final String benhNen = benhNenField.getText();
        KhaiBaoModel.update(idKB, place, date, BHYT, trace, symptom, covidContact, countryContact, symptomContact, benhNen);
        //TODO: đóng cửa sổ, thông báo thành công/thất bại, chuyển đến trang tìm kiếm
        Utility.displaySuccessDialog("Chỉnh sửa thành công!");
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
