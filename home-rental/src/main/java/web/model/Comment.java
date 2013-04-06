package web.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;
    
    @Column(name = "creator", length = 36)
    @OneToOne(mappedBy = "creator", cascade = CascadeType.ALL)
    private String creator;
    
    @Column(name = "target_property")
    @OneToOne(mappedBy = "target_property", cascade = CascadeType.ALL)
    private Integer property;
    
    @Column(name = "date", insertable = true, updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar cdate;
    
    @Column(name = "modified", insertable = true, updatable = true, nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar modified;
    
    @Column(name = "message", columnDefinition = "Text")
    private String message;
    
    public Comment() {}
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Long getId() {
        return id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Calendar getDate() {
        return cdate;
    }

    public void setDate(Calendar date) {
        this.cdate = date;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
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
