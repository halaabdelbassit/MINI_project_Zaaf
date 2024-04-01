package com.hotel.hotelmanagement;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashBoardController implements Initializable {
    @FXML
    private Label avaRoom;

    @FXML
    private Label bookedRoom;

    @FXML
    private Label earning;

    @FXML
    private Label pending;

    @FXML
    private Label totalRoom;

    private final Connection connection;

    private final DBConnection dbConnection;

    private PreparedStatement pstmt;
     public DashBoardController() {
         dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
     }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String query = "SELECT COUNT(roomNumber) AS totalRooms, \n" +
                "  (SELECT COUNT(roomNumber) FROM rooms WHERE status = 'Not Booked') AS totalNotBooked,\n" +
                "  (SELECT COUNT(roomNumber) FROM rooms WHERE status = 'Booked') AS totalBooked\n" +
                "FROM rooms";
        String query2 = "SELECT SUM(b.billAmount) AS totalEarnings, (SELECT SUM((r.price * DATEDIFF(res.checkOutDate, res.checkInDate))) AS Pending FROM reservations res \n" +
                "INNER JOIN rooms r ON r.roomNumber = res.roomNumber \n" +
                "WHERE res.status = 'Checked In') AS totalPendings FROM bills b \n" +
                "INNER JOIN reservations res ON res.reservationID = b.reservationID \n" ;
        try {
            pstmt = connection.prepareStatement(query);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                totalRoom.setText(res.getString("totalRooms"));
                bookedRoom.setText(res.getString("totalBooked"));
                avaRoom.setText(res.getString("totalNotBooked"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            pstmt = connection.prepareStatement(query2);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                earning.setText(rs.getString("totalEarnings"));
                pending.setText(rs.getString("totalPendings"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
