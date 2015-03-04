/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.FriendShipCatalog;
import edu.hernandezvicente.daniel.persistance.model.UserCatalog;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class HomeController implements Initializable {
    private UserCatalog userCatalog;
    private FriendShipCatalog friendShipCatalog;
    private User user;
    private MainController mainController;
    
    @FXML
    private ImageView userImage;
    @FXML
    private Label lUserName;
  
   @FXML
   private ScrollPane sc;
    
   @FXML
   private TextField tSearchUser;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       userCatalog = new UserCatalog();
       friendShipCatalog = new FriendShipCatalog();
    }    

    public void wall() throws IOException{
        GridPane pane = new GridPane();
        sc.setContent(pane);
        
        //test create publication:
        FXMLLoader publicationLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Publication.fxml"));
        Parent publication;
        PublicationController publicationController;
        publication = (Parent)publicationLoader.load();
        publicationController = publicationLoader.<PublicationController>getController();        
        publicationController.setUser(user);
        publicationController.setHomeController(this);
        pane.add(publication, 0, 0);

       //Test show last Publications:
//        FXMLLoader publicationViewLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/PublicationView.fxml"));
//        Parent publicationView;
//        PublicationViewController publicationViewController;
//        publicationView = (Parent)publicationViewLoader.load();
//        publicationViewController = publicationViewLoader.<PublicationViewController>getController();        
//        publicationViewController.setPublication(user.getPublicationList().get(0));
//        publicationViewController.fillPublication();
//        pMainPane.add(publicationView, 0, 1);
//       
        //Show various publications:
        List<FXMLLoader> publicationsviewLoader = new ArrayList<>();
        List<Parent> publicationsView = new ArrayList<>();
        List<PublicationViewController> publicationsViewController = new ArrayList<>();
        
        for(int i = 0; i < user.getPublicationList().size(); i++){
            publicationsviewLoader.add(new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/PublicationView.fxml")));
            publicationsView.add((Parent)publicationsviewLoader.get(i).load());
            publicationsViewController.add(publicationsviewLoader.get(i).<PublicationViewController>getController());
            publicationsViewController.get(i).setPublication(user.getPublicationList().get(i));
            publicationsViewController.get(i).fillPublication();
            pane.addRow(i+1, publicationsView.get(i));
        }        
    }   
    
    /**
     * Fill up friendWall if the friend is found. If user is not found I´ll shown a message
     * @param user
     * @throws IOException 
     */
    private void friendWall(User friend) throws IOException{
        GridPane pane = new GridPane();
        sc.setContent(pane);                      
        FXMLLoader publicationLoader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/UserInfo.fxml"));
        Parent UserInfo;
        UserInfoController userInfoController;
        UserInfo = (Parent)publicationLoader.load();
        userInfoController = publicationLoader.<UserInfoController>getController();        
        userInfoController.setOwner(user);
        userInfoController.setFriend(friend);
        userInfoController.setGp(pane);
        userInfoController.fill();
        pane.add(UserInfo, 0, 0);
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    
    public void exit() throws IOException{
        mainController.showLogin();
    }
    
    public void refresh() throws IOException{
        refreshUserData();
        wall();
        friendShipCatalog.getFriends(user);
    }
    
    /**
     * Refresh user values
     */
    public void refreshUserData() throws IOException{
        String userName = user.getName();
        lUserName.setText(userName);
        userImage.setImage(new ImageTools().showImage(user.getPhoto()));
    }
    
    
   /**
    * search text search ser and if is found I´ll be shown.
    * @throws IOException 
    * 
    */ 
   public void searchFriend() throws IOException{
       User friend;
       try{
            friend = userCatalog.searchUserByName(tSearchUser.getText());
            friendWall(friend);
        }
        catch(javax.persistence.NoResultException e){
            GridPane pane = new GridPane();
            sc.setContent(pane);
            pane.add(new Label("User not found"), 0, 0);
        }
    }
   
   public void panelUserValues() throws IOException{
        GridPane pane = new GridPane();
        sc.setContent(pane); 
        FXMLLoader userAdminPaneLodader = new FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/UserAdminPane.fxml"));
        Parent userAdminPane;
        UserAdminPaneController userAdminPaneController;
        userAdminPane = (Parent)userAdminPaneLodader.load();
        userAdminPaneController = userAdminPaneLodader.<UserAdminPaneController>getController();        
        userAdminPaneController.setUser(user);
        userAdminPaneController.setHomeController(this);
        userAdminPaneController.FillValues();
        pane.add(userAdminPane, 0, 0);
    }
}
