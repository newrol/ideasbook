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
    private Stage stage;
    private User user;
    private FXMLLoader loginLoader;
    private FXMLLoader homeLoader;
    private Parent login;
    private Parent home;
    private Scene sceneLogin;
    private Scene sceneHome;
    private LoginController loginController;
    private HomeController homeController;
    
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
        
        loginLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Login.fxml")); 
        homeLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Home.fxml"));
//        
//        login = (Parent)loginLoader.load();
//        loginController = loginLoader.<LoginController>getController();
//        loginController.setMainController(this);
//        
// //       loggin = FXMLLoader.load(getClass().getResource("/edu/hernandezvicente/daniel/view/Login.fxml"));
//        home = FXMLLoader.load(getClass().getResource("/edu/hernandezvicente/daniel/view/UserMainPage.fxml"));       
        showLogin();
        //Create a new scene to view that in screen:
        //Scene scene = new Scene(root,tamanio.width,tamanio.height);
        //stage.setMaximized(true);
//        
//        
//        sceneLogin = new Scene(login);
//        sceneHome = new Scene(home);    
//        DoubleProperty sceneWidth = new SimpleDoubleProperty();
//        sceneWidth.bind(sceneLogin.widthProperty());
//        sceneWidth.bind(sceneHome.widthProperty());
//        //bind xml file to over scene:
//        stage.setScene(sceneLogin);
        //show the scene:
       
    //    stage.show();
    }
    
        public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void showHome() throws IOException{
        login = null;
        home = (Parent)homeLoader.load();
        homeController = homeLoader.<HomeController>getController();
        homeController.setMainController(this);
        homeController.setUser(user);
        sceneHome = new Scene(home);
        stage.setScene(sceneHome);
        stage.show();
    }
    
    public void showLogin() throws IOException{
        home = null;
        login = (Parent)loginLoader.load();
        loginController = loginLoader.<LoginController>getController();
        loginController.setMainController(this);
        sceneLogin = new Scene(login);
        stage.setScene(sceneLogin);
        stage.show();
    }
}
