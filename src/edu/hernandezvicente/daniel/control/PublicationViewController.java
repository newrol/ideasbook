/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.Publication;
import com.iesdealquerias.dam.ideasbook.Text;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PublicationViewController implements Initializable {
    private Publication publication;
    
    @FXML
    private TextArea tText;
    
    @FXML 
    private ImageView iImage;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
    
    public void fillPublication(){
        byte []publiPhoto = ((Text) publication).getPhoto();
        iImage.setImage(new Image(new ByteArrayInputStream(publiPhoto)));
        tText.setText(((Text) publication).getText());
    }
}
