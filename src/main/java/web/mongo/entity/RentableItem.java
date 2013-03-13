package web.mongo.entity;

import web.model.Countries;
import web.model.Types;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class RentableItem {
    
    private String id;
    private Types type;
    private Countries country;
    private int postCode;
    private String address;
    private String owner;
    private int price;
    private int capacity;
    
    public RentableItem() {}

    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public String getId() {
        return id;
    }
    
    public Types getType() {
        return type;
    }

    public Countries getCountry() {
        return country;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getAddress() {
        return address;
    }

    public String getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setType(Types type) {
        this.type = type;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    // </editor-fold>
    
    @Override
    public String toString() {
        return "RentableItem [id=" + id + ", type=" + type + ", country="
                + country + ", postCode=" + postCode + ", address=" + address
                + ", owner=" + owner + ", price=" + price + ", capacity="
                + capacity;
    }
}
