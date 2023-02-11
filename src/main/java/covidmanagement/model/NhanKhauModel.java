package covidmanagement.model;

import covidmanagement.database.QueryDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhanKhauModel {
    private int MaNhanKhau;
    private String HoTen;
    private LocalDate NgaySinh;
    private String GioiTinh;
    private String CMNDCCCD;
    private String SDT;
    private String QuocTich;
    private String TonGiao;
    private String NguyenQuan;
    private String NgheNghiep;
    private int MaHoKhau;
    private Boolean LaChuHo;
    private String QuanHeVoiChuHo;
    private String ghiChu;

    public NhanKhauModel(int MaNhanKhau, String HoTen, LocalDate NgaySinh, String GioiTinh, String CMNDCCCD, String SDT,
                         String QuocTich, String TonGiao, String NguyenQuan, int MaHoKhau, Boolean LaChuHo, String QuanHeVoiChuHo, String NgheNghiep, String ghiChu){
        this.MaNhanKhau = MaNhanKhau;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.CMNDCCCD = CMNDCCCD;
        this.SDT = SDT;
        this.QuocTich = QuocTich;
        this.TonGiao = TonGiao;
        this.NguyenQuan = NguyenQuan;
        this.MaHoKhau = MaHoKhau;
        this.LaChuHo = LaChuHo;
        this.QuanHeVoiChuHo = QuanHeVoiChuHo;
        this.NgheNghiep = NgheNghiep;
        this.ghiChu = ghiChu;
    }

    public static List<NhanKhauModel> getNhanKhauList(){
        List<NhanKhauModel> NhanKhauList = new ArrayList<>();
        try {
            QueryDB queryDB = new QueryDB();
            String sql = "select * from nhankhau;";

            ResultSet rs = queryDB.executeQuery(sql);
            while (rs.next()) {
                int _maNhanKhau = rs.getInt("manhankhau");
                String _hoVaTen = rs.getString("hoten");
                LocalDate _ngaySinh = rs.getDate("ngaysinh").toLocalDate();
                String _gioiTinh = rs.getString("gioitinh");
                String _cmnd_CCCD_ = rs.getString("cmnd_cccd");
                String _sDT = rs.getString("sdt");
                String _quocTich = rs.getString("quoctich");
                String _tonGiao = rs.getString("tongiao");
                String _nguyenQuan = rs.getString("nguyenquan");
                int _maHoKhau = rs.getInt("mahokhau");
                Boolean _laChuHo = rs.getBoolean("lachuho");
                String _quanHeVoiChuHo = rs.getString("quanhevoichuho");
                String _ngheNghiep = rs.getString("nghenghiep");
                String _ghiChu = rs.getString("ghichu");
                NhanKhauList.add(new NhanKhauModel(_maNhanKhau, _hoVaTen, _ngaySinh, _gioiTinh, _cmnd_CCCD_, _sDT, _quocTich,
                        _tonGiao, _nguyenQuan, _maHoKhau, _laChuHo, _quanHeVoiChuHo, _ngheNghiep, _ghiChu));

            }
            rs.close();
            queryDB.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return NhanKhauList;
    }

    public static void addNhanKhau( String hoVaTen, String gioiTinh, LocalDate ngaySinh, String cmnd_CCCD_,
                                   String quocTich, String tonGiao, String sDT, String nguyenQuan, String ngheNghiep,
                                   int maHoKhau, Boolean laChuHo, String quanHeVoiChuHo, String ghiChu) throws SQLException {
        PreparedStatement preparedStatement = null;
        QueryDB queryDB = null;
        try {
            queryDB = new QueryDB();
            preparedStatement = queryDB.getConnection().prepareStatement(
                    "INSERT INTO nhankhau(hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghichu) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"
            );
            preparedStatement.setString(1, hoVaTen);
            preparedStatement.setDate(2, Date.valueOf(ngaySinh));
            preparedStatement.setString(3, gioiTinh);
            preparedStatement.setString(4, cmnd_CCCD_);
            preparedStatement.setString(5, sDT);
            preparedStatement.setString(6, quocTich);
            preparedStatement.setString(7, tonGiao);
            preparedStatement.setString(8, nguyenQuan);
            preparedStatement.setInt(9, maHoKhau);
            preparedStatement.setBoolean(10, laChuHo);
            preparedStatement.setString(11, quanHeVoiChuHo);
            preparedStatement.setString(12, ngheNghiep);
            preparedStatement.setString(13, ghiChu);

            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Thêm nhân khẩu thành công!!");
                alert.show();
            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Thêm nhân khẩu thất bại!!");
                alert.show();
            }
            preparedStatement.close();
            queryDB.close();

            LichSuModel.add("Thêm một nhân khẩu trong hộ số " + maHoKhau);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi khi thêm dữ liệu: " + e.getMessage()+"." + "Vui lòng nhập lại!!");
            alert.show();
        }
    }

    public static void updateNhanKhau(int maNhanKhau, String hoVaTen, String gioiTinh, LocalDate ngaySinh, String cmnd_CCCD_,
                                      String quocTich, String tonGiao, String sDT, String nguyenQuan, String ngheNghiep,
                                      int maHoKhau, Boolean laChuHo, String quanHeVoiChuHo, String ghiChu) throws SQLException {
        PreparedStatement preparedStatement = null;
        QueryDB queryDB = null;
        try {
            queryDB = new QueryDB();
            preparedStatement = queryDB.getConnection().prepareStatement(
                    "UPDATE nhankhau SET (hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, " +
                            "nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep, ghiChu) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                            "WHERE manhankhau = ?;"
            );
            preparedStatement.setString(1, hoVaTen);
            preparedStatement.setDate(2, Date.valueOf(ngaySinh));
            preparedStatement.setString(3, gioiTinh);
            preparedStatement.setString(4, cmnd_CCCD_);
            preparedStatement.setString(5, sDT);
            preparedStatement.setString(6, quocTich);
            preparedStatement.setString(7, tonGiao);
            preparedStatement.setString(8, nguyenQuan);
            preparedStatement.setInt(9, maHoKhau);
            preparedStatement.setBoolean(10, laChuHo);
            preparedStatement.setString(11, quanHeVoiChuHo);
            preparedStatement.setString(12, ngheNghiep);
            preparedStatement.setString(13, ghiChu);
            preparedStatement.setInt(14, maNhanKhau);
            int result = preparedStatement.executeUpdate();
            if (result == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Cập nhật nhân khẩu thành công!!");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Cập nhật nhân khẩu thất bại!!");
                alert.show();
            }
            preparedStatement.close();
            queryDB.close();

            LichSuModel.add("Sửa đổi thông tin nhân khẩu số " + maNhanKhau);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi khi cập nhật dữ liệu: " + e.getMessage() + "." + "Vui lòng nhập lại!!");
            alert.show();
        }
    }

    public static void deleteNhanKhau(int maNhanKhau) throws SQLException {
        PreparedStatement preparedStatement = null;
        QueryDB queryDB = null;
        try {
            queryDB = new QueryDB();
            preparedStatement = queryDB.getConnection().prepareStatement(
                    "DELETE FROM nhankhau WHERE manhankhau = ?;");
            preparedStatement.setInt(1, maNhanKhau);
            int result = preparedStatement.executeUpdate();
//            if (result == 1) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setHeaderText("Xóa nhân khẩu thành công trong database!!");
//                alert.show();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setHeaderText("Xóa nhân khẩu thất bại!!");
//                alert.show();
//            }
            preparedStatement.close();
            queryDB.close();

            LichSuModel.add("Xóa nhân khẩu số " + maNhanKhau);
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Lỗi khi Xóa dữ liệu: " + e.getMessage() + "." + "Vui lòng thực hiện lại!!");
            alert.show();
        }
    }

    public static NhanKhauModel getInstanceById(int maNhanKhau) throws SQLException{
        QueryDB queryDB = new QueryDB();
        ResultSet rs = queryDB.executeQuery("SELECT * FROM NhanKhau WHERE maNhanKhau = " + maNhanKhau + ";");
        if (!rs.next()) throw new SQLException("Mã nhân khẩu không tồn tại");
        int _maNhanKhau = rs.getInt("manhankhau");
        String _hoVaTen = rs.getString("hoten");
        LocalDate _ngaySinh = rs.getDate("ngaysinh").toLocalDate();
        String _gioiTinh = rs.getString("gioitinh");
        String _cmnd_CCCD_ = rs.getString("cmnd_cccd");
        String _sDT = rs.getString("sdt");
        String _quocTich = rs.getString("quoctich");
        String _tonGiao = rs.getString("tongiao");
        String _nguyenQuan = rs.getString("nguyenquan");
        int _maHoKhau = rs.getInt("mahokhau");
        Boolean _laChuHo = rs.getBoolean("lachuho");
        String _quanHeVoiChuHo = rs.getString("quanhevoichuho");
        String _ngheNghiep = rs.getString("nghenghiep");
        String _ghiChu = rs.getString("ghichu");
        return new NhanKhauModel(_maNhanKhau, _hoVaTen, _ngaySinh, _gioiTinh, _cmnd_CCCD_, _sDT, _quocTich,
                _tonGiao, _nguyenQuan, _maHoKhau, _laChuHo, _quanHeVoiChuHo, _ngheNghiep, _ghiChu);
    }

    public static List<NhanKhauModel> getNhanKhauListByMaHK(int maHoKhau) throws SQLException{
        QueryDB queryDB = new QueryDB();
        ResultSet rs = queryDB.executeQuery("SELECT * FROM NhanKhau WHERE maHoKhau = " + maHoKhau + ";");
        if (!rs.isBeforeFirst()) throw new SQLException("Hộ khẩu không có người đăng ký");
        List<NhanKhauModel> nhanKhauList = new ArrayList<>();
        while (rs.next()) {
            int _maNhanKhau = rs.getInt("manhankhau");
            String _hoVaTen = rs.getString("hoten");
            LocalDate _ngaySinh = rs.getDate("ngaysinh").toLocalDate();
            String _gioiTinh = rs.getString("gioitinh");
            String _cmnd_CCCD_ = rs.getString("cmnd_cccd");
            String _sDT = rs.getString("sdt");
            String _quocTich = rs.getString("quoctich");
            String _tonGiao = rs.getString("tongiao");
            String _nguyenQuan = rs.getString("nguyenquan");
            int _maHoKhau = rs.getInt("mahokhau");
            Boolean _laChuHo = rs.getBoolean("lachuho");
            String _quanHeVoiChuHo = rs.getString("quanhevoichuho");
            String _ngheNghiep = rs.getString("nghenghiep");
            String _ghiChu = rs.getString("ghichu");
            nhanKhauList.add(new NhanKhauModel(_maNhanKhau, _hoVaTen, _ngaySinh, _gioiTinh, _cmnd_CCCD_, _sDT, _quocTich,
                    _tonGiao, _nguyenQuan, _maHoKhau, _laChuHo, _quanHeVoiChuHo, _ngheNghiep, _ghiChu));
        }
        return nhanKhauList;
    }
    public static boolean isExistedChuHo(int maHK) throws SQLException {

        QueryDB queryDB = new QueryDB();
        ResultSet rs = queryDB.executeQuery("SELECT * FROM NhanKhau WHERE maHoKhau = " + maHK + ";");
        List<Boolean> lachuhoList = new ArrayList<>();
        while(rs.next()){
            Boolean _laChuHo = rs.getBoolean("lachuho");
            lachuhoList.add(_laChuHo);
        }
        ;
        for (Boolean chuho:lachuhoList){
            if(chuho)
                return true;
        }
        return false;
    }

    public static List getQHVCHByMaHK(int maHK) throws SQLException {

        QueryDB queryDB = new QueryDB();
        ResultSet rs = queryDB.executeQuery("SELECT * FROM NhanKhau WHERE maHoKhau = " + maHK + ";");
        List<String> qhvchList = new ArrayList<>();
        while (rs.next()) {
            String _quanHeVoiChuHo = rs.getString("quanhevoichuho");
            qhvchList.add(_quanHeVoiChuHo);
        }
        return qhvchList;
    }

    public static LocalDate getNgaySinhByQuanHe(int maHK, String Q) throws SQLException {

        QueryDB queryDB = new QueryDB();
        ResultSet rs = queryDB.executeQuery("SELECT * FROM NhanKhau WHERE maHoKhau = " + maHK + ";");
        if (!rs.isBeforeFirst()) throw new SQLException("Hộ khẩu không có người đăng ký");
        List<NhanKhauModel> nhanKhauList = new ArrayList<>();
        while (rs.next()) {
            int _maNhanKhau = rs.getInt("manhankhau");
            String _hoVaTen = rs.getString("hoten");
            LocalDate _ngaySinh = rs.getDate("ngaysinh").toLocalDate();
            String _gioiTinh = rs.getString("gioitinh");
            String _cmnd_CCCD_ = rs.getString("cmnd_cccd");
            String _sDT = rs.getString("sdt");
            String _quocTich = rs.getString("quoctich");
            String _tonGiao = rs.getString("tongiao");
            String _nguyenQuan = rs.getString("nguyenquan");
            int _maHoKhau = rs.getInt("mahokhau");
            Boolean _laChuHo = rs.getBoolean("lachuho");
            String _quanHeVoiChuHo = rs.getString("quanhevoichuho");
            String _ngheNghiep = rs.getString("nghenghiep");
            nhanKhauList.add(new NhanKhauModel(_maNhanKhau, _hoVaTen, _ngaySinh, _gioiTinh, _cmnd_CCCD_, _sDT, _quocTich,
                    _tonGiao, _nguyenQuan, _maHoKhau, _laChuHo, _quanHeVoiChuHo, _ngheNghiep));
        }
        LocalDate ns = null;
        for (NhanKhauModel nhankhau : nhanKhauList) {
            if (nhankhau.getQuanHeVoiChuHo().equalsIgnoreCase(Q)) {
                ns = nhankhau.getNgaySinh();
            }
        }
        System.out.println(ns);
        return ns;
    }

    public int getMaNhanKhau() { return MaNhanKhau; }
    public void setMaNhanKhau(int MaNhanKhau) {
        this.MaNhanKhau = MaNhanKhau;
    }
    public String getHoTen(){
        return HoTen;
    }
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }
    public LocalDate getNgaySinh() {
        return NgaySinh;
    }
    public void setNamSinh(LocalDate namSinh) {
        this.NgaySinh = namSinh;
    }
    public String getGioiTinh() {
        return GioiTinh;
    }
    public void setGioiTinh(String gioiTinh) {
        this.GioiTinh = gioiTinh;
    }
    public String getCMNDCCCD() {
        return CMNDCCCD;
    }

    public void setCMNDCCCD(String CMNDCCCD) {
        this.CMNDCCCD = CMNDCCCD;
    }

    public String getSDT(){
        return SDT;
    }
    public void setSDT(String SDT){
        this.SDT = SDT;
    }
    public String getQuocTich() {
        return QuocTich;
    }
    public void setQuocTich(String quocTich) {
        this.QuocTich = quocTich;
    }
    public String getTonGiao() {
        return TonGiao;
    }
    public void setTonGiao(String tonGiao) {
        this.TonGiao = tonGiao;
    }
    public String getNguyenQuan(){ return NguyenQuan;}
    public void setNguyenQuan( String NguyenQuan){ this.NguyenQuan = NguyenQuan;}
    public String getNgheNghiep() {
        return NgheNghiep;
    }
    public void setNgheNghiep(String ngheNghiep) {
        this.NgheNghiep = ngheNghiep;
    }
    public int getMaHoKhau() {
        return MaHoKhau;
    }
    public void setMaHoKhau(int MaHoKhau) {
        this.MaHoKhau = MaHoKhau;
    }
    public Boolean getLaChuHo() {
        return LaChuHo;
    }
    public void setChuHo(Boolean LaChuHo) {
        this.LaChuHo = LaChuHo;
    }
    public String getQuanHeVoiChuHo() {
        return QuanHeVoiChuHo;
    }
    public void setQuanHeVoiChuHo(String QuanHeVoiChuHo) {
        this.QuanHeVoiChuHo = QuanHeVoiChuHo;
    }

    public String getGhiChu() {
        return ghiChu;
    }
}
