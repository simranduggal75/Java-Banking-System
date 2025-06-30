package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDB {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3308/bank", "root", "root"
            );
            System.out.println("✅ Connected to MySQL!");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

