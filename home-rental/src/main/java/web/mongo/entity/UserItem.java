package web.mongo.entity;

import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER
 * <ro.foncier@gmail.com>
 */
public class UserItem implements Serializable {

    private String id;
    private String username;
    private String name;
    private String firstname;
    private String email;
    private String password;
    private Calendar created;
    private Boolean staff;

    public UserItem() {
    }

    public UserItem(String firstName, String lastName, String email) {
        this.firstname = firstName;
        this.name = lastName;
        this.email = email;
    }

    public UserItem(String firstName, String lastName, String email, char[] password, boolean isStaff) {
        this.firstname = firstName;
        this.name = lastName;
        this.email = email;
        this.password = new String(password); // Hash or Spring authentification
        this.staff = isStaff;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Boolean isStaff() {
        return staff;
    }

    public void setStaff(Boolean staff) {
        this.staff = staff;
    }
    // </editor-fold>

    /**
     * toString method with omitted password
     *
     * @return a String with the UserItem info
     */
    @Override
    public String toString() {
        return "UserItem [id=" + id + ", username=" + username + ", name =" + name + ", firstname"
                + "=" + firstname + ", email=" + email + ", created=" + created;
    }
}
