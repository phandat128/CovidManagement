package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.Utility;
import covidmanagement.model.XetNghiemModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ChinhSuaController implements Initializable {
    @FXML TextField idNKField, nameField;

    @FXML DatePicker dateRangeFrom, dateRangeTo;

    @FXML TableView<XetNghiemModel> searchTable;

    @FXML TableColumn<XetNghiemModel, Integer> idColumn;

    @FXML TableColumn<XetNghiemModel, String> idNKColumn;

    @FXML TableColumn<XetNghiemModel, String> nameColumn;

    @FXML TableColumn<XetNghiemModel, LocalDate> dateColumn;

    @FXML TableColumn<XetNghiemModel, String> placeColumn;

    @FXML TableColumn<XetNghiemModel, XetNghiemModel.KetQuaXetNghiem> resultColumn;

    @FXML TableColumn<XetNghiemModel, Button> changeButtonColumn;

    @FXML TableColumn<XetNghiemModel, Button> deleteButtonColumn;

    private final ObservableList<XetNghiemModel> xetNghiemList = FXCollections.observableArrayList(
            XetNghiemModel.getXetNghiemList()
    );
    private final FilteredList<XetNghiemModel> filteredList = new FilteredList<>(xetNghiemList);

    @FXML ChoiceBox<XetNghiemModel.KetQuaXetNghiem> resultSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultSearch.setItems(FXCollections.observableArrayList(
                XetNghiemModel.KetQuaXetNghiem.values()
        ));
        resultSearch.setOnAction(this::getResultChoice);
        // set date format to DD/MM/yyyy
        StringConverter<LocalDate> dateConverter = new StringConverter<LocalDate>() {
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            @Override
            public String toString(LocalDate localDate) {
                if (localDate == null) return "";
                return formatter.format(localDate);
            }
            @Override
            public LocalDate fromString(String s) {
                if (s.isBlank()) return null;
                return LocalDate.parse(s, formatter);
            }
        };
        dateRangeFrom.setConverter(dateConverter);
        dateRangeTo.setConverter(dateConverter);
        // set up for table view
        idNKColumn.setCellValueFactory(new PropertyValueFactory<>("maNK"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        changeButtonColumn.setCellValueFactory(new PropertyValueFactory<>("changeButton"));
        deleteButtonColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        searchTable.setItems(filteredList);
        // set serial number column
        idColumn.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        searchTable.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        idColumn.setSortable(false);
    }

    public void getResultChoice(ActionEvent event){
        // TODO here
        XetNghiemModel.KetQuaXetNghiem choice = resultSearch.getValue();
        System.out.println(choice);
    }

    public void onSearch(ActionEvent event) throws RuntimeException{
        int idNK = 0;
        try {
            if (!idNKField.getText().isBlank())
                idNK = Integer.parseInt(idNKField.getText());
        } catch (NumberFormatException e){
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        String name = nameField.getText();
        LocalDate from = dateRangeFrom.getValue();
        LocalDate to = dateRangeTo.getValue();
        XetNghiemModel.KetQuaXetNghiem result = resultSearch.getValue();

        if (from != null && to != null){
            if (from.isAfter(to)) {
                Utility.displayExceptionDialog(new RuntimeException("Khoảng thời gian không hợp lệ!"));
                return;
            }
        }

        System.out.println(idNK == 0 ? null : idNK);
        System.out.println(name);
        System.out.println(from);
        System.out.println(to);
        System.out.println(result);

        final int finalIdNK = idNK;
        filteredList.setPredicate(xetNghiemRow -> {
            if (finalIdNK != 0 && xetNghiemRow.getMaNK() != finalIdNK) return false;
            if (!name.isBlank() && !xetNghiemRow.getName().contains(name)) return false;
            if (from != null && xetNghiemRow.getDate().isBefore(from)) return false;
            if (to != null && xetNghiemRow.getDate().isAfter(to)) return false;
            if (result != null && xetNghiemRow.getResult() != result) return false;
            return true;
        });
    }

    public void resetAllFields(ActionEvent event){
        idNKField.setText("");
        nameField.setText("");
        dateRangeFrom.setValue(null);
        dateRangeTo.setValue(null);
        resultSearch.setValue(null);
        onSearch(event);
    }
}