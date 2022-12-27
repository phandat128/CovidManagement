package covidmanagement.database;

import java.sql.*;

public class QueryDB {
    Connection connection;
    Statement statement;
    String URL = "jdbc:postgresql://localhost:5432/";
    String user = "postgres";
    String password = "123456";

    public QueryDB() throws SQLException{
        connection = DriverManager.getConnection(URL, user, password);
        System.out.println("Connected!");
        statement = connection.createStatement();
    }

    public ResultSet query(String sql) throws SQLException{
        return this.statement.executeQuery(sql);
    }

    public void close() throws SQLException{
        this.statement.close();
        this.connection.close();
    }
}
