package business;

/**
 * Records basic user information.
 */
public class User {
    private String username;
    private String password;

    //Getters
    /**
     * Gets username of User object instance.
     * 
     * @return User Username field of instance.
     * 
     * @author Cal Woods
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets password field of User instance.
     * 
     * @return User password field of instance object.
     * 
     * @author Cal Woods
     */
    public String getPassword() {
        return this.password;
    }
}
