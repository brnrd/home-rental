package web.mongo.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author monsieurblah
 */
@Entity
@Table(name = "UserItems")
public class UserItem implements Serializable {
    
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private char[] password;
    
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(char[] password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public char[] getPassword() {
        return password;
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
