package web.model;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class Options {

    private String id;
    private String property;
    private Boolean parking;
    private Boolean swimmingPool;
    private Boolean wifi;
    private Boolean laundry;
    
    public Options() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Boolean isParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean isSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(Boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public Boolean isWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean isLaundry() {
        return laundry;
    }

    public void setLaundry(Boolean laundry) {
        this.laundry = laundry;
    }
    
}
