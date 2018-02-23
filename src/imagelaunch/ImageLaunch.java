/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagelaunch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.simple.*;

/**
 *
 * @author gaby & SY
 */
public class ImageLaunch extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));        
        Scene scene = new Scene(root);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
       @Override
       public void handle(WindowEvent e) {
          Platform.exit();
          System.exit(0);
       }
    });
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ObjectReader reader = mapper.reader();
            JSONObject obj = mapper.readValue(new File("src/imagelaunch/motscles.json"), JSONObject.class);
            obj.put("file", "hh");
            JSONArray list = new JSONArray();
            list.add("image1");
            list.add("image2");
            list.add("image3");
            obj.put("poisson", list);
//JSONObject obj=new JSONObject();
            /*obj.put("name", "o");
            obj.put("age", new Integer(100));
            
            JSONArray list = new JSONArray();
            list.add("msg 1");
            list.add("msg 2");
            list.add("msg 3");
            
            obj.put("messages", list);*/
            try (FileWriter file = new FileWriter("src/imagelaunch/motscles.json")) {
                //file.write("ppppp");
                file.write(obj.toJSONString());
                file.flush();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            System.out.print(obj.toJSONString());
            launch(args);
        } catch (IOException ex) {
            Logger.getLogger(ImageLaunch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
