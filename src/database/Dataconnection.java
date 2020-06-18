package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dataconnection {

    static Connection con = null;

    public static Connection mkDataBase() {
        try {
            String unicode = "?useUnicode=yes&characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/";
            String db = "bakery";
            con = DriverManager.getConnection(url+db+unicode, "root", "P@ssw0rd@bakery");
            System.out.println("true");
            return con;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Dataconnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("false");
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Dataconnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

}
