package com.hotel.hotelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Addcstmr {

    @FXML
    public TextField cName;

    @FXML
    public TextField cPhone;
    @FXML
    public TextField cNationality;
    @FXML
    public TextField cNumber;
    @FXML
    public TextField cEmail;
    @FXML
    public TextField cGender;
    @FXML
    public Button submit;

    private Connection connection;

    private DBConnection dbConnection;



    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = DBConnection.getConnection();
    }

    public void handleSubmitAction(ActionEvent actionEvent) {
        String name = cName.getText();
        String email = cEmail.getText();
        String gender = cGender.getText();
        String nationality = cNationality.getText();
        String numberStr = cNumber.getText();
        int number = Integer.parseInt(numberStr);
        String phone = cPhone.getText();

        PreparedStatement pst =null;
        try {
            pst = connection.prepareStatement("INSERT INTO customers(ctmrIDNumber, ctmrName" +
                    ",ctmrNationality,ctmrGender,ctmrPhone,ctmrEmail) VALUES(?,?,?,?,?,?)");
            pst.setInt(1, number);
            pst.setString(2, name);
            pst.setString(3, email);
            pst.setString(4, gender);
            pst.setString(5, nationality);
            pst.setString(6, phone);

            pst.executeUpdate();


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (pst != null)
                    pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
