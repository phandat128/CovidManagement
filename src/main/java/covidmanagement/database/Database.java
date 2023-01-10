package covidmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
        private static final String DB_URL = "jdbc:postgresql://localhost:5432/";
        private static final String USERNAME = "postgres";
        private static final String PASSWORD = "123123";

        public static Connection ConnectDB() {
            try {
//                Class.forName("org.postgresql.Driver");
//                DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
//                System.out.println("Connected!");
//                return null;
                System.out.println("Connecting...");
                Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Connected!");
                return connection;
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }

        }

        public static void closeDB(Connection conn) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
