/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelaunch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author gaby & SY
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private void openParametre(ActionEvent event)
    {
        
        try {
            FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("Parametre.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public ChoiceBox<String> choicebox;
    
    @FXML
    private Label label;
    @FXML
    private Button buttonDirectoryChooser;
    @FXML
    private ListView listeImages;
    
    @FXML
    private void openDirectory(ActionEvent event){
        
        ObservableList<String> imageVide =FXCollections.observableArrayList ();
        ObservableList<String> images =FXCollections.observableArrayList ();
        Stage dialogue=new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(dialogue);
        
        if(selectedDirectory == null){
           imageVide.add("AUCUN DOSSIER SELECTIONNE");
           listeImages.setItems(imageVide);
        }else{
            if (selectedDirectory.isDirectory()) { // make sure it's a directory
                for (final File f : selectedDirectory.listFiles(IMAGE_FILTER)) {
                    BufferedImage img = null;

                    //img = ImageIO.read(f);
                    images.add(f.getName());
                    // you probably want something more involved here
                    // to display in your UI
                    //System.out.println("image: " + f.getName());
                    //System.out.println(" width : " + img.getWidth());
                    //System.out.println(" height: " + img.getHeight());
                    //System.out.println(" size  : " + f.length());
                }
                listeImages.setItems(images);
                
            }
        }
    
    }
    static final String[] EXTENSIONS = new String[]{
        "gif", "png", "bmp" // and other formats you need
    };
    // filter to identify images based on their extensions
    static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       // choicebox.getItems().add("Français(France)");
       // choicebox.getItems().add("Anglais(Grande Bretagne)");
       // choicebox.getItems().add("Arabe()");
        //choicebox.setValue("Français(France)");
    }    
    
}
