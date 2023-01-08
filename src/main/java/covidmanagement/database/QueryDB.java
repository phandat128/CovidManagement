package covidmanagement.database;

import java.sql.*;

public class QueryDB {
    Connection connection;
    Statement statement;
    String URL = "jdbc:postgresql://localhost:5432/covidmanagement";
    String user = "postgres";
    String password = "123456";

    public QueryDB() throws SQLException{
        connection = DriverManager.getConnection(URL, user, password);
        System.out.println("Connected!");
        statement = connection.createStatement();
    }

    public ResultSet executeQuery(String sql) throws SQLException{
        return this.statement.executeQuery(sql);
    }

    public void executeUpdate(String sql) throws SQLException{
        this.statement.executeUpdate(sql);
    }

    public void close(){
        try {
            this.statement.close();
            this.connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
