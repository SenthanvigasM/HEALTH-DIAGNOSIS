package com.example.login_page;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
public class database {
    public static void changeScene(ActionEvent event ,String fxmlfile,String title,String username,String membership){
        Parent root=null;
        if(username !=null&&membership!=null)
        {
            try
            {
                FXMLLoader loader=new FXMLLoader(database.class.getResource(fxmlfile));
                root=loader.load();
                LoggedController loggedController=loader.getController();
                loggedController.setUserInformation(username,membership);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else {
            try{
                root=FXMLLoader.load(database.class.getResource(fxmlfile));
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void signupUser(ActionEvent event,String username,String password,String membership)
    {
        Connection connection =null;
        PreparedStatement psInsert =null;
        PreparedStatement psCheckExist=null;
        ResultSet resultSet=null;
        try
        {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/login-schema","root","Msenthan0307");
            psCheckExist= connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            psCheckExist.setString(1,username);

            resultSet=psCheckExist.executeQuery();
            if(resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("you entered existed username.");
                alert.show();
            }
            else{
                psInsert=connection.prepareStatement("INSERT  INTO users (username,password,membership) VALUES (?, ? , ?)");
                psInsert.setString(1,username);
                psInsert.setString(2,password);
                psInsert.setString(3,membership);
                psInsert.executeUpdate();
                changeScene(event,"Logged-in.fxml","Welcome!",username,membership);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            if(resultSet!=null)
            {
                try
                {
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(psCheckExist !=null){
                try
                {
                    psCheckExist.close();
                }catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(psInsert!=null)
            {
                try
                {
                    psInsert.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                try
                {
                    connection.close();
                }
                catch(SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void loginUser(ActionEvent event,String username,String password){
        Connection connection =null;
        PreparedStatement preparedstatement =null;
        ResultSet resultSet=null;
        try
        {
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/login-schema","root","Msenthan0307");
            preparedstatement =connection.prepareStatement("SELECT password ,membership, username FROM users WHERE username = ?");
            preparedstatement.setString(1,username);
            resultSet = preparedstatement.executeQuery();
            if(!resultSet.isBeforeFirst()){
                System.out.println("User not found in here");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Provided credentials are incorrect.");
                alert.show();
            }else {
                while(resultSet.next()){

                    String s =resultSet.getString("username");
                    System.out.println(s+" "+username);
                    String gotpassword =resultSet.getString("password");
                    String gotmembership=resultSet.getString("membership");
                    if(s.equals(username)) {
                        if (gotpassword.equals(password)) {
                            changeScene(event, "logged-in.fxml", "Welcome to DVS", username, gotmembership);//if you want to change the text edit this
                        } else {
                            System.out.println("Password did not match");
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            if (password.isEmpty())
                                alert.setContentText("Please Enter Your Password");
                            else
                                alert.setContentText("Wrong Password");
                            alert.show();
                        }
                    }else {
                        System.out.println("Password did not match");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        if (password.isEmpty())
                            alert.setContentText("Please Enter Your Password");
                        else
                            alert.setContentText("Username is not found");
                        alert.show();
                    }
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally{
            if(resultSet!=null)
            {
                try{
                    resultSet.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(preparedstatement!=null)
            {
                try
                {
                    preparedstatement.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try
                {
                    connection.close();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }

}