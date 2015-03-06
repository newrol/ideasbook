/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.control;


import com.iesdealquerias.dam.ideasbook.User;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.help.CSH;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.help.HelpSetException;
import javax.swing.JFrame;
import jfx.messagebox.MessageBox;

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
    private HelpBroker helpbroker;
    
    private JFrame trucoVentanaVar;
    private JFrame trucoVentanaLogin;
    private JFrame trucoVentanaHome;

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

        trucoVentanaVar = new JFrame();
        trucoVentanaHome = new JFrame();
        trucoVentanaLogin = new JFrame();
        addHelpButton();
        try{
        showLogin(); //Run loggin when the app is launched.
        }
        catch(Exception e){
            MessageBox.show(stage,
                                    "No se Ha podido conectar Con la base de datos",
                                    "Mensaje de error", 
                                    MessageBox.ICON_ERROR);
        }
    }
    
    /**
     * View a new home Screen
     * @throws IOException 
     */
    public void showHome(User user) throws IOException, MalformedURLException, HelpSetException{
        trucoVentanaVar = trucoVentanaHome;
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
    public void showLogin() throws IOException, MalformedURLException, HelpSetException{
        trucoVentanaVar = trucoVentanaLogin;
         // kill useless reference to liberate memory in case that it exist.
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
    
    public void addHelpButton() throws MalformedURLException, HelpSetException{ 
        //File archivo = new File("javahelp/help_set.hs");
        File archivo = new File("MyJavaHelp/HelpSet.hs");
        URL hsURL = archivo.toURI().toURL();
        //Leemos el HelpSet de Configuración
        HelpSet helpset = new HelpSet(getClass().getClassLoader(),hsURL);
        helpbroker = helpset.createHelpBroker();
        // Trucamos el borker para que al pasarle un determinado frame (ficticio)
        // equivalente al que estamos viendo, nos muestre su ayuda
        // helpbroker.enableHelp(trucoVentanaVar, sceneName, helpset);
        
        helpbroker.enableHelp(trucoVentanaHome, "home", helpset);
        helpbroker.enableHelp(trucoVentanaLogin, "login", helpset);
        
        
         stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.F1)
                    new CSH.DisplayHelpFromSource( helpbroker ).actionPerformed(new java.awt.event.ActionEvent(trucoVentanaVar, 1, "help"));
                
            }
        });
    }    
}
