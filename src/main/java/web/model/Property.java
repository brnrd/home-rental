package web.model;

import java.util.Calendar;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class Property {
    
    private String id;
    private String owner;
    private Calendar added;
    private Calendar modified;
    private String shortDesc;
    private String longDesc;
    private Type type;
    private Integer rooms;
    private String country;
    private String city;
    private String address;
    private String coordinates;
    private Evaluation note;
    private Calendar rentPeriodStart;
    private Calendar rentPeriodStop;
    
    public Property() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Evaluation getNote() {
        return note;
    }

    public void setNote(Evaluation note) {
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
    
}
