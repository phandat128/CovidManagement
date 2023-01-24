package covidmanagement.controller.cachlycontroller;

import covidmanagement.Utility;
import covidmanagement.model.CachLyModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class TimKiemCachLyController implements Initializable {
    @FXML TextField MaNKCl, nameCl;

    @FXML DatePicker dateBeginRangeFromCl, dateBeginRangeToCl, dateFinishRangeFromCl,dateFinishRangeToCl;

    @FXML TableView<CachLyModel> searchTableCl;

    @FXML TableColumn<CachLyModel, Integer> idColumnCl;

    @FXML TableColumn<CachLyModel, String> idNKColumnCl;

    @FXML TableColumn<CachLyModel, String> nameColumnCl;

    @FXML TableColumn<CachLyModel, LocalDate> dateBeginColumnCl;
    @FXML TableColumn<CachLyModel, LocalDate> dateFinishColumnCl;
    @FXML TableColumn<CachLyModel, String> placeColumnCl;


    private final ObservableList<CachLyModel> cachLyList = FXCollections.observableArrayList(
            CachLyModel.getCachLyList()
    );
    private final FilteredList<CachLyModel> filteredList = new FilteredList<>(cachLyList);



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
        dateBeginRangeFromCl.setConverter(dateConverter);
        dateBeginRangeToCl.setConverter(dateConverter);
        dateFinishRangeFromCl.setConverter(dateConverter);
        dateBeginRangeToCl.setConverter(dateConverter);

        idNKColumnCl.setCellValueFactory(new PropertyValueFactory<>("maNK"));
        nameColumnCl.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateBeginColumnCl.setCellValueFactory(new PropertyValueFactory<>("beginDate"));
        dateFinishColumnCl.setCellValueFactory(new PropertyValueFactory<>("finishDate"));
        placeColumnCl.setCellValueFactory(new PropertyValueFactory<>("place"));


        searchTableCl.setItems(filteredList);

        //set serial number column
        idColumnCl.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        searchTableCl.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        idColumnCl.setSortable(false);
    }



    public void onSearchCl(ActionEvent event) throws RuntimeException{
        int idNK = 0;
        try {
            if (!MaNKCl.getText().isBlank()) idNK = Integer.parseInt(MaNKCl.getText());
        } catch (NumberFormatException e){
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        final String name = nameCl.getText();
        final LocalDate beginfrom = dateBeginRangeFromCl.getValue();
        final LocalDate beginto = dateBeginRangeToCl.getValue();
        final LocalDate finishfrom =dateFinishRangeFromCl.getValue();
        final LocalDate finishto  =dateFinishRangeToCl.getValue();

        if ((beginfrom != null && beginto != null) || (finishfrom != null && finishto != null)){
            if (beginfrom.isAfter(beginto) || (finishfrom.isAfter(finishto))) {
                Utility.displayExceptionDialog(new RuntimeException("Khoảng thời gian không hợp lệ!"));
                return;
            }
        }




        System.out.println(idNK == 0 ? null : idNK);
        System.out.println(name);
        System.out.println(beginfrom);
        System.out.println(beginto);

    final int finalIdNK = idNK;
        filteredList.setPredicate(cachLyRow -> {
        if (finalIdNK != 0 && cachLyRow.getMaNK() != finalIdNK) return false;
        if (!name.isBlank() && !cachLyRow.getName().contains(name)) return false;
        if (beginfrom != null && cachLyRow.getBeginDate().isBefore(beginfrom)) return false;
        if (finishto != null && cachLyRow.getBeginDate().isAfter(finishto)) return false;
        return true;
    });
}

    public void resetAllFieldsCl(ActionEvent event){
        MaNKCl.setText("");
        nameCl.setText("");
        dateBeginRangeFromCl.setValue(null);
        dateBeginRangeToCl.setValue(null);
        dateFinishRangeFromCl.setValue(null);
        dateFinishRangeToCl.setValue(null);
        onSearchCl(event);
    }
}
