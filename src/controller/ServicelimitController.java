package controller;

import Model.client;
import Model.limits;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import static controller.EditclientsController.x;
import database.Dataconnection;
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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Mostafa Badr
 */
public class ServicelimitController implements Initializable {

    PreparedStatement ps = null;
    ResultSet rs = null;
    private Dataconnection dc;

    @FXML
    JFXComboBox ranks;
    @FXML
    JFXTextField bonnum;

    @FXML
    public TableView<limits> limittable;
    @FXML
    private TableColumn<limits, String> colmid;
    @FXML
    private TableColumn<limits, String> colmrank;
    @FXML
    private TableColumn<limits, String> colmnum;

    ObservableList<limits> observcorselist;
    private ObservableList<limits> list;
    client client;
    limits limit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        limittable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                limit = limittable.getSelectionModel().getSelectedItem() != null ? limittable.getSelectionModel().getSelectedItem() : null;
                if (limit != null) {
                    setTextFieldData();
                }
            }
        });

        retrievelimit(new ActionEvent());
        retrieveranks(new ActionEvent());

    }

    @FXML
    private void Savenum(ActionEvent event) {
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
        } else if (ranks.getValue().equals("الكل")) {
            x = 23;
        }

        String Bonnumber = bonnum.getText();
        try {

            String Sql = "insert into bakery.limits (rank_id,limits) values ('" + x + "','" + Bonnumber + "')";
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
            retrievelimit(new ActionEvent());
        } catch (SQLException ex) {
            Logger.getLogger(EditserviceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Updatenum(ActionEvent event) {
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
        } else if (ranks.getValue().equals("الكل")) {
            x = 23;
        }
        try {
            if (limittable.getSelectionModel().getSelectedIndex() == -1) {
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
                int selectedItem = limittable.getSelectionModel().getSelectedIndex();

                String query = "update limits set limits='" + bonnum.getText() + "'"
                        + " where limit_id='" + list.get(selectedItem).idProperty().getValue() + "' ";

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
                retrievelimit(new ActionEvent());

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void Deletenum(ActionEvent event) {
        try {
            if (limittable.getSelectionModel().getSelectedIndex() == -1) {
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
                int selectedItem = limittable.getSelectionModel().getSelectedIndex();

                String query = "delete from limits where limit_id='" + list.get(selectedItem).idProperty().getValue() + "' ";
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
                limittable.setItems(null);
                retrieveranks(new ActionEvent());
                retrievelimit(new ActionEvent());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
    private void retrievelimit(ActionEvent event) {
        try {
            Connection con = dc.mkDataBase();
            list = FXCollections.observableArrayList();

            rs = con.createStatement().executeQuery("SELECT limit_id,limits,rank_name FROM bakery.limits inner join ranks on (rank_id=ranks_id)");

            while (rs.next()) {
                list.add(new limits(rs.getInt(2), rs.getInt(1), rs.getString(3)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditpriceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colmrank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        colmnum.setCellValueFactory(new PropertyValueFactory<>("limit"));
        colmid.setCellValueFactory(new PropertyValueFactory<>("id"));

        limittable.setItems(null);
        limittable.setItems(list);
    }

    private void setTextFieldData() {
        this.bonnum.setText(limit != null ? limit.limitProperty().getValue().toString() : "");
        this.ranks.setValue(limit != null ? limit.rankProperty().getValue() : "");
    }
}
