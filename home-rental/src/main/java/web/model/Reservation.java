package web.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private Long id;
    
    @Column(name = "target_user", length = 36)
    @OneToOne(mappedBy = "target_user", cascade = CascadeType.ALL)
    private String user;
    
    @Column(name = "target_property")
    @OneToOne(mappedBy = "target_property", cascade = CascadeType.ALL)
    private Integer property;
    
    @Column(name = "rent_start")
    @Temporal(TemporalType.DATE)
    private Calendar rentStart;
    
    @Column(name = "rent_stop")
    @Temporal(TemporalType.DATE)
    private Calendar rentStop;
    
    @Column(name="hosts")
    private Integer hosts;
    
    @Column(name="price")
    private Integer price;
    
    @Transient
    @Column(name="evaluated", columnDefinition="boolean default false")
    private Boolean evaluated;
    
    @Transient
    @Column(name="commented", columnDefinition="boolean default false")
    private Boolean commented;
    
    @Column(name="note", nullable=true)
    private Integer note;
    
    public Reservation() {
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Calendar getRentStart() {
        return rentStart;
    }

    public void setRentStart(Calendar rentStart) {
        this.rentStart = rentStart;
    }

    public Calendar getRentStop() {
        return rentStop;
    }

    public void setRentStop(Calendar rentStop) {
        this.rentStop = rentStop;
    }

    public Integer getHosts() {
        return hosts;
    }

    public void setHosts(Integer hosts) {
        this.hosts = hosts;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean isEvaluated() {
        return evaluated;
    }

    public void setEvaluated(Boolean evaluated) {
        this.evaluated = evaluated;
    }

    public Boolean isCommented() {
        return commented;
    }

    public void setCommented(Boolean commented) {
        this.commented = commented;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }
    // </editor-fold>
}
