/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelaunch;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;

/**
 *
 * @author gaby & SY
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    public ChoiceBox<String> choicebox;
    
    File selectedDirectory=null;
    @FXML
    private Label label;
    @FXML
    private Button buttonDirectoryChooser;
    @FXML
    private ListView listeImages;
    @FXML
    private TextField nomImage;
    @FXML
    private TextField motCle;
    @FXML
    private Label typeImg;
    @FXML
    private Label folderMessage;
    @FXML
    private Label tailleImg;
    @FXML
    private ImageView imageSelected;
    @FXML
    private HBox ImageEditOption;
    @FXML
    private ImageView imageDiapoId;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       // choicebox.getItems().add("Français(France)");
       // choicebox.getItems().add("Anglais(Grande Bretagne)");
       // choicebox.getItems().add("Arabe()");
        //choicebox.setValue("Français(France)");
    }    
    
    @FXML
    private void openParametre(ActionEvent event){
        
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
    private void openDirectory(ActionEvent event){
        
        ObservableList<String> imageVide =FXCollections.observableArrayList ();
        ObservableList<String> images =FXCollections.observableArrayList ();
        Stage dialogue=new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        selectedDirectory = directoryChooser.showDialog(dialogue);
        
        if(selectedDirectory == null){
           //
        }else{
            if (selectedDirectory.isDirectory()) { // make sure it's a directory
                folderMessage.setVisible(false);
                for (final File f : selectedDirectory.listFiles(IMAGE_FILTER)) {
                    BufferedImage img = null;
                    images.add(f.getName());
                    imageSelected.setImage(null);
                }
                
                listeImages.setItems(images);
                listeImages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        try {
                            if(newValue!=null){
                                File f=new File(selectedDirectory.getAbsolutePath()+"/"+newValue.toString());
                                BufferedImage img = ImageIO.read(f);
                                imageSelected.setImage(new Image(new FileInputStream(selectedDirectory.getAbsolutePath()+"/"+f.getName()),388,406,false,false));
                                nomImage.setText(f.getName());
                                motCle.setText("");
                                typeImg.setText(getExtension(f));
                                tailleImg.setText(getFormatedSize(f));
                                ImageEditOption.setVisible(true);
                            }
                            else{
                                ImageEditOption.setVisible(false);
                                imageSelected.setImage(null);
                            }
                            //System.out.println(selectedDirectory.getAbsolutePath()+"/"+f.getName());
                            
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                
            }
        }
    
    }
    
    @FXML
    private void diaporama(MouseEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Diaporama.fxml"));
            Stage stage=new Stage();
            Parent root = (Parent)loader.load();
            
            stage.setScene(new Scene(root));
            Diaporama ctrldiapo=loader.<Diaporama>getController();
            ctrldiapo.setSelectedDirectory(selectedDirectory);
            ctrldiapo.setListeImages(listeImages);  
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
//                        for( ScheduledExecutorService sched : activeExecutorServices ){
//                            sched.shutdown();
//                        }c
                        ctrldiapo.setExit(true);
                        
                    }});
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void forward(MouseEvent event){
        listeImages.getSelectionModel().selectNext();
    }
    @FXML
    private void previous(MouseEvent event){
        listeImages.getSelectionModel().selectPrevious();
    }
    
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp", "jpg"};
    
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
    public static String getExtension(File f) { 
        if(f != null) { 
            String filename = f.getName(); 
            int i = filename.lastIndexOf('.'); 
            if(i>0 && i<filename.length()-1) { 
                return filename.substring(i+1).toLowerCase(); 
            } 
        } 
        return null; 
    }
    
    public static String getFormatedSize(File f) {
        int size = (int) (f.length() / 1024) + 1;
        if (size > 1024) {
            return (size / 1024) + " Mo";
        } else {
            return size + " ko";
        }
    }
    
    
    
}
