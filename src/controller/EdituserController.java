package controller;

import Model.users;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
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
public class EdituserController implements Initializable {

    ObservableList<String> corseslist = FXCollections.observableArrayList("admin", "manager", "casher");
    Parent home_page_parent;
    Scene home_page_scene;
    Stage app_stage;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private Dataconnection dc;
    users user;
    @FXML
    private JFXTextField username;
    @FXML
    JFXPasswordField userpass;
    @FXML
    private JFXTextField userdesc;
    @FXML
    private JFXComboBox usertype;
    @FXML
    private TableView<users> usertable;
    @FXML
    private TableColumn<users, String> colmname;
    @FXML
    private TableColumn<users, String> colmpass;
    @FXML
    private TableColumn<users, String> colmtype;
    @FXML
    private TableColumn<users, String> colmdesc;
    @FXML
    private TableColumn<users, String> colmnid;

    ObservableList<users> observcorselist;
    private ObservableList<users> list;

    String UserName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usertype.setValue("admin");
        usertype.setItems(corseslist);
        usertable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                user = usertable.getSelectionModel().getSelectedItem() != null ? usertable.getSelectionModel().getSelectedItem() : null;
                if (user != null) {
                    setTextFieldData();
                }
            }
        });

        dc = new Dataconnection();
    }

    @FXML
    private void Saveuser(ActionEvent event) throws SQLException {
        if (usertype.getValue().equals("")) {
            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text("يجــب اختيار الصلاحية ").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();

        } else {

            try {
                String UserName = username.getText();
                String UserPass = userpass.getText();
                String UserType = usertype.getValue().toString();
                String Userdesc = userdesc.getText();
                Connection con = dc.mkDataBase();
                rs = con.createStatement().executeQuery("SELECT user_name FROM bakery.users where user_name='"+UserName+"'");
                if (!(rs.next())) {

                    String Sql = "insert into bakery.users (user_name,user_pass,user_type,user_desc) values ('" + UserName + "','" + UserPass + "','" + UserType + "','" + Userdesc + "')";
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
                } else {
                    Notifications notificationBuildeer = Notifications.create().
                            title("warning").
                            text("هــذا الاســم موجــود بالفــعل ").
                            graphic(null).
                            hideAfter(Duration.seconds(4)).
                            position(Pos.TOP_RIGHT).
                            onAction(e -> {
                                System.out.println("warning Noti ");
                            });
                    notificationBuildeer.showWarning();
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditclientsController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            retrieveuser(new ActionEvent());
        }
    }

    @FXML
    private void Updateuser(ActionEvent event) {
        try {
            if (usertable.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("يجب تحـــديدعـــميل").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {
                int selectedItem = usertable.getSelectionModel().getSelectedIndex();

                String query = "update users set user_name='" + username.getText() + "'"
                        + ",user_pass='" + userpass.getText() + "',user_type='" + usertype.getValue() + "'"
                        + ",user_desc='" + userdesc.getText() + "'"
                        + "  where user_id='" + list.get(selectedItem).idProperty().getValue() + "' ";
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
                retrieveuser(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void Deleteuser(ActionEvent event) {
        try {
            if (usertable.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("يجب تحـــديد عميل").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {
                int selectedItem = usertable.getSelectionModel().getSelectedIndex();

                String query = "delete from users where user_id='" + list.get(selectedItem).idProperty().getValue() + "' ";
                ps = dc.mkDataBase().prepareStatement(query);
                ps.execute();
                Notifications notificationBuildeer = Notifications.create().
                        title("updated").
                        text("تـــــم الحـــــذف").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("update Noti ");
                        });
                notificationBuildeer.showConfirm();
                retrieveuser(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void retrieveuser(ActionEvent event) {
        try {
            Connection con = dc.mkDataBase();
            list = FXCollections.observableArrayList();

            rs = con.createStatement().executeQuery("SELECT * FROM bakery.users");
            while (rs.next()) {
                list.add(new users(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(1)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EdituserController.class.getName()).log(Level.SEVERE, null, ex);
        }

        colmname.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmpass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        colmtype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colmdesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colmnid.setCellValueFactory(new PropertyValueFactory<>("id"));

        usertable.setItems(null);
        usertable.setItems(list);
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
        this.username.setText(user != null ? user.nameProperty().getValue() : "");
        this.userpass.setText(user != null ? user.passProperty().getValue() : "");
        this.userdesc.setText(user != null ? user.descProperty().getValue() : "");
        this.usertype.setValue(user != null ? user.typeProperty().getValue() : "");

    }

}
