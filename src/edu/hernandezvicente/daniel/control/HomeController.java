/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.User;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class HomeController implements Initializable {
    private User user;
    private MainController mainController;
    
    @FXML
    private ImageView userImage;
    @FXML
    private Label lUserName;
    @FXML
    private Label lUserName1;
    
    @FXML 
    private GridPane pMainPane;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        FXMLLoader publicationLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Publication.fxml"));
        Parent publication;
        PublicationController publicationController;
        try {
            na();
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }    

    public void na() throws IOException{
        
        FXMLLoader publicationLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Publication.fxml"));
        Parent publication;
        PublicationController publicationController;
            publication = (Parent)publicationLoader.load();
            publicationController = publicationLoader.<PublicationController>getController();        
            
        pMainPane.add(publication, 0, 0);
    }   
    
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void exit() throws IOException{
        mainController.showLogin();
    }
    
    public void Refresh(){
        refreshuserImage();
        refreshUserData();
    }
    
    public void refreshuserImage(){
       byte []userPhoto = user.getPhoto();
       ImageIcon imageIcon;        
       
       if(userPhoto.length < 15)
            userImage.setImage(new Image(HomeController.class.getResourceAsStream(
                                    "/edu/hernandezvicente/daniel/images/ProfileImageTest.jpg")));
       else{
           userImage.setImage(new Image(new ByteArrayInputStream(userPhoto)));
       }              
    }
    
    public void refreshUserData(){
        String userName = user.getName();
        lUserName.setText(userName);
        lUserName1.setText(userName);
    }
    
    public void ChangeImage(){
        
    }
}
