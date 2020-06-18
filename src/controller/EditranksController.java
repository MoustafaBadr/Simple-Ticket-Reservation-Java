package controller;

import Model.ranks;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.control.Notifications;
import javafx.util.Duration;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Mostafa Badr
 */
public class EditranksController implements Initializable {

    Parent home_page_parent;
    Scene home_page_scene;
    Stage app_stage;
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    private Dataconnection dc;
    ranks rank;
    @FXML
    private JFXTextField rank_name;
    @FXML
    private TableView<ranks> rankstable;
    @FXML
    private TableColumn<ranks, String> colmname;
    @FXML
    private TableColumn<ranks, String> colmnid;
    private ObservableList<ranks> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rankstable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                rank = rankstable.getSelectionModel().getSelectedItem() != null ? rankstable.getSelectionModel().getSelectedItem() : null;
                if (rank != null) {
                    setTextFieldData();
                }
            }
        });
        dc = new Dataconnection();
    }

    @FXML
    private void SaveRanks(ActionEvent event) throws SQLException {
        if (rank_name.getText().equals("")) {
            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text("يجــب ادخال الاســم").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();

        } else {
            String Rankname = rank_name.getText();
            String Sql = "insert into ranks (rank_name) values('" + Rankname + "') ";
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
            retrieveranks(new ActionEvent());
        }
    }

    @FXML
    private void UpdateRanks(ActionEvent event) {
        try {
            if (rankstable.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("يجب اختيار عميل ").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {
                int selectedItem = rankstable.getSelectionModel().getSelectedIndex();

                String query = "update ranks set rank_name='" + rank_name.getText() + "'"
                        + "  where ranks_id='" + list.get(selectedItem).idProperty().getValue() + "' ";

                ps = dc.mkDataBase().prepareStatement(query);
                ps.execute();
                Notifications notificationBuildeer = Notifications.create().
                        title("updated").
                        text("تـــــم التــــعديل").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("update Noti ");
                        });
                notificationBuildeer.showConfirm();
                retrieveranks(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void DeleteRanks(ActionEvent event) {
        try {
            if (rankstable.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("يجب اختيار عميل").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {

                ResultSet rss = null;

                int selectedItem = rankstable.getSelectionModel().getSelectedIndex();

                String query = "delete from ranks  where ranks_id='" + list.get(selectedItem).idProperty().getValue() + "' ";

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
                retrieveranks(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    private void retrieveranks(ActionEvent event) {
        try {
            Connection con = dc.mkDataBase();
            list = FXCollections.observableArrayList();

            rs = con.createStatement().executeQuery("SELECT * FROM bakery.ranks");
            while (rs.next()) {
                list.add(new ranks(rs.getString(2), rs.getInt(1)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EdituserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        colmname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmnid.setCellValueFactory(new PropertyValueFactory<>("id"));

        rankstable.setItems(null);
        rankstable.setItems(list);
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
        this.rank_name.setText(rank != null ? rank.nameProperty().getValue() : "");
    }

}
