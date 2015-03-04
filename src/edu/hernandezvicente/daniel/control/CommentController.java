/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.Publication;
import com.iesdealquerias.dam.ideasbook.Text;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.PublicationCatalog;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class CommentController implements Initializable {
    private PublicationCatalog publicationCatalog;
    private User user;
    private Publication Publication;
    private PublicationViewController publicationController;
    
    @FXML
    private ImageView iUserImage;
    
    @FXML
    private Label lUsername;
    
    @FXML
    private TextArea tComment;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        publicationCatalog = new PublicationCatalog();
    }    
 
    public void fillValues() throws IOException{
        iUserImage.setImage(new ImageTools().showImage(user.getPhoto()));
        lUsername.setText(user.getName());
    }
    
    public void addComment() throws IOException{
        Publication comment = new Text();
        comment.setUser(user);
        ((Text) comment).setText(tComment.getText());
        
        publicationCatalog.addPublication(comment);
        publicationCatalog.addCommentToPublication(Publication, comment);
      //  publicationController.fillPublication(Publication);
        
    }
    
    public Publication getPublication() {
        return Publication;
    }

    public void setPublication(Publication Publication) {
        this.Publication = Publication;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PublicationViewController getPublicationController() {
        return publicationController;
    }

    public void setPublicationController(PublicationViewController publicationController) {
        this.publicationController = publicationController;
    }
    
    
}
