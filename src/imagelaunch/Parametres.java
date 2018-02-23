/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelaunch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author gaby
 */
public class Parametres implements Initializable {
    
    private File selectedDirectory;
    
     @FXML private TextField repertory;
     
     @FXML private Button Buttonok;
     
     @FXML private Button ButtonAnnuler;
     
      
 

  
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
        initDefaultValues();
       
    }

    public Parametres() {
    }

    public void setRepertory(File selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }

    private void initDefaultValues() {
        
       String s   =  FXMLDocumentController.getVALUE();
     
       repertory.setText(s);
       
    }
    
    
    @FXML
    private void okButton(ActionEvent event){
          
    Stage stage = (Stage) ButtonAnnuler.getScene().getWindow();
 
    stage.close(); 
        
    }
    
     @FXML
    private void annulerButton(ActionEvent event){
       
    Stage stage = (Stage) Buttonok.getScene().getWindow();
 
    stage.close();
    }


   
    
    
    
    
}
