/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelaunch;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
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
        String s="{}";
        File f=new File("motscles.json");
        try {
            f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(f));
            writer.write(s);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* appel de la fonction pour Initialisation des radio boutons de la recherche */
        initParametresAccueil();
         
    }    
    
    
    /* action associee au boutton setting en bas a droite de la fenetre principale */
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
            
                switch (Parametres.getLangParam()) {
                    case "fr":
                        setLang("fr");
                        FRAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
                        ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
                        ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
                        break;
                    case "en":
                        setLang("en");
                        ENAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
                        FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
                        ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
                        break;
                    case "ar":
                        setLang("ar");
                        ARAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
                        ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
                        FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
                        break;
                    default:
                        break;
                }

            
           });
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /* action associe au click du bouton FR pour la langue francaise */
    @FXML
    private void btnFR(ActionEvent event) {
        
        setLang("fr");
        loadLang("fr");
        
        FRAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
        ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
        ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
    }
    
    /* action associe au click du bouton EN pour la langue anglaise */
    @FXML
    private void btnEN(ActionEvent event) {
        setLang("en");
        loadLang("en");
        
        ENAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
        FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
        ARAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");

    }
    
    /* action associe au click du bouton AR pour la langue arabe */
    @FXML
    private void btnAR(ActionEvent event) {
       setLang("ar");
       loadLang("ar");
       
        ARAction.setStyle("-fx-background-color: #8C98A0;-fx-text-fill:#f0ebeb;");
        
        ENAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
        
        FRAction.setStyle("-fx-background-color: -fx-inner-border;-fx-text-fill:#000000;");
    }
    
    /* charger la langue active */
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
    
    
    /* fenetre pour choix du repertoire */
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
                listeImages.getSelectionModel().selectedItemProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
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
                });
                
                
            }
        }
    
    }
    /* lancement du diaporama */
    @FXML
    private void diaporama(MouseEvent event){
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Diaporama.fxml"));
            Diaporama ctrldiapo=new Diaporama(selectedDirectory, listeImages);
            loader.setController(ctrldiapo);
            Stage stage=new Stage();
            Parent root = (Parent)loader.load();
            stage.setScene(new Scene(root));
            stage.setOnCloseRequest((WindowEvent e) -> {
                    ctrldiapo.setExit(true);
            });
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /* basculer vers l'image suivante */
    @FXML
    private void forward(MouseEvent event){
        listeImages.getSelectionModel().selectNext();
    }
    
    /* basculer vers l'image precedante */
    @FXML
    private void previous(MouseEvent event){
        listeImages.getSelectionModel().selectPrevious();
    }
    
    /* ajout d'un mot cle a une image */
    @FXML
    private void saveKey(ActionEvent event){
        
        if(!nomImage.getText().equals("") && !motCle.getText().equals("")){
            try{
                ObjectMapper mapper = new ObjectMapper();
                JSONObject obj = mapper.readValue(new File("motscles.json"), JSONObject.class);
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
                            ArrayList list;
                            list=(ArrayList) s;
                            list.add((selectedDirectory+"/"+nomImage.getText()));
                            obj.put(motCle.getText(), list);
                        }
                    }
                    else{
                        ArrayList list;
                        list=(ArrayList) s;
                        list.add((selectedDirectory+"/"+nomImage.getText()));
                        obj.put(motCle.getText(), list);
                    }
                }
                
                
                FileWriter file = new FileWriter("motscles.json");
                file.write(obj.toJSONString());
                file.flush();
                locale= new Locale(lang);
                bundle = ResourceBundle.getBundle("language.lang", locale);
                final Notifications notif = Notifications.create()
                    .title(bundle.getString("titre_notif"))
                    .text(bundle.getString("msg_notif"))
                    .hideAfter(Duration.seconds(10))
                    .darkStyle()
                    .position(Pos.BASELINE_CENTER);
                
                notif.showConfirm();
                
            } 
            catch(IOException e){}
            
            
            
        }
            
            
            
    }
    
    
    /* fonction de recherche */
    @FXML
    private void recherche(ActionEvent event){
        
        if(!champSearch.getText().equals("") && selectedDirectory!=null){
            if(motcle_rech.isSelected()){
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JSONObject obj = mapper.readValue(new File("motscles.json"), JSONObject.class);
                    Object s=obj.get(champSearch.getText());
                    if(s!=null){
                        ObservableList<String> images =FXCollections.observableArrayList ();
                        ((ArrayList) s).forEach((f) -> {
                            images.add(f.toString().substring(selectedDirectory.getAbsolutePath().length()+1));
                        });
                        
                        
                        listeImages.setItems(null);
                        listeImages.setItems(images);
                        
                        
                    }
                    else{
                        listeImages.setItems(null);
                        locale= new Locale(lang);
                        bundle = ResourceBundle.getBundle("language.lang", locale);
                        Alert alert=new Alert(Alert.AlertType.WARNING);
                        alert.setContentText(bundle.getString("folder_mess_empty_key"));
                        alert.setResizable(false);
                        alert.setHeaderText("");
                        alert.show();
                    }
                
                } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                String im=selectedDirectory.getAbsolutePath()+"/"+champSearch.getText();
                ObservableList<String> images =FXCollections.observableArrayList ();
                for (final File f : selectedDirectory.listFiles(IMAGE_FILTER)) {
                    if((f.getName().substring(0,f.getName().length()-4)).equals(champSearch.getText())){
                        images.add(f.getName());
                    }   
                }
                if(!images.isEmpty()){
                    listeImages.setItems(images);
                }
                else{
                    
                    listeImages.setItems(null);
                    locale= new Locale(lang);
                    bundle = ResourceBundle.getBundle("language.lang", locale);
                    images.add(bundle.getString("folder_mess_empty"));
                    Alert alert=new Alert(Alert.AlertType.WARNING);
                    alert.setContentText(bundle.getString("folder_mess_empty"));
                    alert.setResizable(false);
                    alert.setHeaderText("");
                    alert.show();
                    
                }
            }
        }
        else if(selectedDirectory==null){
            
            locale= new Locale(lang);
            bundle = ResourceBundle.getBundle("language.lang", locale);
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setContentText(bundle.getString("folder_mess_empty_dir"));
            alert.setResizable(false);
            alert.setHeaderText("");
            alert.show();
        }
        
    }
    
    /* listener du champs de recherche */
    @FXML
    private void champSearchListen(KeyEvent event){
        if(champSearch.getText().equals("") && selectedDirectory!=null){
            listeImages.setItems(listeImagesCurrent.getItems());
            imageSelected.setImage(null);
            ImageEditOption.setVisible(false);
        }
        
    }
    static final String[] EXTENSIONS = new String[]{"gif", "png", "bmp", "jpg"};
    
    static final FilenameFilter IMAGE_FILTER = (final File dir, final String name) -> {
        for (final String ext : EXTENSIONS) {
            if (name.endsWith("." + ext)) {
                return (true);
            }
        }
        return (false);
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
        FXMLDocumentController.lang = lang;
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
            JSONObject obj = mapper.readValue(new File("motscles.json"), JSONObject.class);
          
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

    private void initParametresAccueil() {
        setLang("fr");
        ToggleGroup group = new ToggleGroup();
        motcle_rech.setToggleGroup(group);
        nom_rech.setToggleGroup(group);
        nom_rech.setSelected(true);
    
    }
    
}
