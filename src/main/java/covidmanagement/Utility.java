package covidmanagement;

import covidmanagement.database.QueryDB;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Utility {
    //chuyển từ có dấu sang không dấu không cách
    public static String removeAccent(String s){
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace(" ", "");
    }
    //hiển thị hộp thông báo lỗi
    public static void displayExceptionDialog(Exception e){
        e.printStackTrace();

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(e.getMessage());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    //hộp tin xác nhận xóa hàng có chỉ số id trong bảng tableName trong cơ sở dữ liệu
    public static void displayConfirmDialog(String message, int id, String tableName){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Are you sure?");
        alert.setHeaderText(message);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK){
            //remove id in tableName
            if (tableName.toLowerCase().equals("hokhau")){
                //TODO here about delete one row in hokhau table
                //đặt mã hộ khẩu trong bảng nhân khẩu về 0

                return;
            }
            String sql = "DELETE FROM " + tableName + " WHERE ma" + tableName + "=" + id + ";";
            System.out.println(sql);
//            try {
//                QueryDB queryDB = new QueryDB();
//                queryDB.query(sql);
//            } catch (SQLException e){
//                e.printStackTrace();
//            }
        }
    }
}
