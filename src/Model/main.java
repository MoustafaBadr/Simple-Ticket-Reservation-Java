/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.print.PageFormat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Mostafa Badr
 */
public class main extends Application {
    
     @Override
    public void start(Stage stage) throws Exception {
         Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle(" المخــبز");
        stage.getIcons().add(new Image("Images/cashiring.png"));
        stage.show();
        stage.setResizable(false);

    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
