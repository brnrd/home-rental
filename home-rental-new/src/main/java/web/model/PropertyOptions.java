package web.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Entity
@Table(name = "property_options")
public class PropertyOptions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_options_id")
    private Integer id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="target_property")
    private Property property;
    
    @Transient
    @Column(name="parking")
    private Boolean parking;
    
    @Transient
    @Column(name="swimming_pool")
    private Boolean swimmingPool;
    
    @Transient
    @Column(name="wifi")
    private Boolean wifi;
    
    @Transient
    @Column(name="laundry")
    private Boolean laundry;
    
    public PropertyOptions() {}
    
    public PropertyOptions(Property property, Boolean hasParking, Boolean hasSwim, Boolean hasWifi, Boolean hasLaundry) {
        this.property = property;
        this.parking = hasParking;
        this.swimmingPool = hasSwim;
        this.wifi = hasWifi;
        this.laundry = hasLaundry;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Integer getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Boolean hasParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean hasSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(Boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public Boolean hasWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean hasLaundry() {
        return laundry;
    }

    public void setLaundry(Boolean laundry) {
        this.laundry = laundry;
    }
    // </editor-fold>
}