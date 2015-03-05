/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.view;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import com.athaydes.automaton.Speed;
import edu.hernandezvicente.daniel.persistance.model.UserCatalog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Daniel 
 */
public class DBConntectionTest{
    FXer fxer;
    FXApp fxapp;
    Parent login ;
    
    @Before
    public void setup() throws Exception {
            final Stage stage = FXApp.initialize();
            System.out.println(getClass().getResource("/edu/hernandezvicente/daniel/view/Login.fxml"));
            final FXMLLoader loginLoader = new 
                                        FXMLLoader(getClass().getResource("/edu/hernandezvicente/daniel/view/Login.fxml"));
            login = (Parent)loginLoader.load();
            FXApp.doInFXThreadBlocking( new Runnable() {
                public void run() {
                    stage.getScene().setRoot( login );
                    stage.setWidth(1200);
                    stage.setMaximized(true);
                     fxer = FXer.getUserWith( FXApp.getScene().getRoot());
                }
            } );    
    }
   
    @Test
    public void testFeature() {
            fxer.clickOn("tName").type("user");
            fxer.clickOn("tMail1").type("user");
            fxer.clickOn("tMail2").type("user");
            fxer.clickOn("tPassword").type("user");
            fxer.clickOn("cDay").moveTo(700, 600, Speed.SLOW).click();
            fxer.clickOn("cMonth").moveTo(780, 600, Speed.SLOW).click();
            fxer.clickOn("cYear").moveTo(850, 600, Speed.SLOW).click();
            fxer.clickOn("bRegister");
    }
}
