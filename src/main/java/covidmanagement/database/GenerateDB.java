package covidmanagement.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GenerateDB {
    public static void main(String[] args) {
        String URL = "jdbc:postgresql://localhost:5432/";
        String user = "postgres";
        String password = "truyen123";
        StringBuilder builder = new StringBuilder();

        try {
            File file = new File("src/main/java/covidmanagement/database/generate.sql");
            FileInputStream fis = new FileInputStream(file);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            int content;
            while((content = buffer.read()) != -1){
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
