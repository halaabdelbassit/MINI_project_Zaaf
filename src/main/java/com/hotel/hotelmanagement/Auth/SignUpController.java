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
import java.sql.*;
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
    DBConnection dbConnection;
    Connection connection;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        connection = DBConnection.getConnection();

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
        if (name_text.isEmpty() || username_text.isEmpty() || password_text.isEmpty()
               || address_text.isEmpty()) {
            OptionPane("Every Field is required", "Error Message");
        } else {
            Connection connection = DBConnection.getConnection();
            CallableStatement pst = null;
            try {
              pst = connection.prepareCall("{? = call signup(?,?,?,?)}");
              pst.registerOutParameter(1, Types.VARCHAR);
              pst.setString(2,name_text);
              pst.setString(3,username_text);
              pst.setString(4,password_text);
              pst.setString(5,address_text);
              pst.execute();

              String massage = pst.getString(1);
                OptionPane(massage, "Message");
            } catch (SQLException e) {
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
