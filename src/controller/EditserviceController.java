package controller;

import Model.UsersSingleton;
import Model.client;
import Model.prices;
import com.jfoenix.controls.JFXTextField;
import static controller.EditclientsController.x;
import database.Dataconnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import javax.print.*;
import java.awt.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.util.Arrays;
import javax.print.PrintService;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.HashAttributeSet;
import java.util.List;
import java.util.prefs.Preferences;
import javax.print.attribute.standard.PrinterName;

/**
 *
 * @author Mostafa Badr
 */
public class EditserviceController implements Initializable {

    PreparedStatement ps = null;
    ResultSet rs = null;
    private Dataconnection dc;

    public client client;
    int countservnum;
    int limit;
    String time;

    @FXML
    private JFXTextField firstname;
    @FXML
    private JFXTextField totalservice;
    @FXML
    public TableView<client> clienttable;
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
    @FXML
    private JFXTextField rank;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField servicenumber;
    @FXML
    private JFXTextField totalprice;
    @FXML
    private JFXTextField date;

    prices price;
    double pricevalue;
    int Servicenumberforuser;
    int counter;
    String Total;
    String serviceNum;
    static EditserviceController INSTANCE;

    ObservableList<client> observcorselist;
    private ObservableList<client> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        servicenumber.setEditable(false);
        totalservice.setEditable(false);

        counter = 0;

        getdatetime();

