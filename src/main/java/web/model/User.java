package web.model;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
public class User {

    private String id;
    private String name;
    private String fname;
    private String address;
    private String email;
    private String phone;
    private int region;

    public User() {
    }

    public String getId() {
        return id.toString();
    }

    private void setID(String user_id) {
        id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String newFName) {
        fname = newFName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String newAddress) {
        address = newAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) {
        email = newEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String newPhone) {
        phone = newPhone;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int newRegion) {
        region = newRegion;
    }

    @Override
    public String toString() {
        return "{\"id\": \"+id+\", \"name\": \"+name+\", \"fname\": \"+fname+\","
                + " \"address\": \"+address+\", \"email\": \"+email+\","
                + " \"phone\": \"+phone+\", \"region\": " + region + "}";
    }
}