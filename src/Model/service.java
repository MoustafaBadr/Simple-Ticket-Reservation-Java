package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class service {

    private IntegerProperty id;
    private StringProperty date;
    private StringProperty nomofservice;
    private StringProperty totalprice;
    private StringProperty fkclientid;
   
    

    public Integer getId() {
        return id.get();
    }

    public String getDate() {
        return date.get();
    }

    public String getNomofservice() {
        return nomofservice.get();
    }

    public String getTotalprice() {
        return totalprice.get();
    }

    public String getFkclientid() {
        return fkclientid.get();
    }

    

    public void setId(int value) {
        id.set(value);
    }

    public void setDate(String value) {
        date.set(value);
    }

    public void setNomofservice(String value) {
        nomofservice.set(value);
    }

    public void setTotalprice(String value) {
        totalprice.set(value);
    }

    public void setFkclientid(String value) {
        fkclientid.set(value);
    }

    

    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty nomofserviceProperty() {
        return nomofservice;
    }

    public StringProperty totalpriceProperty() {
        return totalprice;
    }

    public StringProperty fkclientidProperty() {
        return fkclientid;
    }

   

    public service(Integer id, String date, String nomofservice, String totalprice, String fkclientid) {

        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.nomofservice = new SimpleStringProperty(nomofservice);
        this.totalprice = new SimpleStringProperty(totalprice);
        this.fkclientid = new SimpleStringProperty(fkclientid);
       
    }

}
