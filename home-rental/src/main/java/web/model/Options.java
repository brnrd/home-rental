package web.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Entity
@Table(name = "property_options")
public class Options implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "property_options_id")
    private Long id;
    
    @Column(name = "target_property")
    @OneToOne(mappedBy = "target_property", cascade = CascadeType.ALL)
    private Integer property;
    
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
    
    public Options() {}
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Long getId() {
        return id;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
        this.property = property;
    }

    public Boolean isParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean hazSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(Boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public Boolean hazWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean hazLaundry() {
        return laundry;
    }

    public void setLaundry(Boolean laundry) {
        this.laundry = laundry;
    }
    // </editor-fold>
}
