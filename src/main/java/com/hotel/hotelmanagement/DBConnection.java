package com.hotel.hotelmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {

        public static final String Host = "jdbc:mysql://localhost:3306/";
        public static final String DB_Name = "hotel_management";
        public static final String USERNAME = "root";
        public static final String PASSWORD = "";
        private static Connection connect = null;

        static {
            try {
                connect = DriverManager.getConnection(Host + DB_Name, USERNAME, PASSWORD);
                System.out.println("Success connection");
            } catch (SQLException e) {
                System.out.println("Driver Manager Error " + e);
            }
        }

        public static Connection getConnection() {
            return connect;
        }

}
