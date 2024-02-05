package com.example.login_page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Checker implements Initializable{
    @FXML
    private Button button_logout;
    @FXML
    private Button button_check;
    @FXML
    private Button button_back;
    @FXML
    private CheckBox cb_1;
    @FXML
    private CheckBox cb_2;
    @FXML
    private CheckBox cb_3;
    @FXML
    private CheckBox cb_4;
    @FXML
    private CheckBox cb_5;
    @FXML
    private CheckBox cb_6;
    @FXML
    private CheckBox cb_7;
    @FXML
    private CheckBox cb_8;
    @FXML
    private Label tb_0;
    @FXML
    private Label tb_1;
    @FXML
    private Label tb_2;
    @FXML
    private Label tb_3;
    @FXML
    private Label tb_4;
    @FXML
    private Label tb_5;
    @FXML
    private Label tb_6;
    @FXML
    private Label tb_7;
    @FXML
    private Label tb_8;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.changeScene(event,"Sample.fxml","Log in!",null,null);
            }
        });
        button_check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int b=8;
                int a=0;
                if(cb_1.isSelected()){a++;}
                if(cb_2.isSelected()){a++;}
                if(cb_3.isSelected()){a++;}
                if(cb_4.isSelected()){a++;}
                if(cb_5.isSelected()){a++;}
                if(cb_6.isSelected()){a++;}
                if(cb_7.isSelected()){a++;}
                if(cb_8.isSelected()){a++;}
                if((b-a)==b){
                    System.out.println("Invalid choice");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Select at least One Symptom in the following given Choice");
                    alert.show();
                }
                else
                    Result.changingScene(event,tb_0.getText(),a,b);
            }
        });
        button_back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.changeScene(event,"Logged-in.fxml","Result!",null,null);
            }
        });
    }

    public void dischoose(ActionEvent event, String[] s, String name) {
        tb_0.setText(name);
        tb_1.setText(s[0]);
        tb_2.setText(s[1]);
        tb_3.setText(s[2]);
        tb_4.setText(s[3]);
        tb_5.setText(s[4]);
        tb_6.setText(s[5]);
        tb_7.setText(s[6]);
        tb_8.setText(s[7]);
    }
}
