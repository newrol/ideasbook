/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.hernandezvicente.daniel.reports;

import java.io.File;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;


/**
 *
 * @author juanvi
 */
public class JasperTestFX extends Application {
    private final VBox root = new VBox();
    private final HBox up   = new HBox();
    private final ProgressBar bar = new ProgressBar();
    private final Button btn = new Button();
    private final Label info = new Label();
    
    @Override
    public void start(Stage primaryStage) {
        btn.setText("Show");
        btn.setOnAction((e) -> loadReport());
        
        up.getChildren().add(btn);
        root.getChildren().add(up);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Jasper Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    private void loadReport() {
        setLoadingUI();        
        startLoadTask();
    }
    
    private void setLoadingUI() {
        info.setText("Cargando...");
        info.setStyle("-fx-text-fill: blue;");
        up.getChildren().removeAll(bar, info);
        up.getChildren().addAll(bar, info);
        btn.setDisable(true); 
    }
    
    private void startLoadTask() {
        Task task = createTask();
        new Thread(task).start();
    }
    
    private Task createTask() {
        return new Task<Void>() {
            File file;
            
            @Override protected Void call()  {
                try {
                    //file = File.createTempFile("report", ".html");
                    file = new File("\\src\\edu\\hernandezvicente\\daniel\\reports\\UserFr.html");
                    
                    // Crea el fichero HTML
                    JasperTestLoader jtl = new JasperTestLoader();
                    jtl.load();
                    
                    JasperPrint jasperPrint = jtl.getJasperPrint();
                    JasperExportManager.exportReportToHtmlFile(jasperPrint, file.getPath());
                } catch (Exception ex) {
                    Logger.getLogger(JasperTestFX.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    return null;
                }
            }
            
            @Override protected void cancelled() {
                System.out.println("cancelled");
            }
            
            @Override protected void succeeded() {
                try {
                    // Quita la etiqueta de "Cargando..."
                    up.getChildren().remove(info);
                    succeededOrFailed();
                    // Muestra el fichero html
                    WebView view = new WebView();
                    view.getEngine().load(file.toURI().toURL().toExternalForm());
                    root.getChildren().removeIf((o) -> o instanceof WebView);
                    root.getChildren().add(view);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(JasperTestFX.class.getName()).log(Level.SEVERE, null, ex);
                    failed();
                }
            }
            
            @Override protected void failed() {
                info.setStyle("-fx-text-fill: red;");
                info.setText("failed");
                succeededOrFailed();
            }
            
            private void succeededOrFailed() {
                up.getChildren().remove(bar);
                btn.setDisable(false);
            }
        };        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
