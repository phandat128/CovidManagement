package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.Utility;
import covidmanagement.model.XetNghiemModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TimKiemController implements Initializable {
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
            new XetNghiemModel(1,23, "hoang", "172366327", LocalDate.of(2022,1,2),
                    "wheraes", XetNghiemModel.KetQuaXetNghiem.NEGATIVE),
            new XetNghiemModel(2,12, "nam", "132534245", LocalDate.of(2022,1,3),
                    "asd", XetNghiemModel.KetQuaXetNghiem.POSITIVE)
    );

    @FXML ChoiceBox<XetNghiemModel.KetQuaXetNghiem> resultSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultSearch.setItems(FXCollections.observableArrayList(
                XetNghiemModel.KetQuaXetNghiem.values()
        ));
        resultSearch.setOnAction(this::getResultChoice);



        idNKColumn.setCellValueFactory(new PropertyValueFactory<>("maNK"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));
        changeButtonColumn.setCellValueFactory(new PropertyValueFactory<>("changeButton"));
        deleteButtonColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        searchTable.setItems(xetNghiemList);

        //set serial number column
        idColumn.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        searchTable.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        idColumn.setSortable(false);
    }

    public void getResultChoice(ActionEvent event){
        //TODO here
        XetNghiemModel.KetQuaXetNghiem choice = resultSearch.getValue();
        System.out.println(choice);
    }

    public void onSearch(ActionEvent event) throws RuntimeException{
        int idNK = 0;
        try {
            if (!idNKField.getText().isBlank()) idNK = Integer.parseInt(idNKField.getText());
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
    }
}