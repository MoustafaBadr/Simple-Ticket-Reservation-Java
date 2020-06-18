package controller;

import Model.UsersSingleton;
import Model.users;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.Dataconnection;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Mostafa Badr
 */
public class LoginController implements Initializable {

    PreparedStatement ps = null;
    ResultSet rs = null;
    private Dataconnection dc;
    public String UserType = "";
    public String UserName = "";

    Parent home_page_parent;
    Scene home_page_scene;
    Stage app_stage;

    @FXML
    JFXTextField name;
    @FXML
    JFXPasswordField pass;
    @FXML
    JFXButton login;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pass.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    boolean isEmpty = isEmpty(name.getText(), pass.getText());
                    if (isEmpty) {

                        Notifications notificationBuildeer = Notifications.create().
                                title("warning").
                                text("يجــب ادخــال اســم").
                                graphic(null).
                                hideAfter(Duration.seconds(4)).
                                position(Pos.TOP_RIGHT).
                                onAction(e -> {
                                    System.out.println("warning Noti ");
                                });
                        notificationBuildeer.showWarning();
                    } else {

                        ResultSet rs = null;

                        try {
                            dc = new Dataconnection();
                            String sql = "SELECT * FROM `users`where user_name='"
                                    + name.getText()
                                    + "'  AND user_pass='"
                                    + pass.getText() + "'  ";

                            ps = dc.mkDataBase().prepareStatement(sql);
                            rs = ps.executeQuery();

                            if (rs.next()) {
                                UserType = rs.getString("user_type");
                                UserName = rs.getString("user_name");
                                UsersSingleton admin = UsersSingleton.getInstance();
                                admin.setUserName(UserName);

                                if (UserType.equals("admin")) {

                                    home_page_parent = FXMLLoader.load(getClass().getResource("/view/admin.fxml"));
                                    home_page_scene = new Scene(home_page_parent);
                                    app_stage = (Stage) ((Node) ke.getSource()).getScene().getWindow();

                                    app_stage.hide();
                                    app_stage.setScene(home_page_scene);
                                    app_stage.setTitle("Admin");

                                    app_stage.show();

                                    Notifications notificationBuildeer = Notifications.create().
                                            title("Welcome").
                                            text("مرحـــبا بكم").
                                            graphic(null).
                                            hideAfter(Duration.seconds(4)).
                                            position(Pos.TOP_RIGHT).
                                            onAction(e -> {
                                                System.out.println("warning Noti ");
                                            });
                                    notificationBuildeer.showInformation();
                                } else if (UserType.equals("manager")) {
                                    System.out.println(UserType);
                                    home_page_parent = FXMLLoader.load(getClass().getResource("/view/manager.fxml"));
                                    home_page_scene = new Scene(home_page_parent);
                                    app_stage = (Stage) ((Node) ke.getSource()).getScene().getWindow();

                                    app_stage.hide();
                                    app_stage.setScene(home_page_scene);
                                    app_stage.setTitle("Manager");

                                    app_stage.show();

                                    Notifications notificationBuildeer = Notifications.create().
                                            title("Welcome").
                                            text("مرحـــبا بكم").
                                            graphic(null).
                                            hideAfter(Duration.seconds(4)).
                                            position(Pos.TOP_RIGHT).
                                            onAction(e -> {
                                                System.out.println("warning Noti ");
                                            });
                                    notificationBuildeer.showInformation();
                                } else if (UserType.equals("casher")) {
                                    System.out.println(UserType);

                                    home_page_parent = FXMLLoader.load(getClass().getResource("/view/casher.fxml"));
                                    home_page_scene = new Scene(home_page_parent);
                                    app_stage = (Stage) ((Node) ke.getSource()).getScene().getWindow();

                                    app_stage.hide();
                                    app_stage.setScene(home_page_scene);
                                    app_stage.setTitle("Casher");

                                    app_stage.show();

                                    Notifications notificationBuildeer = Notifications.create().
                                            title("Welcome").
                                            text("مرحـــبا بكم").
                                            graphic(null).
                                            hideAfter(Duration.seconds(4)).
                                            position(Pos.TOP_RIGHT).
                                            onAction(e -> {
                                                System.out.println("warning Noti ");
                                            });
                                    notificationBuildeer.showInformation();
                                }

                            } else {

                                Notifications notificationBuildeer = Notifications.create().
                                        title("warning").
                                        text("خطـــا فى الاسم ورقم السر").
                                        graphic(null).
                                        hideAfter(Duration.seconds(4)).
                                        position(Pos.TOP_RIGHT).
                                        onAction(e -> {
                                            System.out.println("warning Noti ");
                                        });
                                notificationBuildeer.showWarning();
                                name.setText("");
                                pass.setText("");

                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();

                        }

                    }
                }
            }
        });

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
        boolean isEmpty = isEmpty(name.getText(), pass.getText());
        if (isEmpty) {

            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text("يجــب ادخــال اســم").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
        } else {

            ResultSet rs = null;

            try {
                dc = new Dataconnection();
                String sql = "SELECT * FROM `users`where user_name='"
                        + name.getText()
                        + "'  AND user_pass='"
                        + pass.getText() + "'  ";

                ps = dc.mkDataBase().prepareStatement(sql);
                rs = ps.executeQuery();

                if (rs.next()) {
                    UserType = rs.getString("user_type");
                    UserName = rs.getString("user_name");
                    UsersSingleton admin = UsersSingleton.getInstance();
                    admin.setUserName(UserName);

                    if (UserType.equals("admin")) {
                        home_page_parent = FXMLLoader.load(getClass().getResource("/view/admin.fxml"));
                        home_page_scene = new Scene(home_page_parent);
                        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.hide();
                        app_stage.setScene(home_page_scene);
                        app_stage.setTitle("Admin");

                        app_stage.show();

                        Notifications notificationBuildeer = Notifications.create().
                                title("Welcome").
                                text("مرحـــبا بكم").
                                graphic(null).
                                hideAfter(Duration.seconds(4)).
                                position(Pos.TOP_RIGHT).
                                onAction(e -> {
                                    System.out.println("warning Noti ");
                                });
                        notificationBuildeer.showInformation();
                    } else if (UserType.equals("manager")) {
                        home_page_parent = FXMLLoader.load(getClass().getResource("/view/manager.fxml"));
                        home_page_scene = new Scene(home_page_parent);
                        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.hide();
                        app_stage.setScene(home_page_scene);
                        app_stage.setTitle("Manager");

                        app_stage.show();

                        Notifications notificationBuildeer = Notifications.create().
                                title("Welcome").
                                text("مرحـــبا بكم").
                                graphic(null).
                                hideAfter(Duration.seconds(4)).
                                position(Pos.TOP_RIGHT).
                                onAction(e -> {
                                    System.out.println("warning Noti ");
                                });
                        notificationBuildeer.showInformation();
                    } else if (UserType.equals("casher")) {
                        System.out.println(UserType);

                        home_page_parent = FXMLLoader.load(getClass().getResource("/view/casher.fxml"));
                        home_page_scene = new Scene(home_page_parent);
                        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        app_stage.hide();
                        app_stage.setScene(home_page_scene);
                        app_stage.setTitle("Casher");

                        app_stage.show();

                        Notifications notificationBuildeer = Notifications.create().
                                title("Welcome").
                                text("مرحـــبا بكم").
                                graphic(null).
                                hideAfter(Duration.seconds(4)).
                                position(Pos.TOP_RIGHT).
                                onAction(e -> {
                                    System.out.println("warning Noti ");
                                });
                        notificationBuildeer.showInformation();
                    }

                } else {

                    Notifications notificationBuildeer = Notifications.create().
                            title("warning").
                            text("خطـــا فى الاسم ورقم السر").
                            graphic(null).
                            hideAfter(Duration.seconds(4)).
                            position(Pos.TOP_RIGHT).
                            onAction(e -> {
                                System.out.println("warning Noti ");
                            });
                    notificationBuildeer.showWarning();
                    name.setText("");
                    pass.setText("");

                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }

        }
    }

    public boolean isEmpty(String... text) {
        for (String s : text) {
            if (s.isEmpty()) {
                return true;
            }
        }
        return false;
    }

   
}