        clienttable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                client = clienttable.getSelectionModel().getSelectedItem() != null ? clienttable.getSelectionModel().getSelectedItem() : null;
                if (client != null) {
                    setTextFieldData();
                    String limit = getServiceLimt();
                    servicenumber.setText(limit);
                }
            }
        });

        totalprice.setEditable(false);
        totalprice.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                if (servicenumber.getText().equals("")) {
                    Notifications notificationBuildeer = Notifications.create().
                            title("warning").
                            text("يجب اخـــتيار عمـــيل ").
                            graphic(null).
                            hideAfter(Duration.seconds(4)).
                            position(Pos.TOP_RIGHT).
                            onAction(e -> {
                                System.out.println("warning Noti ");
                            });
                    notificationBuildeer.showWarning();
                } else {
                    settotalpricevalue();
                }
            }
        });

        reloadservice();
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
                Logger.getLogger(EditclientsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @FXML
    private void BackButton(ActionEvent event) throws IOException {
        Parent home_page_parent;
        Scene home_page_scene;
        Stage app_stage;

        home_page_parent = FXMLLoader.load(getClass().getResource("/view/admin.fxml"));
        home_page_scene = new Scene(home_page_parent);
        app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.hide();
        app_stage.setScene(home_page_scene);

        app_stage.show();

    }

    @FXML
    private void servicelimits(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/servicelimit.fxml"));
        Parent parent = loader.load();

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("اضــــافة اقصى عــدد");
        stage.setScene(new Scene(parent));
        stage.show();

    }

    private int retrievetotalservice() {

        try {
            String Date = date.getText();
            Connection con = dc.mkDataBase();

            rs = con.createStatement().executeQuery("SELECT COUNT(*) FROM bakery.service where time = '" + Date + "'");
            while (rs.next()) {
                countservnum = Integer.parseInt((rs.getString(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditclientsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return countservnum;
    }

    // get max service limits per day
    private int servicelimits() {

        try {
            Connection con = dc.mkDataBase();

            rs = con.createStatement().executeQuery("SELECT limits FROM bakery.limits where rank_id =23 ");
            while (rs.next()) {
                limit = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditclientsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return limit;
    }

    //get the last action time
    private String servicedate() {

        try {
            Connection con = dc.mkDataBase();

            rs = con.createStatement().executeQuery("SELECT max( date_format(time,\"%Y-%m-%d\")) FROM bakery.service ");
            while (rs.next()) {
                time = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditclientsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return time;
    }

    @FXML
    private void printing(ActionEvent event) {

        String totalperday = totalservice.getText();
        String day = getdatetime();
        String servicedate = servicedate();
        int a = Integer.parseInt(totalperday);
        int b = servicelimits();

        int number = countservice();

        if (a == b && day.equals(servicedate)) {
            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text(" عــفوا لقد بلغت الحد الاقصى").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
            //if you need to prevent specific customer change number == 1 to number == limit of this customer referneced from his rank. 
        } else if (number == 1) {
            Notifications notificationBuildeer = Notifications.create().
                    title("warning").
                    text("لقد تم الصرف لهذا العميل").
                    graphic(null).
                    hideAfter(Duration.seconds(4)).
                    position(Pos.TOP_RIGHT).
                    onAction(e -> {
                        System.out.println("warning Noti ");
                    });
            notificationBuildeer.showWarning();
        } else {

            try {
                String Rank = rank.getText();
                String Name = name.getText();
                String Date = date.getText();
                String numofserv = servicenumber.getText();
                String Totalprice = totalprice.getText();
                INSTANCE = new EditserviceController();

                List<PrintService> services = INSTANCE.getServicesByName("BIXOLON SRP-350III");
                if (services == null) {
                    throw new RuntimeException("No printer services available");
                }
                INSTANCE.printServices(services);

                try {
                    INSTANCE.printcard(services.get(0), Rank, Name, Date, numofserv, Totalprice);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                int selectedItem = clienttable.getSelectionModel().getSelectedIndex();

                String Sql = "insert into bakery.service (time,nomof_service,total_price,fk_client_id) values ('" + Date + "','" + numofserv + "','" + Totalprice + "','" + list.get(selectedItem).idProperty().getValue() + "')";
                ps = dc.mkDataBase().prepareStatement(Sql);
                ps.execute();

                if (totalservice.getText().equals("0")) {
                    counter++;
                } else {
                    counter = Integer.parseInt(totalservice.getText());
                    counter++;
                }
                totalservice.setText(String.valueOf(counter));
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

            } catch (SQLException ex) {
                Logger.getLogger(EditserviceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private double retrieveprice() {

        try {
            Connection con = dc.mkDataBase();

            rs = con.createStatement().executeQuery("SELECT price FROM bakery.prices");
            while (rs.next()) {
                pricevalue = Double.parseDouble(rs.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EditclientsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return pricevalue;
    }

    public void settotalpricevalue() {

        double numofserv = Double.parseDouble(servicenumber.getText());
        double total = numofserv * retrieveprice();

        String totalpr = String.valueOf(total);

        totalprice.setText(totalpr);
    }

    private void setTextFieldData() {
        this.rank.setText(client != null ? client.rankProperty().getValue() : "");
        this.name.setText(client != null ? client.nameProperty().getValue() : "");
    }

    public String getdatetime() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate now = LocalDate.now();
        date.setText(dtf.format(now));
        date.setEditable(false);

        return date.getText();

    }

    public void reloadservice() {
        String T = String.valueOf(retrievetotalservice());
        totalservice.setText(T);
    }

    public int countservice() {
        int selectedItem = clienttable.getSelectionModel().getSelectedIndex();
        String Date = date.getText();
        try {
            Connection con = dc.mkDataBase();

            rs = con.createStatement().executeQuery("SELECT count(nomof_service) FROM bakery.service where fk_client_id = '" + list.get(selectedItem).idProperty().getValue() + "' AND time = '" + Date + "'");
            while (rs.next()) {
                Servicenumberforuser = Integer.parseInt(rs.getString(1));

            }
        } catch (SQLException ex) {
            Logger.getLogger(EditclientsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return Servicenumberforuser;
    }

    private String getServiceLimt() {
        if (rank.getText().equals("مشير")) {
            x = 1;
        } else if (rank.getText().equals("فريق أول")) {
            x = 2;
        } else if (rank.getText().equals("فريق")) {
            x = 3;
        } else if (rank.getText().equals("لواء")) {
            x = 4;
        } else if (rank.getText().equals("عميد")) {
            x = 5;
        } else if (rank.getText().equals("عقيد")) {
            x = 6;
        } else if (rank.getText().equals("مقدم")) {
            x = 7;
        } else if (rank.getText().equals("رائد")) {
            x = 8;
        } else if (rank.getText().equals("نقيب")) {
            x = 9;
        } else if (rank.getText().equals("ملازم أول")) {
            x = 10;
        } else if (rank.getText().equals("ملازم")) {
            x = 11;
        } else if (rank.getText().equals("مساعد أول")) {
            x = 12;
        } else if (rank.getText().equals("مساعد")) {
            x = 13;
        } else if (rank.getText().equals("رقيب أول")) {
            x = 14;
        } else if (rank.getText().equals("رقيب")) {
            x = 15;
        } else if (rank.getText().equals("عريف")) {
            x = 16;
        } else if (rank.getText().equals("طالب")) {
            x = 17;
        } else if (rank.getText().equals("صانع متدرج")) {
            x = 18;
        } else if (rank.getText().equals("وكيل عريف")) {
            x = 19;
        } else if (rank.getText().equals("محارب")) {
            x = 20;
        } else if (rank.getText().equals("جندى")) {
            x = 21;
        } else if (rank.getText().equals("مدنى")) {
            x = 22;
        } else if (rank.getText().equals("الكل")) {
            x = 23;
        }

        try {
            Connection con = dc.mkDataBase();

            rs = con.createStatement().executeQuery("SELECT limits FROM bakery.limits where rank_id ='" + x + "' ");
            while (rs.next()) {
                serviceNum = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditclientsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return serviceNum;
    }

    public List<PrintService> getServicesByName(String serviceName) {
        //Find printer service by name
        AttributeSet aset = new HashAttributeSet();
        aset.add(new PrinterName(serviceName, null));
        return Arrays.asList(PrintServiceLookup.lookupPrintServices(null, aset));
    }

    public void printServices(List<PrintService> services) {
        System.out.println("Printer Services found:");
        for (PrintService service : services) {
            System.out.println("\t" + service);
        }
    }

    public static boolean printcard(PrintService service, String R, String N, String D, String S, String T) throws Exception {
        String Rank = R;
        String Name = N;
        String Date = D;
        String numofserv = S;
        String Totalprice = T;

        if (service == null) {
            throw new Exception("Service is not valid");
        }

        Printable contePrintable = new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) graphics;
                double imagebleX = pageFormat.getImageableX();
                double imagebleY = pageFormat.getImageableY();
                UsersSingleton Admin = UsersSingleton.getInstance();

                g2d.translate(imagebleX, imagebleY);

                try {
                    int y = 20;
                    int yShift = 10;
                    int headerRectHeight = 15;

                    g2d.setFont(new Font("Helvetica",Font.BOLD, 7));
                    g2d.drawString("المجمع الطبي بكبرى القبة", 0, y);
                    y += yShift;
                    g2d.drawString("---------------------------------", 0,y);
                    y += yShift;
                    g2d.drawString("المستخدم:"+ Admin.getUserName() + "", 0, y);
                    y += headerRectHeight;
                    g2d.drawString("التاريخ:"+ Date + "", 0, y);
                    y += headerRectHeight;
                    g2d.drawString("الوقــت:"+ java.time.LocalTime.now() + "", 0, y);
                    y += yShift;
                    g2d.drawString("---------------------------------", 0, y);
                    y += headerRectHeight;
                    g2d.drawString("الرتـبة:"+ Rank + "", 0, y);
                    y += headerRectHeight;
                    g2d.drawString("الاســم:"+ Name + "", 0, y);
                    y += headerRectHeight;
                    g2d.drawString("الكمـــية:"+ numofserv + "كيس", 0, y);
                    y += headerRectHeight;
                    g2d.drawString("الاجــمالى:"+ Totalprice + "", 0, y);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                return PAGE_EXISTS;
            }
        };
        PrinterJob job = PrinterJob.getPrinterJob();

        if (service.getName().contains("BIXOLON SRP-350III")) {

            job.setPrintable(contePrintable);
            boolean ok = job.printDialog();
            int num = job.getCopies();
            if (ok) {
                if (num <= 1) {
                    job.print();
                }
            }

        }

        return true;

    }
}
