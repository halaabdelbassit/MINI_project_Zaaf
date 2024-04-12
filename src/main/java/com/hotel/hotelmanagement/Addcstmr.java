package com.hotel.hotelmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    private PreparedStatement pst;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public void handleSubmitAction(ActionEvent actionEvent) {
        String name = cName.getText();
        String email = cEmail.getText();
        String gender = cGender.getText();
        String nationality = cNationality.getText();
        String number = cNumber.getText();
        String phone = cPhone.getText();
    }
}
