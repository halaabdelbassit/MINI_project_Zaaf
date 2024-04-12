package com.hotel.hotelmanagement.Auth;

import com.hotel.hotelmanagement.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {
    @FXML
    private TextField address;

    @FXML
    private TextField answer;

    @FXML
    private RadioButton female;

    @FXML
    private RadioButton male;

    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<String> question;

    @FXML
    private Button signup;

    @FXML
    private TextField username;

    private Connection connection;

    private DBConnection dbConnection;

    private PreparedStatement pst;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
    }

    @FXML
    public void handleSignupAction(javafx.event.ActionEvent actionEvent) {
        String name_text = name.getText();
        String username_text = username.getText();
        String password_text = password.getText();
        String address_text = address.getText();

        assert name_text != null;
        assert username_text != null;
        assert password_text != null;

        assert address_text != null;
        if (name_text.isEmpty() || username_text.isEmpty() || password_text.isEmpty()  || address_text.isEmpty()) {
            OptionPane("Every Field is required", "Error Message");
        } else {
            String insert = "INSERT INTO users(name, username, password, address)"
                    + "VALUES (?, ?, ?, ?,)";
            connection = dbConnection.getConnection();
            try {
                pst = connection.prepareStatement(insert);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                pst.setString(1, name_text);
                pst.setString(2, username_text);
                pst.setString(3, password_text);
                pst.setString(4, address_text);
                pst.executeUpdate();
                OptionPane("Register Successfully", "Message");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void handleLoginButton(javafx.event.ActionEvent actionEvent) throws IOException {
        signup.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
    }

    private void OptionPane(String message, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
