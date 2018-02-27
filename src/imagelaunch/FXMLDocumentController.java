/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelaunch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import static javafx.scene.paint.Color.WHITE;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author gaby & SY
 */
public class FXMLDocumentController implements Initializable {

    
    @FXML
    private Label output;
    private ResourceBundle bundle;
    private Locale locale ;
    private static String lang;
    private ListView listeImagesCurrent;
    @FXML
    private Text rechercheLbl;
    @FXML
    private RadioButton motcle_rech;
    @FXML
    private RadioButton  nom_rech;
    @FXML
    private Label  img_nom;
    @FXML
    private Label  motcle_img;
    @FXML
    private Label  type_img;
    @FXML
    private Label  taille_img;
    @FXML
    private Button buttonDirectoryChooser;
    @FXML
    private Button lancerRech;
    @FXML
    private Button save_keyw;
    @FXML
    private Button setting;
    @FXML
    private Label  folderMessage;
    @FXML
    private Button FRAction;
    @FXML
    private Button ENAction;
    @FXML
    private Button ARAction;
    @FXML
    public ChoiceBox<String> choicebox;
    
    File selectedDirectory=null;
    @FXML
    private Label label;
    @FXML
    private ListView listeImages;
    @FXML
    private TextField nomImage;
    @FXML
    private TextField motCle;
    @FXML
    private Label typeImg;
    @FXML
    private Label tailleImg;
    @FXML
    private ImageView imageSelected;
    @FXML
    private HBox ImageEditOption;
    @FXML
    private ImageView imageDiapoId;
    @FXML
    private TextField champSearch;
    
