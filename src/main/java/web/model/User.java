package web.model;

import java.util.UUID;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
public class User {

    UUID id;
    String name;
    String fname;
    String address;
    String email;
    String phone;
    
    public String getId() {
        return id.toString();
    }

    private void setID() {
        id = UUID.randomUUID();
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
}