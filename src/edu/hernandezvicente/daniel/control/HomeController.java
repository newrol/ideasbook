/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.UserCatalog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class HomeController implements Initializable {
    private UserCatalog userCatalog;
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
       userCatalog = new UserCatalog();
    }    

    public void na() throws IOException{
        
        //test create publication:
        FXMLLoader publicationLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Publication.fxml"));
        Parent publication;
        PublicationController publicationController;
            publication = (Parent)publicationLoader.load();
            publicationController = publicationLoader.<PublicationController>getController();        
            publicationController.setUser(user);
            pMainPane.add(publication, 0, 0);
       
       //Test show last Publications:
        FXMLLoader publicationViewLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/PublicationView.fxml"));
        Parent publicationView;
        PublicationViewController publicationViewController;
        publicationView = (Parent)publicationViewLoader.load();
        publicationViewController = publicationViewLoader.<PublicationViewController>getController();        
        publicationViewController.setPublication(user.getPublicationList().get(0));
        publicationViewController.fillPublication();
        pMainPane.add(publicationView, 0, 2);
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
    
    public void Refresh() throws IOException{
        refreshuserImage();
        refreshUserData();
        na();
        userCatalog.getFriends(user);
    }
    
    public void refreshuserImage(){
       byte []userPhoto = user.getPhoto();        
       
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
