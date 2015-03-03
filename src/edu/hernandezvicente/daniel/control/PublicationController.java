/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.Text;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.PublicationCatalog;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * 
 * @author Daniel 
 */
public class PublicationController implements Initializable {
    private User user;
    private HomeController HomeController;
    private ImageTools imageTools;
    private PublicationCatalog publicationCatalog;
    Text publication;
    
    @FXML
    private TextArea tPublicationText;
    @FXML
    private ImageView iPublicationImage;
    @FXML
    private Button bPublicate;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageTools = new ImageTools();
        publicationCatalog = new PublicationCatalog();
        publication = new Text();
    }
    
    public void create() throws IOException{
        publication.setId((long) user.getId());
        publication.setUser(user);
        publication.setText(tPublicationText.getText());
        publication.setPhoto(imageTools.wrapImage(iPublicationImage.getImage()));        
        publicationCatalog.addPublication(publication);
        HomeController.refresh();        
    }    
    
    public void changePhoto() throws IOException{
        iPublicationImage.setImage(imageTools.LoadImage());
    }
    
    //User get an set values and homeController:
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public HomeController getHomeController() {return HomeController;}
    public void setHomeController(HomeController HomeController) {this.HomeController = HomeController;}    
}
