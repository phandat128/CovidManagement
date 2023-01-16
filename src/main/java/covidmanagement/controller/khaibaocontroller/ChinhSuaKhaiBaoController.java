package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.KhaiBaoModel;
import covidmanagement.model.XetNghiemModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ChinhSuaKhaiBaoController implements Initializable {
    @FXML
    TableView<KhaiBaoModel> searchTable;
    @FXML
    TextField nameField, declareSpot;
    @FXML
    DatePicker dateRangeFrom, dateRangeTo;
    @FXML
    TableColumn<KhaiBaoModel, Integer> countColumn;
    @FXML TableColumn<KhaiBaoModel, String> nameColumn;
    @FXML TableColumn<KhaiBaoModel, String> trieuchungColumn;
    @FXML TableColumn<KhaiBaoModel, LocalDate> declareDateColumn;
    @FXML TableColumn<KhaiBaoModel, LocalDate> declareSpotColumn;
    @FXML TableColumn<KhaiBaoModel, Button> viewColumn;

    private final ObservableList<KhaiBaoModel> khaiBaoList = FXCollections.observableArrayList(
            KhaiBaoModel.getKhaiBaoList()
    );
    private final FilteredList<KhaiBaoModel> filteredList = new FilteredList<>(khaiBaoList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trieuchungColumn.setCellValueFactory(new PropertyValueFactory<>("trieuChung"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        declareDateColumn.setCellValueFactory(new PropertyValueFactory<>("ngayKhaiBao"));
        declareSpotColumn.setCellValueFactory(new PropertyValueFactory<>("diemKhaiBao"));
        viewColumn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));

        searchTable.setItems(khaiBaoList);

        //set serial number column
        countColumn.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        searchTable.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        countColumn.setSortable(false);
    }
    public void onSearch(ActionEvent event) throws RuntimeException{
        int idNK = 0;
        try {
            if (!nameField.getText().isBlank())
                idNK = Integer.parseInt(nameField.getText());
        } catch (NumberFormatException e){
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        LocalDate from = dateRangeFrom.getValue();
        LocalDate to = dateRangeTo.getValue();
        final String diemKhaiBao = declareSpot.getText();

        if (from != null && to != null){
            if (from.isAfter(to)) {
                Utility.displayExceptionDialog(new RuntimeException("Khoảng thời gian không hợp lệ!"));
                return;
            }
        }

        final int finalIdNK = idNK;
        filteredList.setPredicate(khaiBaoRow -> {
            if (finalIdNK != 0 && khaiBaoRow.getMaNhanKhau() != finalIdNK) return false;
            if (from != null && khaiBaoRow.getNgayKhaiBao().isBefore(from)) return false;
            if (to != null && khaiBaoRow.getNgayKhaiBao().isAfter(to)) return false;
            if (diemKhaiBao != null && khaiBaoRow.getDiemKhaiBao() != diemKhaiBao) return false;
            return true;
        });
    }
}
