/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class UserInfoController implements Initializable {
    private User Owner, friend;
    
    @FXML
    private ImageView Vphoto;
    
    @FXML
    private Label lName;
    
    @FXML
    private Label lMail;
    
    @FXML 
    private Button bFriendship;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    //Get And set user values:
    public User getOwner() {return Owner;}
    public void setOwner(User Owner) {this.Owner = Owner;}
    public User getFriend() {return friend;}
    public void setFriend(User friend) {this.friend = friend;} 
}
