package web.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="creator")
    private User creator;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="target_property")
    private Property property;
    
    @Column(name = "date", insertable = true, updatable = false, nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime date;
    
    @Column(name = "modified", insertable = true, updatable = true, nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime modified;
    
    @Column(name = "message", columnDefinition = "Text")
    private String message;
    
    public Comment() {}
    
    public Comment(User creator, Property property, String message) {
        this.creator = creator;
        this.property = property;
        this.message = message;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Integer getId() {
        return id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // </editor-fold>
}