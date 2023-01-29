package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.KhaiBaoModel;
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
    @FXML TableColumn<KhaiBaoModel, String> hotenColumn;
    @FXML TableColumn<KhaiBaoModel, String> trieuchungColumn;
    @FXML TableColumn<KhaiBaoModel, LocalDate> declareDateColumn;
    @FXML TableColumn<KhaiBaoModel, LocalDate> declareSpotColumn;
    @FXML TableColumn<KhaiBaoModel, Button> changeColumn;
    @FXML TableColumn<KhaiBaoModel, Button> deleteColumn;
    private final ObservableList<KhaiBaoModel> khaiBaoList = FXCollections.observableArrayList(
            KhaiBaoModel.getKhaiBaoList()
    );
    private final FilteredList<KhaiBaoModel> filteredList = new FilteredList<>(khaiBaoList);

    public void switchToMainView(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Parent componentScene = fxmlLoader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(componentScene);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToChangeView(ActionEvent e) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("xetnghiem/suaxetnghiem-view.fxml"));
        Parent componentScene = fxmlLoader.load();
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Scene scene = new Scene(componentScene);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        trieuchungColumn.setCellValueFactory(new PropertyValueFactory<>("trieuChung"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        declareDateColumn.setCellValueFactory(new PropertyValueFactory<>("ngayKhaiBao"));
        declareSpotColumn.setCellValueFactory(new PropertyValueFactory<>("diemKhaiBao"));
        hotenColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        changeColumn.setCellValueFactory(new PropertyValueFactory<>("changeButton"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

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
            if (!nameField.getText().isBlank()) idNK = Integer.parseInt(nameField.getText());
        } catch (NumberFormatException e){
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        final String diemKhaiBao = declareSpot.getText();
        final LocalDate from = dateRangeFrom.getValue();
        final LocalDate to = dateRangeTo.getValue();


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
