package covidmanagement.controller.cachlycontroller;

import covidmanagement.Utility;
import covidmanagement.model.CachLyModel;
import covidmanagement.model.CachLyModel.MucDoCachLy;
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

    @FXML TableColumn<CachLyModel, CachLyModel.MucDoCachLy> mucdoColumCl;


    private final ObservableList<CachLyModel> cachLyList = FXCollections.observableArrayList(
            CachLyModel.getCachLyList()
    );
    private final FilteredList<CachLyModel> filteredList = new FilteredList<>(cachLyList);

    private SortedList<CachLyModel> sortedList = new SortedList<>(filteredList);

    @FXML ChoiceBox<CachLyModel.MucDoCachLy> mucdoClSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mucdoClSearch.setItems(FXCollections.observableArrayList(
                CachLyModel.MucDoCachLy.values()
        ));
        mucdoClSearch.setOnAction(this::getMucdoChoice);
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
        mucdoColumCl.setCellValueFactory(new PropertyValueFactory<>("mucdo"));

        sortedList.setComparator((o1, o2) -> {
            if (o1.getBeginDate().isBefore(o2.getBeginDate())) return -1;
            if (o1.getBeginDate().isAfter(o2.getBeginDate())) return 1;
            return 0;
        });
        searchTableCl.setItems(filteredList);

        //set serial number column
        idColumnCl.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        searchTableCl.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        idColumnCl.setSortable(false);
    }
    public void getMucdoChoice(ActionEvent event){
        //TODO here
        CachLyModel.MucDoCachLy choice = mucdoClSearch.getValue();
        System.out.println(choice);
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
        final CachLyModel.MucDoCachLy mucdo = mucdoClSearch.getValue();


        if (beginfrom != null && beginto != null){
            if (beginfrom.isAfter(beginto)) {
                Utility.displayWarningDialog("Khoảng thời gian không hợp lệ!");
                return;
            }
        }

        if (finishfrom != null && finishto != null){
            if (finishfrom.isAfter(finishto)) {
                Utility.displayWarningDialog("Khoảng thời gian không hợp lệ!");
                return;
            }
        }

        if (beginfrom != null && finishto != null){
            if (beginfrom.isAfter(finishto)) {
                Utility.displayWarningDialog("Khoảng thời gian không hợp lệ!");
                return;
            }
        }



        final int finalIdNK = idNK;
        filteredList.setPredicate(cachLyRow -> {
        if (finalIdNK != 0 && cachLyRow.getMaNK() != finalIdNK) return false;
        if (!name.isBlank() && !cachLyRow.getName().contains(name)) return false;
        if (beginfrom != null && cachLyRow.getBeginDate().isBefore(beginfrom)) return false;
        if (beginto != null && cachLyRow.getBeginDate().isAfter(beginto)) return false;
        if (finishfrom != null && cachLyRow.getFinishDate().isBefore(finishfrom)) return false;
        if (finishto != null && cachLyRow.getFinishDate().isAfter(finishto)) return false;
        if (mucdo != null && cachLyRow.getMucdo() != mucdo) return false;

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
        mucdoClSearch.setValue(null);
        onSearchCl(event);
    }
}
