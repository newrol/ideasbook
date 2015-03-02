/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hernandezvicente.daniel.view;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import static com.athaydes.automaton.assertion.AutomatonMatcher.hasText;
import edu.hernandezvicente.daniel.control.MainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Daniel 
 */
public class LogginButtonTest {
    FXer fxer;
    FXApp fxapp;
    
    @Before
    public void setup() throws Exception {
        
        FXApp.startApp(new MainController());
        fxer = FXer.getUserWith( FXApp.getScene().getRoot() ); 
    }
   
    @Test
    public void testFeature() {
        fxer.clickOn("bResgister");
//
//        assertThat(fxer.getAt("my-tf"), hasText("Hello World!"));
    }

}
