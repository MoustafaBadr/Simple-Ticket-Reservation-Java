package Model;

/**
 *
 * @author Mostafa Badr
 */
public class UsersSingleton {

    private static final UsersSingleton single_instance = new UsersSingleton();

    private String userName;

    private UsersSingleton() {
   
    }

    public static UsersSingleton getInstance() {
        return single_instance; 
    }
    
     public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
