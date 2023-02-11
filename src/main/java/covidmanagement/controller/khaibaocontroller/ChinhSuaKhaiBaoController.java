package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Main;
import covidmanagement.Utility;
import covidmanagement.model.KhaiBaoModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private ObservableList<KhaiBaoModel> khaiBaoList = FXCollections.observableArrayList(
            KhaiBaoModel.getKhaiBaoList()
    );
    private FilteredList<KhaiBaoModel> filteredList = new FilteredList<>(khaiBaoList);
    private SortedList<KhaiBaoModel> sortedList = new SortedList<>(filteredList);

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
        
        dateRangeFrom.setConverter(Utility.LOCAL_DATE_CONVERTER);
        dateRangeTo.setConverter(Utility.LOCAL_DATE_CONVERTER);

        trieuchungColumn.setCellValueFactory(trieuchung -> new SimpleStringProperty(trieuchung.getValue().isTrieuChung() ? "Có" : "Không"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        declareDateColumn.setCellValueFactory(new PropertyValueFactory<>("ngayKhaiBao"));
        declareSpotColumn.setCellValueFactory(new PropertyValueFactory<>("diemKhaiBao"));
        hotenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
        changeColumn.setCellValueFactory(new PropertyValueFactory<>("changeButton"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        sortedList.setComparator((o1, o2) -> {
            if (o1.getNgayKhaiBao().isBefore(o2.getNgayKhaiBao())) return -1;
            if (o1.getNgayKhaiBao().isAfter(o2.getNgayKhaiBao())) return 1;
            return 0;
        });
        searchTable.setItems(khaiBaoList);

        //set serial number column
        countColumn.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        searchTable.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        countColumn.setSortable(false);
    }
    public void onSearch(ActionEvent event) throws RuntimeException{
//        int idNK = 0;
//        try {
//            if (!nameField.getText().isBlank()) idNK = Integer.parseInt(nameField.getText());
//        } catch (NumberFormatException e){
//            e.printStackTrace();
//            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
//            return;
//        }
        final String diemKhaiBao = declareSpot.getText();
        final LocalDate from = dateRangeFrom.getValue();
        final LocalDate to = dateRangeTo.getValue();
        final String hoTen = nameField.getText();
        if (from != null && to != null){
            if (from.isAfter(to)) {
                Utility.displayExceptionDialog(new RuntimeException("Khoảng thời gian không hợp lệ!"));
                return;
            }
        }
//        final int finalIdNK = idNK;

        ObservableList<KhaiBaoModel> data = FXCollections.observableArrayList(KhaiBaoModel.getKhaiBaoList());
        FilteredList<KhaiBaoModel> filteredData = new FilteredList<>(data, p -> true);

        filteredData.setPredicate(khaiBaoRow -> {
            if (!hoTen.isBlank() && !khaiBaoRow.getTen().contains(hoTen)) return false;
            if (from != null && khaiBaoRow.getNgayKhaiBao().isBefore(from)) return false;
            if (to != null && khaiBaoRow.getNgayKhaiBao().isAfter(to)) return false;
            if (!diemKhaiBao.isBlank() && !khaiBaoRow.getDiemKhaiBao().contains(diemKhaiBao)) return false;
            return true;
        });
        searchTable.setItems(filteredData);
    }
    public void reload(){
        khaiBaoList = FXCollections.observableArrayList(
                KhaiBaoModel.getKhaiBaoList()
        );
        filteredList = new FilteredList<>(khaiBaoList);
        sortedList = new SortedList<>(filteredList);
        sortedList.setComparator((o1, o2) -> {
            if (o1.getNgayKhaiBao().isBefore(o2.getNgayKhaiBao())) return -1;
            if (o1.getNgayKhaiBao().isAfter(o2.getNgayKhaiBao())) return 1;
            return 0;
        });
        searchTable.setItems(sortedList);
    }
}
