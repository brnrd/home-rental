package web.mongo.entity;

import java.io.Serializable;
import web.model.Countries;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */

public class UserItem implements Serializable {
    
    private String id;
    private String firstName;
    private String lastName;
    private Countries country;
    private String email;
    private char[] password;
    private boolean isAdmin;
    
    public UserItem() {}
    
    public UserItem(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    
    public UserItem(String firstName, String lastName, String email, char[] password, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Countries getCountry() {
        return country;
    }
    
    

    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return password;
    }
    
    public boolean getIsAdmin() {
        return isAdmin;
    }
    // </editor-fold>
    
    /**
     * toString method with omitted password
     * @return a String with the UserItem info
     */
    @Override
    public String toString() {
        return "UserItem [id=" + id + ", firstName =" + firstName + ", lastname"
                + "=" + lastName + ", email=" + email;
    }
    
}
