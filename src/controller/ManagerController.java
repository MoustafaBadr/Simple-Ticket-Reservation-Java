
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mostafa Badr
 */
public class ManagerController implements Initializable {

    Parent home_page_parent;
    Scene home_page_scene;
    Stage app_stage;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void editclients(ActionEvent event) throws IOException {
        
        home_page_parent = FXMLLoader.load(getClass().getResource("/view/editclientmanager.fxml"));
        home_page_scene = new Scene(home_page_parent);
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.setTitle("تعديل الزبائن");

        app_stage.show();
    }

    @FXML
    private void editservice(ActionEvent event) throws IOException {
        
        home_page_parent = FXMLLoader.load(getClass().getResource("/view/editservicemanager.fxml"));
        home_page_scene = new Scene(home_page_parent);
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.setTitle("تعديل المستخدمين");

        app_stage.show();
    }

}
