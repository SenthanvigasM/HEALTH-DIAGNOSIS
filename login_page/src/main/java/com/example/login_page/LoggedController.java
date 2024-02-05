package com.example.login_page;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedController implements Initializable {
    @FXML
    private Button button_logout;
    @FXML
    private Label label_welcome;
    @FXML
    private Label label_membership;
    @FXML
    private Button button_Enter;
    @FXML
    private RadioButton rb_dengue;
    @FXML
    private RadioButton rb_diab;
    @FXML
    private RadioButton rb_Tube;
    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        ToggleGroup tog = new ToggleGroup();
        rb_diab.setToggleGroup(tog);
        rb_dengue.setToggleGroup(tog);
        rb_Tube.setToggleGroup(tog);
        rb_dengue.setSelected(true);
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.changeScene(event,"Sample.fxml","Log in!", null,null);
            }
        });
        button_Enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String togglename=((RadioButton)tog.getSelectedToggle()).getText();
                scenecheck.dis(event,"checker.fxml","check in",togglename);
            }
        });
    }



    public void setUserInformation(String username,String membership){
        label_welcome.setText("Welcome to DVS\n"+username+"!");
        String mem="Years";
        if(membership.equals("One"))
        {
            mem="Year";
        }
        label_membership.setText("Your Membership year is "+membership+" "+mem);
    }
}

