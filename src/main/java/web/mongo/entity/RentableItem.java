package web.mongo.entity;

import web.model.Countries;
import web.model.Types;

/**
 *
 * @author monsieurblah
 */
public class RentableItem {
    
    private String id;
    private Types type;
    private Countries country;
    private int postCode;
    private String address;
    private UserItem owner;
    
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

    public UserItem getOwner() {
        return owner;
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

    public void setOwner(UserItem owner) {
        this.owner = owner;
    }
    // </editor-fold>
    
}
