package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ranks {

    private StringProperty name;
    private IntegerProperty id;
   

    public String getName() {
        return name.get();
    }
    public Integer getId() {
        return id.get();
    }

    
    // setter
    public void setName(String value) {
        name.set(value);
    }
    public void setId(int value) {
        id.set(value);
    }
    
    //property values
    
    public StringProperty nameProperty(){
    return name;
    }
    public IntegerProperty idProperty(){
    return id;
    }
   
    
    
    
      public ranks(String name,Integer id) {
          this.name = new SimpleStringProperty(name);
          this.id = new SimpleIntegerProperty(id);
    }
}
