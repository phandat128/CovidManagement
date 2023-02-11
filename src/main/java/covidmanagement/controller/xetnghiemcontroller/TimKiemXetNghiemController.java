package covidmanagement.controller.xetnghiemcontroller;

import covidmanagement.Utility;
import covidmanagement.model.XetNghiemModel;
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

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TimKiemXetNghiemController implements Initializable {
    @FXML TextField idNKField, nameField;

    @FXML DatePicker dateRangeFrom, dateRangeTo;

    @FXML TableView<XetNghiemModel> searchTable;

    @FXML TableColumn<XetNghiemModel, Integer> idColumn;

    @FXML TableColumn<XetNghiemModel, String> idNKColumn;

    @FXML TableColumn<XetNghiemModel, String> nameColumn;

    @FXML TableColumn<XetNghiemModel, LocalDate> dateColumn;

    @FXML TableColumn<XetNghiemModel, String> placeColumn;

    @FXML TableColumn<XetNghiemModel, XetNghiemModel.KetQuaXetNghiem> resultColumn;

    private final ObservableList<XetNghiemModel> xetNghiemList = FXCollections.observableArrayList(
            XetNghiemModel.getXetNghiemList()
    );
    private final FilteredList<XetNghiemModel> filteredList = new FilteredList<>(xetNghiemList);
    private final SortedList<XetNghiemModel> sortedList = new SortedList<>(filteredList);

    @FXML ChoiceBox<XetNghiemModel.KetQuaXetNghiem> resultSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resultSearch.setItems(FXCollections.observableArrayList(
                XetNghiemModel.KetQuaXetNghiem.values()
        ));
        resultSearch.setOnAction(this::getResultChoice);
        // set date format to DD/MM/yyyy
        dateRangeFrom.setConverter(Utility.LOCAL_DATE_CONVERTER);
        dateRangeTo.setConverter(Utility.LOCAL_DATE_CONVERTER);
        // set up for table view
        idNKColumn.setCellValueFactory(new PropertyValueFactory<>("maNK"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("place"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));

        sortedList.setComparator((o1, o2) -> {
            if (o1.getDate().isBefore(o2.getDate())) return -1;
            if (o1.getDate().isAfter(o2.getDate())) return 1;
            return 0;
        });
        searchTable.setItems(filteredList);
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
            Utility.displayWarningDialog("Mã nhân khẩu chỉ được chứa chữ số!");
            return;
        }
        final String name = nameField.getText();
        final LocalDate from = dateRangeFrom.getValue();
        final LocalDate to = dateRangeTo.getValue();
        final XetNghiemModel.KetQuaXetNghiem result = resultSearch.getValue();

        if (from != null && to != null){
            if (from.isAfter(to)) {
                Utility.displayWarningDialog("Khoảng thời gian không hợp lệ!");
                return;
            }
        }

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