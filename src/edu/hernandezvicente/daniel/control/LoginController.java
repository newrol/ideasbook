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
import java.net.MalformedURLException;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javax.help.HelpSetException;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

/**
 * FXML Controller class
 *Control Login FXML 
 * @author Daniel
 */
public class LoginController implements Initializable {
    private MainController mainController;
    // peristance user object declaration:
    private UserCatalog userCatalog;
    // Class to manage validation:
    private  ValidationSupport validationSupport  =  new ValidationSupport();
    // test if the component is empty;
    private final Validator v1 = Validator.createEmptyValidator("");
    // Labe diccionary - control. join a mistake label: 
    // a specific control:
    private HashMap<Control, Label> map = new HashMap();
    
    
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
    private Button bRegister;          //register button.
    
    @FXML
    private Button bJoin;               //go to application button.
    
    @FXML
    private Label mistakelbl;
    
    @FXML
    private MenuItem mHelp;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userCatalog = new UserCatalog(); // 
        fillAgeCombos();            //Fill up all comboBox
        
        createValidation(tName, mistakelbl, v1);
        createValidation(tMail, mistakelbl, v1);
        createValidation(tMail2, mistakelbl, v1);
        createValidation(tPassword, mistakelbl, v1);
        
    }
    
    
    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    

    public void setValidationValues(){
        
        //Añade validators a los textfield:
        createValidation(tName, mistakelbl, v1);
        createValidation(tMail, mistakelbl, v1);
        createValidation(tMail2, mistakelbl, v1);
        createValidation(tPassword, mistakelbl, v1);
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
        System.out.println("asdfdsaf");
    }
    
    
    public void validateUser() throws IOException, MalformedURLException, HelpSetException{
        User user = new User();
        user.setName(tLogginUser.getText());
        user.setPassword(tLogginPassword.getText());
        
        
        
        try{
            user = userCatalog.validateUser(user);
            mainController.showHome(user);
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

    /**
     * Añade el validador al textfield. Este método es el que lleva el código
     * para añadir el validador de controlsfx y escuchar a los cambios de 
     * de validación. Si sólo hay un campo que validad, bastaría con realizar
     * toda la gestión en el listener. Sin embargo, si hay varios campos que 
     * validar y queremos tener etiquitas con errores por cada campo, hay que
     * complicar un poco el programa. Esa complicación se resuelve en el método
     * displayErrors
     * @see #displayErrors
     * @param textField Textfield a validar
     * @param lbl Etiqueta que muestra el error (de haberlo)
     * @autor juavi
     */
    private void createValidation(TextField tName, Label mistakelbl, Validator v1) {
        // Añade el validador al textfield
        validationSupport.registerValidator(tName, v1);   
        // Cuando cambia el resultado de la validación, se llama al método errors
        validationSupport.validationResultProperty().addListener( 
            (o, oldValue, validationResult) -> displayErrors(validationResult)
        );
        // Asocia el control a la etiqueta de error
        map.put(tName, mistakelbl);
    }

    
    /**
     * Este método recorre la lista de errores para actualizar las etiquetas de
     * error. Al inicio habilita o deshabilita el botón, dependiendo de si hay
     * errores.
     * @param validationResult Lista con los errores de validación
     * @autor juavi
     */
    private void displayErrors(ValidationResult validationResult) {
        // Borra todas las etiquetas de error
        map.entrySet().stream().forEach((lbl)-> lbl.getValue().setText(""));
        // Habilita o deshabilita el botón si no hay o hay errores respectivamente
        bRegister.setDisable(!validationResult.getErrors().isEmpty());
        // Muestra los errores de cada control en su etiqueta correspondiente
        validationResult.getErrors().stream().forEach((err) -> setErrorLabel(err));
        // Muestra los warnings de cada control en su etiqueta correspondiente
        validationResult.getWarnings().stream().forEach((err) -> setErrorLabel(err));
    }

        
    /**
     * Este método identifica qué control generó el error de validación y 
     * muestra el mensaje correspondiente en su etiqueta de error
     * @param err Mensaje de error de validación
     * @autor juavi
     */
    private void setErrorLabel(ValidationMessage err) {
        // Obtiene la etiqueta correspondiente al error
        Label errLbl = getErrorLabelFor(err.getTarget());
        // Establece el color del mensaje de error
        errLbl.setTextFill(getColor(err.getSeverity()));
        // Establece el mensaje de error
        errLbl.setText(err.getText());
    }

        /**
     * Este método devuelve la etiqueta de error asociada al control
     * @param source Control que genera el error
     * @return Etiqueta de error asociada al control
     * @autor juavi
     */
    private Label getErrorLabelFor(Control target) {
            return map.get(target);
    }
    
    /**
     * Devuelve el color del mensaje (rojo / amarillo) depenediendo de la severidad
     * @param s Severidad del mensaje
     * @return color del mensaje (rojo / amarillo) depenediendo de la severidad
     * @autor juavi
     */
    private Color getColor(Severity s) {
        return ( s == Severity.WARNING ) ? Color.DARKGOLDENROD : Color.RED;
    }
}   

  