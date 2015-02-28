/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.control;


import com.iesdealquerias.dam.ideasbook.User;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Daniel 
 */
public class MainController extends Application {
    private Stage stage;
    private User user;
    private Parent loggin;
    private Parent home;
    private Scene sceneLoggin;
    private Scene sceneHome;
    private LoginController loginController;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void main(String[] args) {
        //static method needly to run the app
        Application.launch(args);
        //Run the application
        new MainController();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        //charge in memroy xml view...
        //URL format must be as here to work:
        loggin = FXMLLoader.load(getClass().getResource("/edu/hernandezvicente/daniel/view/Login.fxml"));
        home = FXMLLoader.load(getClass().getResource("/edu/hernandezvicente/daniel/view/UserMainPage.fxml"));
        
        
        loginController = loggin.<LoginController>get

        //Debug the second page
        //Parent root = FXMLLoader.load(getClass().getResource("/edu/hernandezvicente/daniel/view/UserMainPage.fxml"));
        
        //Create a new scene to view that in screen:
        //Scene scene = new Scene(root,tamanio.width,tamanio.height);
        //stage.setMaximized(true);
        stage.setMaximized(false);
        
        Scene sceneLoggin = new Scene(loggin);
        Scene sceneHome = new Scene(home);    
        DoubleProperty sceneWidth = new SimpleDoubleProperty();
        sceneWidth.bind(sceneLoggin.widthProperty());
        //bind xml file to over scene:
        stage.setScene(sceneLoggin);
        //show the scene:
       
        stage.show();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    public void showHome(){
        stage.setScene(sceneHome);
    }
}
