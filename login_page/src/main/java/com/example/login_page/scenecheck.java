package com.example.login_page;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class scenecheck {
    public static void scenechange(ActionEvent event, String fxmlfile, String title, String name,String []s) {
        Parent root = null;
        if (name != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Checker.class.getResource(fxmlfile));
                root = loader.load();
                Checker checker = loader.getController();
                checker.dischoose(event, s,name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(database.class.getResource(fxmlfile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void dis(ActionEvent event, String filename, String tit, String name) {
        Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login-schema", "root", "Msenthan0307");
            preparedstatement = connection.prepareStatement("SELECT * FROM disease WHERE name = ?");
            preparedstatement.setString(1, name);
            resultSet = preparedstatement.executeQuery();
            while (resultSet.next()) {
                String []ss=new String[8];
                for(int i=0;i<8;i++){
                    String m=("sym"+(i+1));
                    String s =resultSet.getString(m);
                    ss[i]=s;
                }
                scenechange(event, filename, tit,name,ss);
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedstatement != null) {
                try {
                    preparedstatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if ( connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}