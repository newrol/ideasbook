/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.Friendship;
import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.FriendShipCatalog;
import edu.hernandezvicente.daniel.persistance.model.UserCatalog;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class UserInfoController implements Initializable {
    private User Owner, friend;
    private List<Friendship> friendships;
    private FriendShipCatalog friendShipCatalog;
    private boolean isfriend;
    private GridPane gp;
    List<FXMLLoader> publicationsviewLoader;
    List<Parent> publicationsView;
    List<PublicationViewController> publicationsViewController;
    
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
        friendShipCatalog = new FriendShipCatalog();
    }    

   public void fill() throws IOException{
       isfriend();
       lName.setText(friend.getName());
       Vphoto.setImage(new ImageTools().showImage(friend.getPhoto()));
       if(isfriend){
           lMail.setText(friend.getEmail());
           fillWall();
           bFriendship.setText("Eliminar amigo");
        }
       else{
           bFriendship.setText("Añadir amigo");
       }
    } 
    
   private void fillWall() throws IOException{
       publicationsviewLoader = new ArrayList<>();
       publicationsView = new ArrayList<>(); 
       publicationsViewController = new ArrayList<>();
        
       for(int i = 0; i < friend.getPublicationList().size(); i++){

            publicationsviewLoader.add(new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/PublicationView.fxml")));
            publicationsView.add((Parent)publicationsviewLoader.get(i).load());
            publicationsViewController.add(publicationsviewLoader.get(i).<PublicationViewController>getController());
            publicationsViewController.get(i).setPublication(friend.getPublicationList().get(i));
            publicationsViewController.get(i).fillPublication();
            gp.addRow(i+1, publicationsView.get(i));
        }
    }
    
   /**
    * Test if two users are friends
    */
    private void isfriend(){
        try{
            friendships = friendShipCatalog.getusersFriendShip(Owner, friend);
            if(friendships != null)
                if(friendships.size() == 2)
                    isfriend = true;
        }
        catch(javax.persistence.NoResultException e){
            isfriend = false;
        }
    }
    
    public void friendshipButtonAction(){
        System.out.println("dentro");
        if(!isfriend)
            friendShipCatalog.addFriend(Owner, friend);
        else{
            friendShipCatalog.deleteFriend(Owner, friend);
        }
    }                 
    
    //Get And set user values:
    public User getOwner() {return Owner;}
    public void setOwner(User Owner) {this.Owner = Owner;}
    public User getFriend() {return friend;}
    public void setFriend(User friend) {this.friend = friend;}
    public GridPane getGp() {return gp;}
    public void setGp(GridPane gp) {this.gp = gp;}
}
