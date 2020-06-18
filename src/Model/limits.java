package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class limits {

    private StringProperty rank;
    private IntegerProperty limit;
    private IntegerProperty id;

    public Integer getLimit() {
        return limit.get();
    }
     public String getRank() {
        return rank.get();
    }

    public Integer getId() {
        return id.get();
    }

    // setter
    public void setLimit(int value) {
        limit.set(value);
    }
  public void setRank(String value) {
        rank.set(value);
    }
    public void setId(int value) {
        id.set(value);
    }

    //property values
    public IntegerProperty limitProperty() {
        return limit;
    }

    public StringProperty rankProperty() {
        return rank;
    }
    public IntegerProperty idProperty() {
        return id;
    }

    public limits(Integer limit, Integer id,String rank) {
        this.limit = new SimpleIntegerProperty(limit);
        this.id = new SimpleIntegerProperty(id);
        this.rank = new SimpleStringProperty(rank);
    }
}
