package web.model;

import java.util.Calendar;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class Reservation {
    
    private String id;
    private String user;
    private String property;
    private Calendar rentStart;
    private Calendar rentStop;
    private Integer hosts;
    private Integer price;
    private Boolean evaluated;
    private Boolean commented;
    private Integer note;
    
    public Reservation() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
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
    
}