    private static String repertoire;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         setLang("fr");
    }    
    
    
  
    @FXML
    private void openParametre(ActionEvent event){
        
        try {
            FXMLLoader fxmlLoader =  new FXMLLoader(getClass().getResource("Parametres.fxml"));
            Stage stage = new Stage();
            Parent root1 = (Parent)fxmlLoader.load();
            stage.setScene(new Scene(root1));
            //Parametres ctrlparam = fxmlLoader.<Parametres>getController();
            stage.resizableProperty().set(false);
            stage.show();
            
            stage.setOnHiding((WindowEvent event1) -> {
            loadLang(Parametres.getLangParam());
            
            if(Parametres.getLangParam().equals("fr"))
            {
            
            setLang("fr");
             FRAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
             ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
             ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
            }
            else if(Parametres.getLangParam().equals("en"))
            {
            
             setLang("en"); 
             ENAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
             FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
             ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
            }
              else if(Parametres.getLangParam().equals("ar"))
            {
            setLang("ar");
            ARAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
            ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
            FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
            } 
            else
              {
                  
              }

            
           });
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void btnFR(ActionEvent event) {
        
        setLang("fr");
        loadLang("fr");
        
        FRAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
        ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
        ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
    }
    @FXML
    private void btnEN(ActionEvent event) {
        setLang("en");
        loadLang("en");
        
        ENAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
        FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
        ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");

    }
    @FXML
    private void btnAR(ActionEvent event) {
       setLang("ar");
       loadLang("ar");
       
        ARAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
        ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
        FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
    }
    private void loadLang(String lang){
        locale= new Locale(lang);
        bundle = ResourceBundle.getBundle("language.lang", locale);
        buttonDirectoryChooser.setText(bundle.getString("dossier_eng"));
        rechercheLbl.setText(bundle.getString("rech_lbl"));
        nom_rech.setText(bundle.getString("nom_lbl"));
        motcle_rech.setText(bundle.getString("motcle_lbl"));
        img_nom.setText(bundle.getString("nom_lbl"));
        motcle_img.setText(bundle.getString("motcle_lbl"));
        type_img.setText(bundle.getString("type_lbl"));
        taille_img.setText(bundle.getString("taille_lbl"));
        save_keyw.setText(bundle.getString("motcle_eng"));
        setting.setText(bundle.getString("param_eng"));
        folderMessage.setText(bundle.getString("folder_mess"));
        lancerRech.setText(bundle.getString("go_eng"));
        
        //Parametres par = new Parametres();
        //par.setParametresLanguage();
                
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
                imageSelected.setImage(null);
                repertoire = selectedDirectory.getAbsolutePath();
                for (final File f : selectedDirectory.listFiles(IMAGE_FILTER)) {
                    images.add(f.getName());
                    
                }
                
                listeImages.setItems(images);
                listeImagesCurrent=new ListView(images);
                listeImages.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        try {
                            if(newValue!=null){
                                File f=new File(selectedDirectory.getAbsolutePath()+"/"+newValue.toString());
                                BufferedImage img = ImageIO.read(f);
                                imageSelected.setImage(new Image(new FileInputStream(selectedDirectory.getAbsolutePath()+"/"+f.getName()),388,406,false,false));
                                nomImage.setText(f.getName());
                                typeImg.setText(getExtension(f));
                                tailleImg.setText(getFormatedSize(f));
                                ImageEditOption.setVisible(true);
                                motCle.setText(getCle(selectedDirectory.getAbsolutePath()+"/"+f.getName()));
                            }
                            else{
                                ImageEditOption.setVisible(false);
                                imageSelected.setImage(null);
                            }
                            
                            
                            
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
            Diaporama ctrldiapo=new Diaporama(selectedDirectory, listeImages);
            loader.setController(ctrldiapo);
            Stage stage=new Stage();
            Parent root = (Parent)loader.load();
            stage.setScene(new Scene(root));
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
    
    @FXML
    private void saveKey(ActionEvent event){
        if(!nomImage.equals("")&& !motCle.equals("")){
            try{
                ObjectMapper mapper = new ObjectMapper();
                JSONObject obj = mapper.readValue(new File("src/imagelaunch/motscles.json"), JSONObject.class);
                Object s=obj.get(motCle.getText());
                if(s==null){
                    String nom=(String) (selectedDirectory+"/"+nomImage.getText());
                    String key=getCle(nom);
                    ArrayList list = new ArrayList();
                    if(key!=null){
                        Object orem=obj.get(key);
                        obj.remove(key);
                        s=removePic((ArrayList) orem,nom);
                        if(!((ArrayList)s).isEmpty()){
                            obj.put(key, s);
                        }
                        
                    }
                    list.add((selectedDirectory+"/"+nomImage.getText()));
                    obj.put(motCle.getText(), list);
                }
                else{
                    String nom=(selectedDirectory+"/"+nomImage.getText());
                    String key=getCle((String) nom);
                    if(key!=null){
                        if(!key.equals(motCle.getText())){
                            Object orem=obj.get(key);
                            obj.remove(key);
                            orem=removePic((ArrayList) orem,nom);
                            if(!((ArrayList)orem).isEmpty()){
                                obj.put(key, orem);
                            }
                            ArrayList list = new ArrayList();
                            list=(ArrayList) s;
                            list.add((selectedDirectory+"/"+nomImage.getText()));
                            obj.put(motCle.getText(), list);
                        }
                    }
                    else{
                        ArrayList list = new ArrayList();
                        list=(ArrayList) s;
                        list.add((selectedDirectory+"/"+nomImage.getText()));
                        obj.put(motCle.getText(), list);
                    }
                }
                
                
                FileWriter file = new FileWriter("src/imagelaunch/motscles.json");
                file.write(obj.toJSONString());
                file.flush();
                
            } 
            catch(IOException e){}
            
            
            
        }
            
            
            
    }
        
    @FXML
    private void recherche(ActionEvent event){
        if(champSearch!=null){
            try {
                System.out.println(champSearch.getText());
                ObjectMapper mapper = new ObjectMapper();
                JSONObject obj = mapper.readValue(new File("src/imagelaunch/motscles.json"), JSONObject.class);
                Object s=obj.get(champSearch.getText());
                if(s!=null){
                    ObservableList<String> images =FXCollections.observableArrayList ();
                    for (final Object f : (ArrayList) s) {
                        images.add(f.toString().substring(selectedDirectory.getAbsolutePath().length()+1));
                    
                    }
                    
                    //images.setAll((ArrayList) s);
                    
                
                listeImages.setItems(images);
                imageSelected.setImage(null);
                ImageEditOption.setVisible(false);
                }
                
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @FXML
    private void champSearchListen(KeyEvent event){
        if(champSearch.getText().equals("")){
            listeImages.setItems(listeImagesCurrent.getItems());
            imageSelected.setImage(null);
            ImageEditOption.setVisible(false);
        }
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

    public  void setLang(String lang) {
        this.lang = lang;
    }

    public static  String getLang() {
        return lang;
    }
    
    public static String getVALUE() {

        return  repertoire;
    }
    
    public static String getCle(String s) {
        String resultat=null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JSONObject obj = mapper.readValue(new File("src/imagelaunch/motscles.json"), JSONObject.class);
          
            Object o=obj.keySet();
            
            for(Object i : obj.keySet().toArray()){
                for(int j=0;j<((ArrayList)obj.get(i)).size();j++){
                    ArrayList t=(ArrayList)obj.get(i);
                    if(t.get(j).equals(s)){
                        resultat=i.toString();
                        break;
                    }
                    
                }
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  resultat;
    }
    
    public static ArrayList removePic(ArrayList l,String s) {
        ArrayList li=null;
        for(int i=0;i<l.size();i++){
            
            if(l.get(i).equals(s)){
                li=l;
                li.remove(i);
                System.out.println("resultat remove function :"+li);
                break;
            }
                    
        }
        return  li;
    }
    
    
    
}
