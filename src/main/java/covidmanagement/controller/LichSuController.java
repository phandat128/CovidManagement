package covidmanagement.controller;

import covidmanagement.Utility;
import covidmanagement.model.LichSuModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LichSuController implements Initializable {
    @FXML
    CheckBox selectAll, selectNhanKhau, selectHoKhau, selectKhaiBao, selectXetNghiem, selectCachLy;
    @FXML
    TableView<LichSuModel> lichSuTable;
    @FXML
    TableColumn<LichSuModel, String> timeColumn;
    @FXML
    TableColumn<LichSuModel, String> infoColumn;

    private final ObservableList<LichSuModel> lichSu = FXCollections.observableArrayList(
            LichSuModel.getLichSu()
    );
    private final FilteredList<LichSuModel> filteredList = new FilteredList<>(lichSu);
    private final List<CheckBox> checkBoxList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkBoxList.add(selectCachLy);
        checkBoxList.add(selectHoKhau);
        checkBoxList.add(selectNhanKhau);
        checkBoxList.add(selectKhaiBao);
        checkBoxList.add(selectXetNghiem);

        timeColumn.setCellValueFactory(row -> new SimpleStringProperty(Utility.DATE_CONVERTER.toString(row.getValue().getThoiGian())));
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("thongTin"));
        lichSuTable.setItems(filteredList);

        selectAll.selectedProperty().addListener((observableValue, oldValue, newValue) -> {
            for (CheckBox checkBox: checkBoxList){
                checkBox.setSelected(newValue);
            }
        });
        for (CheckBox checkBox: checkBoxList){
            checkBox.selectedProperty().addListener((observableValue, oldValue, newValue) -> onSearch());
        }

        lichSuTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void onSearch(){
        filteredList.setPredicate(row -> {
            for (CheckBox checkBox: checkBoxList) {
                if (!checkBox.isSelected() && row.getThongTin().contains(checkBox.getText().toLowerCase()))
                    return false;
            }
            return true;
        });
    }

}
