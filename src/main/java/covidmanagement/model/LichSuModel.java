package covidmanagement.model;

import covidmanagement.database.QueryDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LichSuModel {
    private Date thoiGian;
    private String thongTin;

    public LichSuModel(Date thoiGian, String thongTin) {
        this.thoiGian = thoiGian;
        this.thongTin = thongTin;
    }

    public static void add(String thongtin){
        try {
            QueryDB queryDB = new QueryDB();
            PreparedStatement statement = queryDB.getConnection().prepareStatement(
                    "INSERT INTO LichSu(ThongTin) VALUES (?);"
            );
            statement.setString(1, thongtin);
            statement.executeUpdate();
            statement.close();
            queryDB.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static List<LichSuModel> getLichSu(){
        List<LichSuModel> lichSu = new ArrayList<>();
        try {
            QueryDB queryDB = new QueryDB();
            ResultSet rs = queryDB.executeQuery("SELECT * FROM LichSu");
            while(rs.next()) {
                Date _thoiGian = rs.getTimestamp("thoigian");
                String _thongTin = rs.getString("thongtin");
                System.out.println(_thoiGian + _thongTin);
                lichSu.add(new LichSuModel(_thoiGian, _thongTin));
            }
            Collections.reverse(lichSu);
            queryDB.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lichSu;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public String getThongTin() {
        return thongTin;
    }
}
