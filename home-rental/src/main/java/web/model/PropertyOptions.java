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
    
    @Column(name="parking")
    private Boolean parking;
    
    @Column(name="swimming_pool")
    private Boolean swimmingPool;
    
    @Column(name="wifi")
    private Boolean wifi;
    
    @Column(name="laundry")
    private Boolean laundry;
    
    public PropertyOptions() {
        setParking(Boolean.FALSE);
        setSwimmingPool(Boolean.FALSE);
        setWifi(Boolean.FALSE);
        setLaundry(Boolean.FALSE);
    }
    
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

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(Boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getLaundry() {
        return laundry;
    }

    public void setLaundry(Boolean laundry) {
        this.laundry = laundry;
    }
    // </editor-fold>
}