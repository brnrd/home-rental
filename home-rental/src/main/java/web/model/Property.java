package web.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 * @author Bernard <bernard.debecker@gmail.com>,
 * @author Romain <ro.foncier@gmail.com>
 */
@Entity
@Table(name = "property")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "property_id")
    private Long id;
    
    @Column(name = "owner", length = 36)
    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL)
    private String owner;
    
    @Column(name = "added", insertable = true, updatable = false, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar added;
    
    @Column(name = "modified", insertable = true, updatable = true, nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modified;
    
    @Column(name = "short_desc", length = 255)
    private String shortDesc;
    
    @Column(name = "long_desc", columnDefinition = "Text")
    private String longDesc;
    
    @Column(name = "price")
    private Integer price;
    
    @Column(name = "type")
    private Type type;
    
    @Column(name = "rooms")
    private Integer rooms;
    
    @Column(name = "country", length = 45)
    private String country;
    
    @Column(name = "city", length = 45)
    private String city;
    
    @Column(name = "address", length = 255)
    private String address;
    
    @Column(name = "coordinates", length = 45, nullable = true)
    private String coordinates;
    
    @Column(name = "note", nullable = true)
    private Integer note;
    
    @Column(name = "rent_period_start")
    @Temporal(TemporalType.DATE)
    private Calendar rentPeriodStart;
    
    @Column(name = "rent_period_stop")
    @Temporal(TemporalType.DATE)
    private Calendar rentPeriodStop;

    public Property() {}

    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Calendar getAdded() {
        return added;
    }

    public void setAdded(Calendar added) {
        this.added = added;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer newPrice) {
        this.price = newPrice;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public Calendar getRentPeriodStart() {
        return rentPeriodStart;
    }

    public void setRentPeriodStart(Calendar rentPeriodStart) {
        this.rentPeriodStart = rentPeriodStart;
    }

    public Calendar getRentPeriodStop() {
        return rentPeriodStop;
    }

    public void setRentPeriodStop(Calendar rentPeriodStop) {
        this.rentPeriodStop = rentPeriodStop;
    }
    // </editor-fold>
}
