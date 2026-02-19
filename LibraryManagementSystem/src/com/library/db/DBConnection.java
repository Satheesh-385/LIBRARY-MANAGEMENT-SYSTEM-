package com.library.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static Connection con;

    public static Connection getConnection() {

        try {
            if (con == null) {
                Class.forName("oracle.jdbc.driver.OracleDriver");

                con = DriverManager.getConnection(
                        "jdbc:oracle:thin:@localhost:1521/ORCLPDB",
                        "library_user",
                        "library123"
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }
}
