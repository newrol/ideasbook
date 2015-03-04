/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.Publication;
import com.iesdealquerias.dam.ideasbook.Text;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class CommentViewController implements Initializable {

    
    @FXML
    private ImageView iUserPhoto;
    
    @FXML
    private Label lName;
    
    @FXML 
    private Label lText;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void FillValues(Publication comment) throws IOException{
        iUserPhoto.setImage(new ImageTools().showImage(comment.getUser().getPhoto()));
        lText.setText(((Text)comment).getText());
        lName.setText(comment.getUser().getName());
    }
}
