package covidmanagement.database;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class generateDB {
    public static void main(String[] args) {
        String URL = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "123456";
        StringBuilder builder = new StringBuilder();

        try {
            File file = new File("src/main/java/covidmanagement/database/generate.sql");
            FileInputStream fis = new FileInputStream(file);
            int content;
            while((content = fis.read()) != -1){
                builder.append((char)content);
            }
            fis.close();
//            System.out.println(file.getAbsolutePath());
        } catch (Exception e){
            e.printStackTrace();
        }

        String sql = builder.toString();
        System.out.println(sql);

        try{
            System.out.println("Connecting...");
            Connection connection = DriverManager.getConnection(URL, user, password);
            System.out.println("Connected!");

            Statement statement = connection.createStatement();
            statement.execute(
                    "DROP DATABASE IF EXISTS covidmanagement;\n" +
                            "CREATE DATABASE covidmanagement;"
            );
            statement.close();
            connection.close();

            connection = DriverManager.getConnection(URL + "covidmanagement", user, password);
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
            connection.close();
            System.out.println("Done");
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
