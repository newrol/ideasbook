/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.UserCatalog;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class UserAdminPaneController implements Initializable {
    private User user;
    private UserCatalog userCatalog;
    private HomeController homeController;
    
    @FXML
    private TextField tName;
    
    @FXML
    private TextField tMail;
    
    @FXML
    private PasswordField tPassword;
    
    @FXML
    private ImageView vPhoto;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userCatalog = new UserCatalog();
    }    

    public void changeValues() throws IOException{
        user.setEmail(tMail.getText());
        user.setPassword(tPassword.getText());
        //user.setPhoto();
        userCatalog.modifyUser(user);
        homeController.refresh();
    }
    
    public void FillValues(){
        tName.setText(user.getName());
        tMail.setText(user.getEmail());
        //vPhoto.setImage(user.getPhoto());
        
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
