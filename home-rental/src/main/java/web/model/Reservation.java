package web.model;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="target_user")
    private User user;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="target_property")
    private Property property;
    
    @Column(name = "date_rent_start")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime rentStart;
    
    @Column(name = "date_rent_stop")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime rentStop;
    
    @Column(name="hosts")
    private Integer hosts;
    
    @Column(name="price")
    private Integer price;
    
    @Column(name="evaluated")
    private Boolean evaluated = false;
    
    @Column(name="commented")
    private Boolean commented = false;
    
    @Column(name="note", nullable=true)
    private Integer note;
    
    public Reservation() {}
    
    public Reservation(User user, Property property, LocalDateTime start, LocalDateTime stop, Integer hosts, Integer price) {
        this.user = user;
        this.property = property;
        this.rentStart = start;
        this.rentStop = stop;
        this.hosts = hosts;
        this.price = price;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public LocalDateTime getRentStart() {
        return rentStart;
    }

    public void setRentStart(LocalDateTime rentStart) {
        this.rentStart = rentStart;
    }

    public LocalDateTime getRentStop() {
        return rentStop;
    }

    public void setRentStop(LocalDateTime rentStop) {
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