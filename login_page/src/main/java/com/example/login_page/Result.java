package com.example.login_page;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Result implements Initializable {
    @FXML
    private Button button_logout;

    @FXML
    private Label result;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        button_logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                database.changeScene(event,"Sample.fxml","Log in!",null,null);
            }
        });
    }

    public void disease(String s, int a, int b) {
        int c = b-a;
        String m,n,o=null;
        m="Your Symptoms for the disease "+s+" is ";
        if (c==8) {

        }
        else if(c<3){
            result.setText(m+"\nvery High\n Immediately You want to take checkup \nAnd meet your Doctor");
        }
        else if(c<6) {
            result.setText(m+"\nSlightly High\n Go and take checkup in nearby Hospital");
        }
        else
        {
            result.setText(m+ "\nlow\nFor more info you can consult Your Doctor");
        }
    }
    public static void changingScene(ActionEvent event,String name,int a,int b)
    {
        Parent root = null;
        if (name != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Checker.class.getResource("result.fxml"));
                root = loader.load();
                Result checker = loader.getController();
                checker.disease(name, a,b);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(database.class.getResource("result.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Result!!");
        stage.setScene(new Scene(root));
        stage.show();
    }

}

