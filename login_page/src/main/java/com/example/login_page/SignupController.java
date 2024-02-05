package com.example.login_page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Queue;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    @FXML
        private Button button_SignUp;
    @FXML
        private Button button_login;
    @FXML
        private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
        private RadioButton rb_One;
    @FXML
        private RadioButton rb_Two;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup toggleGroup=new ToggleGroup();
        rb_One.setToggleGroup(toggleGroup);
        rb_Two.setToggleGroup(toggleGroup);
        rb_One.setSelected(true);
        button_SignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String togglename=((RadioButton)toggleGroup.getSelectedToggle()).getText();
                if(!tf_username.getText().trim().isEmpty()&& !tf_password.getText().trim().isEmpty()){
                    database.signupUser(event,tf_username.getText(),tf_password.getText(),togglename);
                }
                else
                {
                    System.out.println("Please fill all Info");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all Info to Sign up!");
                    alert.show();
                }
            }
        });
        button_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.changeScene(event,"Sample.fxml","Log in!",null,null);
            }
        });
    }
}