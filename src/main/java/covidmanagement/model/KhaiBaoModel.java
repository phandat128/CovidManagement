package covidmanagement.model;

import java.sql.*;

public class KhaiBaoModel {
    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/test",
                            "postgres", "123123");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TYPE trieuchung AS ENUM ('Sốt', 'Ho', 'Khó thở', 'Viêm phổi', 'Đau họng', 'Mệt mỏi', 'Không');" +
                    "CREATE TYPE tiepxuc AS ENUM ('Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19', 'Người từ nước có bệnh COVID-19', 'Người có biểu hiện (Sốt, ho, khó thở, Viêm phổi)', 'Không');" +
                    "CREATE TYPE benhnen AS ENUM ('Bệnh gan mãn tính', 'Bệnh máu mãn tính', 'Bệnh phổi mãn tính', 'Bệnh thận mãn tính', 'Bệnh tim mạch', 'Huyết áp cao', 'Suy giảm miễn dịch', 'Người nhận ghép tạng, Tủy xương', 'Tiểu đường', 'Ung thư', 'Không');" +
                    "CREATE TYPE diemkhaibao AS ENUM ('Quận Hoàng Mai', 'Quận Long Biên', 'Quận Thanh Xuân', 'Quận Bắc Từ Liêm', 'Quận Ba Đình', 'Quận Cầu Giấy', 'Quận Đống Đa', 'Quận Hai Bà Trưng', 'Quận Hoàn Kiếm', 'Quận Hà Đông', 'Quận Tây Hồ', 'Quận Nam Từ Liêm');" +
                    "CREATE TABLE IF NOT EXIST KHAIBAOYTE " +
                    "CREATE TYPE gioitinh AS ENUM ('Nam', 'Nữ');" +
                    "(ID INT PRIMARY KEY                NOT NULL," +
                    " NGAYKHAIBAO       DATE            NOT NULL," +
                    " DIEMKHAIBAO       diemkhaibao     NOT NULL," +
                    " HOTEN             CHAR(50)        NOT NULL," +
                    " GIOITINH          gioitinh        NOT NULL," +
                    " CMND              CHAR(20)        NOT NULL," +
                    " DIACHI            CHAR(50)," +
                    " BHYT              BOOL            NOT NULL," +
                    " DIAPHUONGDIQUA    TEXT            NOT NULL," +
                    " TRIEUCHUNG        trieuchung      NOT NULL," +
                    " TIEPXUC           tiepxuc         NOT NULL," +
                    " BENHNEN           benhnen         NOT NULL);";
            String sql2 = " ";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql2);
            stmt.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
