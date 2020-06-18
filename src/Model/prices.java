
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class prices {

    private StringProperty price;
    private IntegerProperty id;
   

    public String getPrice() {
        return price.get();
    }
    public Integer getId() {
        return id.get();
    }

    
    // setter
    public void setPrice(String value) {
        price.set(value);
    }
    public void setId(int value) {
        id.set(value);
    }
    
    //property values
    
    public StringProperty priceProperty(){
    return price;
    }
    public IntegerProperty idProperty(){
    return id;
    }
   
    
    
    
      public prices(String price,Integer id) {
          this.price = new SimpleStringProperty(price);
          this.id = new SimpleIntegerProperty(id);
    }

}
