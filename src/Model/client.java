package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class client {

    private StringProperty name;
    private StringProperty nationalcode;
    private StringProperty phone;
    private StringProperty mailid;
    private StringProperty rank;
    private IntegerProperty id;

    public String getName() {
        return name.get();
    }

    public String getNationalcode() {
        return nationalcode.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getMailid() {
        return mailid.get();
    }

    public String getRank() {
        return rank.get();
    }

    public Integer getId() {
        return id.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public void setNationalcode(String value) {
        nationalcode.set(value);
    }

    public void setPhone(String value) {
        phone.set(value);
    }

    public void setMailid(String value) {
        mailid.set(value);
    }

    public void setRank(String value) {
        rank.set(value);
    }

    public void setId(int value) {
        id.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty nationalcodeProperty() {
        return nationalcode;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty mailidProperty() {
        return mailid;
    }

    public StringProperty rankProperty() {
        return rank;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public client(String nationalcode, String mailid, String phone, String rank, String name, Integer client_id) {
        this.name = new SimpleStringProperty(name);
        this.nationalcode = new SimpleStringProperty(nationalcode);
        this.phone = new SimpleStringProperty(phone);
        this.mailid = new SimpleStringProperty(mailid);
        this.rank = new SimpleStringProperty(rank);
        this.id = new SimpleIntegerProperty(client_id);
    }

}
