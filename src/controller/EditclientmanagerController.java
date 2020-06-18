package controller;

import Model.client;
import com.jfoenix.controls.JFXComboBox;
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
public class EditclientmanagerController implements Initializable {

    Parent home_page_parent;
    Scene home_page_scene;
    Stage app_stage;

    PreparedStatement ps = null;
    ResultSet rs = null;
    private Dataconnection dc;
    static int x;
    client client;

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField nationalcode;
    @FXML
    private JFXTextField mailid;
    @FXML
    private JFXTextField phonenum;
    @FXML
    private JFXComboBox ranks;
    @FXML
    private TableView<client> clienttable;
    @FXML
    private TableColumn<client, String> colmnrank;
    @FXML
    private TableColumn<client, String> colmnname;
    @FXML
    private TableColumn<client, String> colmnnational;
    @FXML
    private TableColumn<client, String> colmnmail;
    @FXML
    private TableColumn<client, String> colmnphone;
    @FXML
    private TableColumn<client, String> colmnid;

    ObservableList<client> observcorselist;
    private ObservableList<client> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        clienttable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                client = clienttable.getSelectionModel().getSelectedItem() != null ? clienttable.getSelectionModel().getSelectedItem() : null;
                if (client != null) {
                    setTextFieldData();
                }
            }
        });
        retrieveranks(new ActionEvent());
    }

    @FXML
    private void retrieveranks(ActionEvent event) {

        try {
            Connection con = dc.mkDataBase();

            rs = con.createStatement().executeQuery("SELECT * FROM bakery.ranks");
            while (rs.next()) {
                ranks.getItems().addAll(rs.getString("rank_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditclientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Saveclientfrommanager(ActionEvent event) throws SQLException {
        if (ranks.getValue().equals("مشير")) {
            x = 1;
        } else if (ranks.getValue().equals("فريق أول")) {
            x = 2;
        } else if (ranks.getValue().equals("فريق")) {
            x = 3;
        } else if (ranks.getValue().equals("لواء")) {
            x = 4;
        } else if (ranks.getValue().equals("عميد")) {
            x = 5;
        } else if (ranks.getValue().equals("عقيد")) {
            x = 6;
        } else if (ranks.getValue().equals("مقدم")) {
            x = 7;
        } else if (ranks.getValue().equals("رائد")) {
            x = 8;
        } else if (ranks.getValue().equals("نقيب")) {
            x = 9;
        } else if (ranks.getValue().equals("ملازم أول")) {
            x = 10;
        } else if (ranks.getValue().equals("ملازم")) {
            x = 11;
        } else if (ranks.getValue().equals("مساعد أول")) {
            x = 12;
        } else if (ranks.getValue().equals("مساعد")) {
            x = 13;
        } else if (ranks.getValue().equals("رقيب أول")) {
            x = 14;
        } else if (ranks.getValue().equals("رقيب")) {
            x = 15;
        } else if (ranks.getValue().equals("عريف")) {
            x = 16;
        } else if (ranks.getValue().equals("طالب")) {
            x = 17;
        } else if (ranks.getValue().equals("صانع متدرج")) {
            x = 18;
        } else if (ranks.getValue().equals("وكيل عريف")) {
            x = 19;
        } else if (ranks.getValue().equals("محارب")) {
            x = 20;
        } else if (ranks.getValue().equals("جندى")) {
            x = 21;
        } else if (ranks.getValue().equals("مدنى")) {
            x = 22;
        }

        String first_name = firstname.getText();

        String national_code = nationalcode.getText();
        String mail_id = mailid.getText();
        String phone_number = phonenum.getText();
        String title = "هـذا الرقــم العــسكري موجود بالفعل";
        String title1 = "هـذا الرقــم القــومي موجود بالفعل";
        String title2 = "رقم الهاتف موجود بالفعل";

        if (firstname.getText().equals("")) {
            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text(" ادخـــل الاسم من فضـــلك").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();

        } else if (getUserncode()) {
             Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text(title1).
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
            
        } else if (getUsermail()) {
             Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text(title).
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
            
        } else if (getUserphone()) {
             Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text(title2).
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
            
        } else {
           String Sql = "insert into bakery.clients (client_name,national_code,mail_id,phone,fk_rank_id) values ('" + first_name + "','" + national_code + "','" + mail_id + "','" + phone_number + "','" + x + "')";
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
            Searchclient(event);
        }
    }

    @FXML
    private void Searchclient(ActionEvent event) {
        if (firstname.getText().equals("")) {
            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text("يجب ادخـــال الاســـم").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
        } else {
            try {
                Connection con = dc.mkDataBase();
                list = FXCollections.observableArrayList();

                String first_name = firstname.getText();

                String selectsqlemp = "SELECT  client_name ,national_code ,mail_id,phone,rank_name,client_id"
                        + " FROM bakery.clients inner join ranks "
                        + "on (fk_rank_id=ranks_id)"
                        + "AND client_name LIKE ?";

                ps = con.prepareStatement(selectsqlemp);
                ps.setString(1, '%' + first_name + '%');
                rs = ps.executeQuery();
                if (!(rs.next())) {
                    Notifications notificationBuildeer = Notifications.create().
                            title("warning").
                            text("غـــير مـــوجود").
                            graphic(null).
                            hideAfter(Duration.seconds(4)).
                            position(Pos.TOP_RIGHT).
                            onAction(e -> {
                                System.out.println("warning Noti ");
                            });
                    notificationBuildeer.showWarning();
                } else {
                    do {
                        list.add(new client(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(1), rs.getInt(6)));
                    } while (rs.next());
                    colmnname.setCellValueFactory(new PropertyValueFactory<>("name"));
                    colmnnational.setCellValueFactory(new PropertyValueFactory<>("nationalcode"));
                    colmnphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
                    colmnmail.setCellValueFactory(new PropertyValueFactory<>("mailid"));
                    colmnrank.setCellValueFactory(new PropertyValueFactory<>("rank"));
                    colmnid.setCellValueFactory(new PropertyValueFactory<>("id"));

                    clienttable.setItems(null);
                    clienttable.setItems(list);

                }
            } catch (SQLException ex) {
                Logger.getLogger(EditclientsController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void Updateclient(ActionEvent event
    ) {
        if (ranks.getValue().equals("مشير")) {
            x = 1;
        } else if (ranks.getValue().equals("فريق أول")) {
            x = 2;
        } else if (ranks.getValue().equals("فريق")) {
            x = 3;
        } else if (ranks.getValue().equals("لواء")) {
            x = 4;
        } else if (ranks.getValue().equals("عميد")) {
            x = 5;
        } else if (ranks.getValue().equals("عقيد")) {
            x = 6;
        } else if (ranks.getValue().equals("مقدم")) {
            x = 7;
        } else if (ranks.getValue().equals("رائد")) {
            x = 8;
        } else if (ranks.getValue().equals("نقيب")) {
            x = 9;
        } else if (ranks.getValue().equals("ملازم أول")) {
            x = 10;
        } else if (ranks.getValue().equals("ملازم")) {
            x = 11;
        } else if (ranks.getValue().equals("مساعد أول")) {
            x = 12;
        } else if (ranks.getValue().equals("مساعد")) {
            x = 13;
        } else if (ranks.getValue().equals("رقيب أول")) {
            x = 14;
        } else if (ranks.getValue().equals("رقيب")) {
            x = 15;
        } else if (ranks.getValue().equals("عريف")) {
            x = 16;
        } else if (ranks.getValue().equals("طالب")) {
            x = 17;
        } else if (ranks.getValue().equals("صانع متدرج")) {
            x = 18;
        } else if (ranks.getValue().equals("وكيل عريف")) {
            x = 19;
        } else if (ranks.getValue().equals("محارب")) {
            x = 20;
        } else if (ranks.getValue().equals("جندى")) {
            x = 21;
        } else if (ranks.getValue().equals("مدنى")) {
            x = 22;
        }
        try {
            if (clienttable.getSelectionModel().getSelectedIndex() == -1) {
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
                int selectedItem = clienttable.getSelectionModel().getSelectedIndex();

                String query = "update clients set client_name='" + firstname.getText() + "'"
                        + ",national_code='" + nationalcode.getText() + "'"
                        + ",mail_id='" + mailid.getText() + "'"
                        + ",phone='" + phonenum.getText() + "'"
                        + ",fk_rank_id='" + x + "'"
                        + " where client_id='" + list.get(selectedItem).idProperty().getValue() + "' ";

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
                Searchclient(event);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void Deleteclient(ActionEvent event
    ) {
        try {
            if (clienttable.getSelectionModel().getSelectedIndex() == -1) {
                Notifications notificationBuildeer = Notifications.create().
                        title("warning").
                        text("يجب تحـــديد عـــميل").
                        graphic(null).
                        hideAfter(Duration.seconds(4)).
                        position(Pos.TOP_RIGHT).
                        onAction(e -> {

                            System.out.println("warning Noti ");

                        });
                notificationBuildeer.showError();
            } else {
                int selectedItem = clienttable.getSelectionModel().getSelectedIndex();

                String query = "delete from clients where client_id='" + list.get(selectedItem).idProperty().getValue() + "' ";
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
                clienttable.setItems(null);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void BackButton(ActionEvent event) throws IOException {

        home_page_parent = FXMLLoader.load(getClass().getResource("/view/manager.fxml"));
        home_page_scene = new Scene(home_page_parent);
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(home_page_scene);

        app_stage.show();

    }

    private void setTextFieldData() {
        this.firstname.setText(client != null ? client.nameProperty().getValue() : "");
        this.nationalcode.setText(client != null ? client.nationalcodeProperty().getValue() : "");
        this.mailid.setText(client != null ? client.mailidProperty().getValue() : "");
        this.phonenum.setText(client != null ? client.phoneProperty().getValue() : "");
        this.ranks.setValue(client != null ? client.rankProperty().getValue() : "");

    }
    
     public boolean getUserncode() throws SQLException {
        String National = nationalcode.getText();

        Connection con = dc.mkDataBase();
        rs = con.createStatement().executeQuery("SELECT national_code FROM bakery.clients where national_code = '" + National + "'");
        if (!rs.next()) {
            return false;
        } else {
            return true;
        }
    }
    public boolean getUsermail() throws SQLException {
        String MAIL = mailid.getText();

        Connection con = dc.mkDataBase();
        rs = con.createStatement().executeQuery("SELECT mail_id FROM bakery.clients where mail_id = '" + MAIL + "'");
        if (!rs.next()) {
            return false;
        } else {
            return true;
        }
    }
    public boolean getUserphone() throws SQLException {
        String PH = phonenum.getText();

        Connection con = dc.mkDataBase();
        rs = con.createStatement().executeQuery("SELECT phone FROM bakery.clients where phone = '" + PH + "'");
        if (!rs.next()) {
            return false;
        } else {
            return true;
        }
    }

}
