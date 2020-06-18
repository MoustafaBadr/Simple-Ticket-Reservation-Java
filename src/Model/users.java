package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class users {

    private StringProperty name;
    private StringProperty pass;
    private StringProperty type;
    private StringProperty desc;
    private IntegerProperty id;

    public String getName() {
        return name.get();
    }

    public String getPass() {
        return pass.get();
    }

    public String getType() {
        return type.get();
    }

    public String getDesc() {
        return desc.get();
    }

    public Integer getId() {
        return id.get();
    }

    // setter
    public void setName(String value) {
        name.set(value);
    }

    public void setPass(String value) {
        pass.set(value);
    }

    public void setType(String value) {
        type.set(value);
    }

    public void setDesc(String value) {
        desc.set(value);
    }

    public void setId(int value) {
        id.set(value);
    }
    //property values

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty passProperty() {
        return pass;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public StringProperty descProperty() {
        return desc;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public users(String name, String pass, String type, String desc, Integer id) {
        this.name = new SimpleStringProperty(name);
        this.pass = new SimpleStringProperty(pass);
        this.type = new SimpleStringProperty(type);
        this.desc = new SimpleStringProperty(desc);
        this.id = new SimpleIntegerProperty(id);
    }

}
