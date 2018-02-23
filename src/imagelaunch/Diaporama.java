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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author sy
 */
public class Diaporama implements Initializable {
    
    private File selectedDirectory;
    private boolean exit=false;
    private ListView listeImages;
    @FXML
    private ImageView imageDiapoId;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
         //To change body of generated methods, choose Tools | Templates.
         initDiaporama();
          System.out.println("Hello");
    }

    public Diaporama() {
    }

    public ListView getListeImages() {
        return listeImages;
    }

    public File getSelectedDirectory() {
        return selectedDirectory;
    }

    public void setListeImages(ListView listeImages) {
        this.listeImages = listeImages;
    }

    public void setSelectedDirectory(File selectedDirectory) {
        this.selectedDirectory = selectedDirectory;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
    
    private void initDiaporama(){
        long delay = 2000;// mis a jour de la photo chaque 2 secondes;
            
            new Timer().schedule(new TimerTask() {
                int count=0;
                @Override
                public void run() {
                    try {
                        String s=listeImages.getItems().get(count++).toString();
                        System.out.println(selectedDirectory.getAbsolutePath()+"/"+s);
                        imageDiapoId.setImage(new Image(new FileInputStream(selectedDirectory.getAbsolutePath()+"/"+s),388,406,false,false));
                        if (count >= listeImages.getItems().size()) {
                            //Thread.currentThread().interrupt();
                            this.cancel();
                        }
                        if (exit ) {
                            this.cancel();
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, 0, delay);
    }
    
    
    
    
}
