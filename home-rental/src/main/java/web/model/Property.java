package web.model;

import com.javadocmd.simplelatlng.LatLng;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

/**
 * @author Bernard <bernard.debecker@gmail.com>,  R. FONCIER  <ro.foncier@gmail.com>
 */
@Entity
@Table(name = "property")
public class Property implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Integer id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="owner")
    private User owner;
    @Column(name="title", length=120)
    private String title;
    @Column(name = "added", insertable = true, updatable = false, nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime added;
    @Column(name = "modified", insertable = true, updatable = true, nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime modified;
    @Column(name = "short_desc", length = 255)
    private String shortDesc;
    @Column(name = "long_desc", columnDefinition = "Text")
    private String longDesc;
    @Column(name = "price")
    private Integer price;
    @Column(columnDefinition = "enum('FLAT', 'HOUSE', 'LOFT')")
    @Enumerated(EnumType.STRING)
    private PropertyType type;
    @Column(name = "rooms")
    private Integer rooms;
    @Column(name = "country", length = 45)
    private String country;
    @Column(name = "city", length = 45)
    private String city;
    @Column(name = "address", length = 255)
    private String address;
    @Column(name="latitude")
    private double latitude;
    @Column(name="longitude")
    private double longitude;
    @Column(name = "note", nullable = true)
    private Integer note;
    @Column(name = "rent_period_start")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime rentPeriodStart;
    @Column(name = "rent_period_stop")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime rentPeriodStop;

    public Property() {}
    
    public Property(User owner, String title, String sDesc, String lDesc, Integer price, PropertyType type, Integer rooms, String country, String city, String address, LocalDateTime start, LocalDateTime stop) {
        this.owner = owner;
        this.title = title;
        this.shortDesc = sDesc;
        this.longDesc = lDesc;
        this.price = price;
        this.type = type;
        this.rooms = rooms;
        this.country = country;
        this.city = city;
        this.address = address;
        this.rentPeriodStart = start;
        this.rentPeriodStop = stop;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Integer getId() {
        return id;
    }
    
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public LocalDateTime getAdded() {
        return added;
    }

    public void setAdded(LocalDateTime added) {
        this.added = added;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return this.longDesc;
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

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer nbRooms) {
        this.rooms = nbRooms;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String newCountry) {
        this.country = newCountry;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String newCity) {
        this.city = newCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double lat) {
        this.latitude = lat;
    }
    
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longi) {
        this.longitude = longi;
    }
    
    public LatLng getCoordinates() {
        if (this.latitude != 0 && this.longitude != 0) {
            return new LatLng(this.latitude, this.longitude);
        }
        return null;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer newNote) {
        this.note = newNote;
    }

    public LocalDateTime getRentPeriodStart() {
        return rentPeriodStart;
    }

    public void setRentPeriodStart(LocalDateTime rentPeriodStart) {
        this.rentPeriodStart = rentPeriodStart;
    }

    public LocalDateTime getRentPeriodStop() {
        return rentPeriodStop;
    }

    public void setRentPeriodStop(LocalDateTime rentPeriodStop) {
        this.rentPeriodStop = rentPeriodStop;
    }
    // </editor-fold>
}
