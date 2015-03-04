/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.Publication;
import com.iesdealquerias.dam.ideasbook.Text;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class PublicationViewController implements Initializable {
    private Publication publication;
    private ImageTools imagetools;
    
    @FXML
    private Label tText;
    
    @FXML 
    private ImageView iImage;
    
    @FXML
    private ScrollPane sp;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imagetools = new ImageTools();
    }    
    
    
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
    
    public void fillPublication(Publication publication) throws IOException{
        GridPane pane = new GridPane();
        int counter = 0;
        
        for(int i = 0; i < publication.getPublicationList().size(); i++){
            FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/CommentView.fxml"));
            Parent coment;
            CommentViewController commentViewController;
            coment = (Parent)commentLoader.load();
            commentViewController = commentLoader.<CommentViewController>getController();        
            sp.setContent(pane);
            pane.add(coment, i, 0);
            counter ++;
        }
        
        FXMLLoader commentLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Comment.fxml"));
        Parent coment;
        CommentController commentController;
        coment = (Parent)commentLoader.load();
        commentController = commentLoader.<CommentController>getController();        
        sp.setContent(pane);
        pane.add(coment, counter, 0);
        iImage.setImage(imagetools.showImage(((Text) publication).getPhoto()));
        tText.setText(((Text) publication).getText());
        
        
    }
    
    
}
