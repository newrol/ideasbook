/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.view;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import com.athaydes.automaton.Speed;
import static com.athaydes.automaton.assertion.AutomatonMatcher.hasText;
import static com.athaydes.automaton.assertion.AutomatonMatcher.hasValue;
import static com.athaydes.automaton.assertion.AutomatonMatcher.selected;
import static com.athaydes.automaton.assertion.AutomatonMatcher.visible;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.Timing;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Daniel 
 */
public class LoginButtonTest {
    FXer fxer;
    FXApp fxapp;
    Parent login ;
    
    @Before
    public void setup() throws Exception {
        
        //FXApp.startApp(new MainController());
     //   fxer = FXer.getUserWith( FXApp.getScene().getRoot());
        //        FXApp.startApp( new MainController() );
//        fxer = FXer.getUserWith( FXApp.getScene().getRoot() );

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
        
           org.junit.Assert.assertTrue(((Button)fxer.getAt("bjoin")).isDisabled());
            fxer.clickOn("tLogginUser").type("user");
            fxer.clickOn("tLogginPassword").type("password");
            org.junit.Assert.assertTrue(!((Button)fxer.getAt("bjoin")).isDisabled()); 

//
//        assertThat(fxer.getAt("my-tf"), hasText("Hello World!"));
    }
}
