package covidmanagement.controller;

import covidmanagement.Utility;
import covidmanagement.model.CachLyModel;
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



    @FXML TableColumn<CachLyModel, Button> changeButtonColumnCl;

    @FXML TableColumn<CachLyModel, Button> deleteButtonColumnCl;

    private final ObservableList<CachLyModel> CachLyList = FXCollections.observableArrayList(
            new CachLyModel(1,23, "hoang", "172366327", LocalDate.of(2022,1,2),
                    LocalDate.of(2022,1,17),"bach khoa"
            ),

            new CachLyModel(2,12,"long","124124124", LocalDate.of(2022,1,3),
                    LocalDate.of(2022,1,18),"truong dinh"
            )
    );



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        idNKColumnCl.setCellValueFactory(new PropertyValueFactory<>("maNK"));
        nameColumnCl.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateBeginColumnCl.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateFinishColumnCl.setCellValueFactory(new PropertyValueFactory<>("date"));
        placeColumnCl.setCellValueFactory(new PropertyValueFactory<>("place"));
        changeButtonColumnCl.setCellValueFactory(new PropertyValueFactory<>("changeButton"));
        deleteButtonColumnCl.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        searchTableCl.setItems(CachLyList);

        //set serial number column
        idColumnCl.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        searchTableCl.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        idColumnCl.setSortable(false);
    }



    public void onSearchCl(ActionEvent event) throws RuntimeException{
        int idNKCl = 0;
        try {
            if (!MaNKCl.getText().isBlank()) idNKCl = Integer.parseInt(MaNKCl.getText());
        } catch (NumberFormatException e){
            e.printStackTrace();
            Utility.displayExceptionDialog(new NumberFormatException("Mã nhân khẩu chỉ được chứa chữ số!"));
            return;
        }
        String name = nameCl.getText();
        LocalDate beginfrom = dateBeginRangeFromCl.getValue();
        LocalDate beginto = dateBeginRangeToCl.getValue();
        LocalDate finishfrom =dateFinishRangeFromCl.getValue();
        LocalDate finishto  =dateFinishRangeToCl.getValue();

        if (beginfrom != null && beginto != null && finishfrom != null && finishto != null){
            if (beginfrom.isAfter(beginto) || (finishfrom.isAfter(finishto))) {
                Utility.displayExceptionDialog(new RuntimeException("Khoảng thời gian không hợp lệ!"));
                return;
            }
        }




        System.out.println(idNKCl == 0 ? null : idNKCl);
        System.out.println(name);
        System.out.println(beginfrom);
        System.out.println(beginto);

    }
}
