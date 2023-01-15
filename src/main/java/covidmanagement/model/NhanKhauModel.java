package covidmanagement.model;

import covidmanagement.Utility;
import covidmanagement.database.QueryDB;

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


    public NhanKhauModel(int MaNhanKhau, String HoTen, LocalDate NgaySinh, String GioiTinh, String CMNDCCCD, String SDT,
                         String QuocTich, String TonGiao, String NguyenQuan, int MaHoKhau, Boolean LaChuHo, String QuanHeVoiChuHo, String NgheNghiep){
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
                NhanKhauList.add(new NhanKhauModel(_maNhanKhau, _hoVaTen, _ngaySinh, _gioiTinh, _cmnd_CCCD_, _sDT, _quocTich,
                        _tonGiao, _nguyenQuan, _maHoKhau, _laChuHo, _quanHeVoiChuHo, _ngheNghiep));

            }

            rs.close();
            queryDB.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return NhanKhauList;
    }

    public static void addNhanKhau(int maNhanKhau, String hoVaTen, String gioiTinh, LocalDate ngaySinh, String cmnd_CCCD_,
                                   String quocTich, String tonGiao, String sDT, String nguyenQuan, String ngheNghiep,
                                   int maHoKhau, Boolean laChuHo, String quanHeVoiChuHo) throws SQLException{
        QueryDB queryDB = new QueryDB();
        PreparedStatement preparedStatement = queryDB.getConnection().prepareStatement(
                "INSERT INTO nhankhau(manhankhau, hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, " +
                        "nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"
        );
        preparedStatement.setInt(1, maNhanKhau);
        preparedStatement.setString(2, hoVaTen);
        preparedStatement.setDate(3, Date.valueOf(ngaySinh));
        preparedStatement.setString(4, gioiTinh);
        preparedStatement.setString(5, cmnd_CCCD_);
        preparedStatement.setString(6, sDT);
        preparedStatement.setString(7, quocTich);
        preparedStatement.setString(8, tonGiao);
        preparedStatement.setString(9, nguyenQuan);
        preparedStatement.setInt(10, maHoKhau);
        preparedStatement.setBoolean(11, laChuHo);
        preparedStatement.setString(12, quanHeVoiChuHo);
        preparedStatement.setString(13, ngheNghiep);

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        queryDB.close();
        if(result == 1){
            RuntimeException thanhCongException = new RuntimeException("Thêm nhân khẩu thành công");
            Utility.displayExceptionDialog(thanhCongException);
            throw thanhCongException;
        } else{
//                RuntimeException thatBaiException = new RuntimeException("Thêm nhân khẩu thất bại");
//                Utility.displayExceptionDialog(thatBaiException);
//                throw thatBaiException;
        }
    }

    public static void updateNhanKhau(int maNhanKhau, String hoVaTen, String gioiTinh, LocalDate ngaySinh, String cmnd_CCCD_,
                                      String quocTich, String tonGiao, String sDT, String nguyenQuan, String ngheNghiep,
                                      int maHoKhau, Boolean laChuHo, String quanHeVoiChuHo) throws SQLException{
        QueryDB queryDB = new QueryDB();
        PreparedStatement preparedStatement = queryDB.getConnection().prepareStatement(
                "UPDATE nhankhau SET (hoten, ngaysinh, gioitinh, cmnd_cccd, sdt, quoctich, tongiao, " +
                        "nguyenquan, mahokhau, lachuho, quanhevoichuho, nghenghiep) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
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
        preparedStatement.setInt(13, maNhanKhau);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        queryDB.close();
        if(result == 1){
            RuntimeException thanhCongException = new RuntimeException("Cập nhật nhân khẩu thành công");
            Utility.displayExceptionDialog(thanhCongException);
            throw thanhCongException;
        } else{
//                RuntimeException thatBaiException = new RuntimeException("Thêm nhân khẩu thất bại");
//                Utility.displayExceptionDialog(thatBaiException);
//                throw thatBaiException;
        }


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
}
