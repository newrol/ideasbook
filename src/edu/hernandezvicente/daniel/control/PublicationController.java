/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.Text;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.PublicationCatalog;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * 
 * @author Daniel 
 */
public class PublicationController implements Initializable {
    private User user;
    private HomeController HomeController;
    
    @FXML
    private TextArea tPublicationText;
    @FXML
    private ImageView iPublicationImage;
    @FXML
    private Button bPublicate;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    public void create() throws IOException{
        PublicationCatalog publicationCatalog = new PublicationCatalog();
        Text publication = new Text();
        publication.setId((long) 2);
        publication.setUser(user);
        publication.setText(tPublicationText.getText());
        
        BufferedImage bImage = SwingFXUtils.fromFXImage(iPublicationImage.getImage(), null);
        ByteArrayOutputStream s = new ByteArrayOutputStream();
        ImageIO.write(bImage, "png", s);
        byte[] res  = s.toByteArray();
        
        publication.setPhoto(res);        
        publicationCatalog.addPublication(publication);
        HomeController.refresh();        
    }    
    
    //User get an set values and homeController:
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
    public HomeController getHomeController() {return HomeController;}
    public void setHomeController(HomeController HomeController) {this.HomeController = HomeController;}    
}
