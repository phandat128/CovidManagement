package covidmanagement.controller.khaibaocontroller;

import covidmanagement.Utility;
import covidmanagement.model.KhaiBaoModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;

public class TimKiemKhaiBaoController implements Initializable {
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
    @FXML TableColumn<KhaiBaoModel, Button> viewColumn;

    private ObservableList<KhaiBaoModel> khaiBaoList = FXCollections.observableArrayList(
            KhaiBaoModel.getKhaiBaoList()
    );
    private FilteredList<KhaiBaoModel> filteredList = new FilteredList<>(khaiBaoList);
    private SortedList<KhaiBaoModel> sortedList = new SortedList<>(filteredList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        trieuchungColumn.setCellValueFactory(new PropertyValueFactory<>("trieuChung"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanKhau"));
        declareDateColumn.setCellValueFactory(new PropertyValueFactory<>("ngayKhaiBao"));
        declareSpotColumn.setCellValueFactory(new PropertyValueFactory<>("diemKhaiBao"));
        viewColumn.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
        hotenColumn.setCellValueFactory(new PropertyValueFactory<>("ten"));
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
//            if (!nameField.getText().isBlank())
//                idNK = Integer.parseInt(nameField.getText());
//        } catch (NumberFormatException e){
//            e.printStackTrace();
//            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
//            return;
//        }
        LocalDate from = dateRangeFrom.getValue();
        LocalDate to = dateRangeTo.getValue();
        String diemKhaiBao = declareSpot.getText();
        String hoTen = nameField.getText();

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
            if (!hoTen.isBlank() && khaiBaoRow.getTen().contains(hoTen)) return false;
            if (from != null && khaiBaoRow.getNgayKhaiBao().isBefore(from)) return false;
            if (to != null && khaiBaoRow.getNgayKhaiBao().isAfter(to)) return false;
            if (!diemKhaiBao.isBlank() && khaiBaoRow.getDiemKhaiBao().contains(diemKhaiBao)) return false;
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
