/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelaunch;

import java.io.File;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author sy & gaby
 */
public class Parametres implements Initializable {
    
    private File selectedDirectory;
    
     @FXML private TextField repertory;
     
     @FXML private Button Buttonok;
     
     @FXML private Button ButtonAnnuler;
     
     @FXML public Label  optionsAction;
     @FXML public Label  labelType;
     
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
       
       //Initialisation de la fenetre parmetres
        initDefaultValues();
        
       
    }

    public Parametres() {
         
    }

    public void setRepertory(File selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }

    private void initDefaultValues() {
        
      
       //Récupérer le répertoire sélectionné pour les photos affichés depuis le controller principal
       String s   =  FXMLDocumentController.getVALUE();
     
       //Initilaiser répertoire courant
       repertory.setText(s);
      
       //Initialiser la langue 
       Parametres.lang_ =  FXMLDocumentController.getLang();
       String types="";
       for(String type :FXMLDocumentController.EXTENSIONS){
           types+=type+" ; ";
       }
       labelType.setText(types.substring(0,types.length()-2));
       
        //Définir la langue qui sera séléctionné par défaut par le choicebutton
        switch (lang_) {
            case "fr":
                lg="Français";
                break;
            case "en":
                lg="English";
                break;
            default:
                lg="العربية";
                break;
        }   
     
       
       //Loading de la lagngue de la fenetre principale
       loadLang(lang_);
        
       //Initialiser les langues
       choiceButton.getItems().addAll("Français", "English", "العربية");
       
       //Définition de la langue sléctionné à l'ouverture de la fenetre parametre
       choiceButton.getSelectionModel().select(lg);
       
    }
    //  Méthode pour l'internalisation de la fenetre parametres
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
    
    // Button OK de validation
    @FXML
    private void okButton(ActionEvent event){
        
        //Créer un message d'information 
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
          
        if(choiceButton.getSelectionModel().getSelectedItem()!=selectedlang_){
            String valuelang =  (String) choiceButton.getSelectionModel().getSelectedItem();



            switch(valuelang) 
            {
                case  "Français":
                      //System.out.println("FR");
                      loadLang("fr");
                      languest_="fr";
                      lg="Français";
                      alert.setTitle("Information");
                      alert.setHeaderText("Vous avez choisi les paramètres de langue par défaut");
                      alert.setContentText("Elles prennent effet dès maintenant!");
                      break;
                 case  "English":
                      //System.out.println("EN"); 
                     loadLang("en");
                     languest_="en";
                     lg="English";
                     alert.setTitle("Information");
                     alert.setContentText("Your changement take effect right now");
                     break; 
                 case  "العربية":
                     // System.out.println("AR");
                     loadLang("ar");
                     languest_="ar";
                     choiceButton.getSelectionModel().select("العربية");
                     lg="العربية";
                     alert.setTitle("معلومات");
                     alert.setContentText("إلس بريننت إفيت ديس مينتينانت!");

                     break; 
            }

               
        }
        alert.showAndWait();
        
        //Récupérer et fermer la fenetre courante
        Stage stage = (Stage) Buttonok.getScene().getWindow();
 
        stage.hide();
  
    }
    //Button Annuler 
     @FXML
    private void annulerButton(ActionEvent event){
       
    String valuelang =  (String) choiceButton.getSelectionModel().getSelectedItem();
    
    
    //FXMLDocumentController.setLang(valuelang);
    
    String langue = null;
    
    if(lg.equals(valuelang)==true)
    {
                
           switch(valuelang) 
               {
                case  "Français":
                      //System.out.println("FR");
                      langue="fr";
                      break;
                 case  "English":
                      //System.out.println("EN");
                     langue="en";
                    
                     break; 
                 case  "العربية":
                     // System.out.println("AR");
                     langue="ar";
                    
                     break; 
                }
    //Définition de la langue en vigueur avant la fermeture de la fenêtre
      Parametres.languest_ =  langue;
    }

    
   
  
    
    
     //Récupérer et fermer la fenetre courante
    Stage stage = (Stage) ButtonAnnuler.getScene().getWindow();
    
    stage.close();
    }

 //Getteur de la langue en vigueur
 public static String getLangParam() {
        return languest_;
    }
  
    
    
    
}
