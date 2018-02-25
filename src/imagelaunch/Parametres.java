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
import java.util.Locale;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author sy&gaby
 */
public class Parametres implements Initializable {
    
    private File selectedDirectory;
    
     @FXML private TextField repertory;
     
     @FXML private Button Buttonok;
     
     @FXML private Button ButtonAnnuler;
     
     @FXML public Label  optionsAction;
     
     @FXML public Label  dossier_lbl;
      
     @FXML public Label  format_lbl;
     
     @FXML public Tab mytab_general;
     
     @FXML public Tab mytablang;
 
     
     @FXML private ChoiceBox choiceButton;
     private ResourceBundle bundle;
     private Locale locale ;
     
     private static String lang_;
     
     private  String selectedlang_;
     
      //Initilaiser la langue par défaut à fr
      private static String languest_="fr";
 
      private String lg;
  
  
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
     
       //Initilaiser répertoire
       repertory.setText(s);
      // System.out.println("La langue par défaut est "+lang_);
      
      //selectedlang_ =  (String) choiceButton.getSelectionModel().getSelectedItem();
      
       //Initialiser la langue 
       this.lang_ =  FXMLDocumentController.getLang();
       
       if(lang_.equals("fr"))
       {
           lg="Français";
       }
       else if(lang_.equals("en"))
       {
           lg="English"; 
       }   
       else
       {
            lg="العربية";
       }   
     
       
       //System.out.println("langue par défaut :"+lang_);
       
       loadLang(lang_);
        
       //Initialiser les langues
       choiceButton.getItems().addAll("Français", "English", "العربية");
       choiceButton.getSelectionModel().select(lg);
       
    }
       private void loadLang(String lang)
    {
        locale= new Locale(lang);
        bundle = ResourceBundle.getBundle("language.lang", locale);
        
        optionsAction.setText(bundle.getString("option_lbl"));
        
        dossier_lbl.setText(bundle.getString("dossier_lbl"));
        
        format_lbl.setText(bundle.getString("format_lbl"));
        
        mytab_general.setText(bundle.getString("general_en"));
        
        mytablang.setText(bundle.getString("langue_en"));
        
        Buttonok.setText(bundle.getString("ok_en"));
        
        ButtonAnnuler.setText(bundle.getString("annuler_en"));
                
        }
    
    @FXML
    private void okButton(ActionEvent event){
          
    if(choiceButton.getSelectionModel().getSelectedItem()!=selectedlang_)
    {
        String valuelang =  (String) choiceButton.getSelectionModel().getSelectedItem();
        

         
        switch(valuelang) 
        {
            case  "Français":
                  //System.out.println("FR");
                  loadLang("fr");
                  languest_="fr";
                  lg="Français";
                  break;
             case  "English":
                  //System.out.println("EN"); 
                 loadLang("en");
                 languest_="en";
                 //FXMLDocumentController c = new FXMLDocumentController("");
                 lg="English";
                 break; 
             case  "العربية":
                 // System.out.println("AR");
                 loadLang("ar");
                 languest_="ar";
                 choiceButton.getSelectionModel().select("العربية");
                 lg="العربية";
                 break; 
        }
               
    }
 
      Stage stage = (Stage) Buttonok.getScene().getWindow();
 
      stage.hide();
  
    }
    
     @FXML
    private void annulerButton(ActionEvent event){
       
    Stage stage = (Stage) ButtonAnnuler.getScene().getWindow();
 
    stage.close();
    }

    
 public static String getLangParam(String lang) {
        return languest_;
    }
  
    
    
    
}
