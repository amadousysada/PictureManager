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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 *
 * @author gaby
 */
public class Parametres implements Initializable {
    
    private File selectedDirectory;
    
     @FXML
    public TextField repertory;
     

  
  
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

   
    
    
    
    
}
