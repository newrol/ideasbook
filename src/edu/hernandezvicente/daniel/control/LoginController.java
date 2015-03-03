/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.control;

import com.iesdealquerias.dam.ideasbook.User;
import edu.hernandezvicente.daniel.persistance.model.UserCatalog;
import edu.hernandezvicente.daniel.tools.ImageTools;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class LoginController implements Initializable {
    private MainController mainController;
    // peristance user object declaration:
    private UserCatalog userCatalog;
    
    //fxml switch object declaration between registration file and this controller.
    @FXML
    private TextField tLogginUser;            //userName Text field;
    @FXML
    private TextField tLogginPassword;        //password text field;  
    
    
    //fxml switch object declaration between registration file and this controller.

    @FXML
    private PasswordField tPassword;    // password text    
    @FXML
    private TextField tName;            //name text field.
    @FXML
    private TextField tMail;            //mail text field.
    @FXML
    private TextField tMail2;           //mail2 text field.
    @FXML
    private ComboBox cDay;              //born day combo box.
    @FXML
    private ComboBox cMonth;            //born month combo box.
    @FXML
    private ComboBox cYear;             //born year combo box.
    
    @FXML 
    private Button bResgister;          //register button.
    
    @FXML
    private Button bJoin;               //go to application button.
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userCatalog = new UserCatalog(); // 
        fillAgeCombos();            //Fill up all comboBox
        
    }
    
    
    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    

    /**
     * Method to registrate a new user.
     */
    public void registerUser() throws IOException{
        Image defaultImage = new Image(HomeController.class.getResourceAsStream(
                                    "/edu/hernandezvicente/daniel/images/ProfileImageTest.jpg"));
        
        User user = new User();
        user.setName(tName.getText());
        user.setPassword(tPassword.getText());
        user.setEmail(tMail.getText());
        user.setAge(LocalDateTime.now().getYear() - (int) cYear.getValue());
        user.setPhoto(new ImageTools().wrapImage(defaultImage));
        user.setSince(new Date());
        userCatalog.CreateUser(user);
    }
    
    
    public void validateUser() throws IOException{
        User user = new User();
        user.setName(tLogginUser.getText());
        user.setPassword(tLogginPassword.getText());
        
        
        try{
            user = userCatalog.validateUser(user);
            mainController.showUserWall(user);
        }
        catch(javax.persistence.NoResultException e){
            System.out.println("no existe el usuario");
        }
    }
           
    /**
     * Private method to fill cDay, cMonth, cYear with the correct values:
     */
    private void fillAgeCombos(){
        ObservableList<Integer> options;            //Class observableList permits me add an arraylist to valuelist.
        List<Integer> days = new ArrayList<>();
        List<Integer> months = new ArrayList<>();
        List<Integer> years = new ArrayList<>();
       
        //Fill Up the diferent arrays:
        for(int i = 1; i < 31; i++)
            days.add(i);
        
        for(int i = 1; i < 12; i++)
            months.add(i);
        
        int actualYear = LocalDateTime.now().getYear();
        
        for(int i = 0; i < 100; i++){
            years.add(actualYear);
            actualYear--;
        }    
        
        //Add Generates values to their comboBox:
        options = FXCollections.observableArrayList(days);
        cDay.setItems(options);
        options = FXCollections.observableArrayList(months);
        cMonth.setItems(options);
        options = FXCollections.observableArrayList(years);
        cYear.setItems(options);
    }
    
    /**
     * Control if the loggin button is enable or disabled
     */
    public void testEnableLogin(){
        if(tLogginUser.getText().equals("") && tPassword.getText().equals("")){
            bJoin.setDisable(true);
        }
        else
            bJoin.setDisable(false);
    }
}

  