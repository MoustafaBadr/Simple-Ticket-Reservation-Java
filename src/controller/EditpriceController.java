package controller;

import Model.prices;
import com.jfoenix.controls.JFXTextField;
import database.Dataconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Mostafa Badr
 */
public class EditpriceController implements Initializable {

    Parent home_page_parent;
    Scene home_page_scene;
    Stage app_stage;
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    private Dataconnection dc;
    prices price;
    
    @FXML
    private JFXTextField newprice;
    @FXML
    private TableView<prices> pricetable;
    @FXML
    private TableColumn<prices, String> colmnprice;
    @FXML
    private TableColumn<prices, String> colmnid;
    private ObservableList<prices> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         pricetable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                price = pricetable.getSelectionModel().getSelectedItem() != null ? pricetable.getSelectionModel().getSelectedItem() : null;
                if (price != null) {
                    setTextFieldData();
                }
            }
        });
        dc = new Dataconnection();
                retrieveprice(new ActionEvent());
    }

    @FXML
    private void Saveprice(ActionEvent event) throws SQLException {
        if (newprice.getText().equals("")) {
            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text("يجــب ادخال السعـــر").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();

        } else {
            String price = newprice.getText();
            String Sql = "insert into prices (price) values('" + price + "') ";
            ps = dc.mkDataBase().prepareStatement(Sql);
            ps.execute();
            Notifications notificationBuildeer = Notifications.create().
                    title("Saved").
                    text("تـــم الحـــفظ").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {

                        System.out.println("saved Noti ");

                    });
            notificationBuildeer.showConfirm();
                retrieveprice(new ActionEvent());
        }
    }

    @FXML
    private void Deleteprice(ActionEvent event) {
        try {
            if (pricetable.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("يجــب تحـــديد سعر للحــذف ").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {

                ResultSet rss = null;

                int selectedItem = pricetable.getSelectionModel().getSelectedIndex();

                String query = "delete from prices  where price_id='" + list.get(selectedItem).idProperty().getValue() + "' ";

                ps = dc.mkDataBase().prepareStatement(query);
                ps.execute();

                Notifications notificationBuildeer = Notifications.create().
                        title("updated").
                        text("تـــــم الـــحذف").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("update Noti ");
                        });
                notificationBuildeer.showConfirm();
                retrieveprice(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void retrieveprice(ActionEvent event) {
         try {
            Connection con = dc.mkDataBase();
            list = FXCollections.observableArrayList();

            rs = con.createStatement().executeQuery("SELECT * FROM bakery.prices");
            while (rs.next()) {
                list.add(new prices(rs.getString(2), rs.getInt(1)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditpriceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colmnprice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmnid.setCellValueFactory(new PropertyValueFactory<>("id"));

        pricetable.setItems(null);
        pricetable.setItems(list);
    }
    
     @FXML
    private void BackButton(ActionEvent event) throws IOException {

        home_page_parent = FXMLLoader.load(getClass().getResource("/view/admin.fxml"));
        home_page_scene = new Scene(home_page_parent);
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }
    private void setTextFieldData() {
        this.newprice.setText(price != null ? price.priceProperty().getValue() : "");
 
    }

}
