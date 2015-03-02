/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.control;


import com.iesdealquerias.dam.ideasbook.User;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Daniel 
 */
public class MainController extends Application {
    Stage stage;
    private FXMLLoader loginLoader, homeLoader;     // FXML instances to control it later.         
    private Parent login, home;                     // Parent to control FXML views.
    private Scene sceneLogin, sceneHome;            // Diferent app scenes.
    private LoginController loginController;        //Login view Contoller.
    private HomeController homeController;          //Home view Controller.
    
    /**
     * Launch the app
     * @param args 
     */
    public static void main(String[] args) {
        //static method needly to run the app
        Application.launch(args);
        //Run the application
        new MainController();
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage; //Charge the stage to control views. 
        showLogin(); //Run loggin when the app is launched.
    }
    
    /**
     * View a new home Screen
     * @throws IOException 
     */
    public void showUserWall(User user) throws IOException{
       // login = null; // kill useless reference to liberate memory.
        //Charge fxml references in memory to acces them
        homeLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Home.fxml"));
        home = (Parent)homeLoader.load();
        homeController = homeLoader.<HomeController>getController(); //Set Login view Controller to add params.
        homeController.setMainController(this);
        homeController.setUser(user);
        homeController.refresh(); //refres interface to show user values.
        //Show steps:
        sceneHome = new Scene(home);
        stage.setScene(sceneHome);
        stage.show();
    }
    
    /**
     * View a new loggin screen
     * @throws IOException 
     */
    public void showLogin() throws IOException{
        home = null; // kill useless reference to liberate memory in case that it exist.
        //Charge fxml references in memory to acces them
        loginLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Login.fxml"));
        login = (Parent)loginLoader.load();
        loginController = loginLoader.<LoginController>getController(); //Set home view controller to add params.
        loginController.setMainController(this);
        //show steps
        sceneLogin = new Scene(login);
        stage.setScene(sceneLogin);
        stage.show();
    }
}
