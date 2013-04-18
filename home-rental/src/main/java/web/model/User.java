package web.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.LocalDateTime;

/**
 * @author Romain <ro.foncier@gmail.com>, Bernard <bernard.debecker@gmail.com>
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id", unique = true)
    private String id;
    @Column(name = "username", length = 30)
    private String username;
    @Column(name = "name", length = 30)
    private String name;
    @Column(name = "firstname", length = 30)
    private String firstname;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "password", length = 40)
    private String password;
    @Column(name = "created", insertable = true, updatable = false, nullable = false)
    @org.hibernate.annotations.Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime created;
    @Column(columnDefinition = "enum('USER', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name="enabled")
    private Boolean enabled = true;

    public User() {
    }

    public User(String username, String name, String firstname, String email,
            String password, Role role) {
        this.username = username;
        this.name = name;
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public String getId() {
        return id;
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

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(Boolean e) {
        this.enabled = e;
    }
    // </editor-fold>
}