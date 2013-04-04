package web.model;

import java.util.Calendar;

/**
 * @author Romain <ro.foncier@gmail.com>, Bernard <bernard.debecker@gmail.com>
 */
public class User {

    private String id;
    private String username;
    private String name;
    private String firstname;
    private String email;
    private String password;
    private Calendar created;
    private Boolean staff;
    
    public User() {
        
    }

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
}
