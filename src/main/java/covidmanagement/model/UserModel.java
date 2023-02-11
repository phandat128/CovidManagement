package covidmanagement.model;

import covidmanagement.database.QueryDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static void validate(final String username, final String password) throws SQLException, RuntimeException {
        QueryDB queryDB = new QueryDB();
        ResultSet rs = queryDB.executeQuery("SELECT * FROM TaiKhoan WHERE username = '" + username + "';");
        if (!rs.next()) throw new SQLException("Tài khoản không tồn tại");
        String _password = rs.getString("password");
        if (!password.equals(_password)) throw new RuntimeException("Mật khẩu sai");
    }
}
