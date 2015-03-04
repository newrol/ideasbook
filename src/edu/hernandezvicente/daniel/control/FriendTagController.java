/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class FriendTagController implements Initializable {
    private User owner;
    private User user;
    private HomeController homeController;

    
    @FXML
    private ImageView vPhoto;
    
    @FXML
    private Label lName;
    
    @FXML
    private Label lMail;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void fillValues() throws IOException{
        vPhoto.setImage(new ImageTools().showImage(user.getPhoto()));
        lName.setText(user.getName());
        lMail.setText(user.getEmail());
    }
    
    public void showUser() throws IOException{
        homeController.friendWall(user);
    }

    public void validateFriendshipRequest(){
        
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public HomeController getHomeController() {
        return homeController;
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }    
    
}
