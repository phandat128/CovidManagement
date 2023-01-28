package covidmanagement.controller.hokhaucontroller;

import covidmanagement.Utility;
import covidmanagement.model.HoKhauModel;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TimKiemHoKhauController implements Initializable {
    @FXML
    TextField thanhPho, quan, phuong, duong;
    @FXML
    TableView<HoKhauModel> table;
    @FXML
    TableColumn<HoKhauModel, Integer> sttColumn;

    @FXML
    TableColumn<HoKhauModel, String> maHoKhauColumn;
    @FXML
    TableColumn<HoKhauModel, String> maChuHoColumn;
    @FXML
    TableColumn<HoKhauModel, String> soNhaColumn;
    @FXML
    TableColumn<HoKhauModel, String> ngachColumn;
    @FXML
    TableColumn<HoKhauModel, String> ngoColumn;
    @FXML
    TableColumn<HoKhauModel, String> duongColumn;
    @FXML
    TableColumn<HoKhauModel, String> phuongColumn;
    @FXML
    TableColumn<HoKhauModel, String> quanColumn;
    @FXML
    TableColumn<HoKhauModel, String> thanhPhoColumn;
    private final ObservableList<HoKhauModel> hoKhauList = FXCollections.observableArrayList(
            HoKhauModel.getHoKhauList()
    );
    private final FilteredList<HoKhauModel> filteredList = new FilteredList<>(hoKhauList);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        maHoKhauColumn.setCellValueFactory(new PropertyValueFactory<>("maHK"));
        maChuHoColumn.setCellValueFactory(new PropertyValueFactory<>("maCH"));
        soNhaColumn.setCellValueFactory(new PropertyValueFactory<>("soNha"));
        ngachColumn.setCellValueFactory(new PropertyValueFactory<>("ngach"));
        ngoColumn.setCellValueFactory(new PropertyValueFactory<>("ngo"));
        duongColumn.setCellValueFactory(new PropertyValueFactory<>("duong"));
        phuongColumn.setCellValueFactory(new PropertyValueFactory<>("phuong"));
        quanColumn.setCellValueFactory(new PropertyValueFactory<>("quan"));
        thanhPhoColumn.setCellValueFactory(new PropertyValueFactory<>("thanhPho"));


        table.setItems(filteredList);

        //set serial number column
        sttColumn.setCellValueFactory(
                cellDataFeatures -> new ReadOnlyObjectWrapper<>(
                        table.getItems().indexOf(cellDataFeatures.getValue()) + 1
                ));
        sttColumn.setSortable(false);
    }
    public void timKiem(ActionEvent event) throws RuntimeException{
        final String tp = thanhPho.getText();
        final String qu = quan.getText();
        final String ph = phuong.getText();
        final String d=duong.getText();


        System.out.println(tp);
        System.out.println(qu);
        System.out.println(ph);
        System.out.println(d);
        filteredList.setPredicate(hoKhauRow -> {
            if (!tp.isBlank() && !hoKhauRow.getThanhPho().contains(tp)) return false;
            if (!qu.isBlank() && !hoKhauRow.getQuan().contains(qu)) return false;
            if (!ph.isBlank() && !hoKhauRow.getPhuong().contains(ph)) return false;
            if (!d.isBlank() && !hoKhauRow.getDuong().contains(d)) return false;

            return true;
        });
    }
    public void xoaTimKiem (ActionEvent event) {
        thanhPho.setText("");
        quan.setText("");
        phuong.setText("");
        duong.setText("");
        timKiem(event);
    }
}