package web.mongo.entity;

import java.util.Date;

/**
 *
 * @author monsieurblah
 */
public class ReservationItem {
    
    private String rentableId;
    private String userId;
    private Date date;
    private String comment;
    
    public ReservationItem() {}
    
    public ReservationItem(String rentableId,String userId, Date date) {
        this.rentableId = rentableId;
        this.userId = userId;
        this.date = date;
    }

    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public String getRentableItem() {
        return rentableId;
    }

    public String getUserItem() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public void setRentableItem(String rentableItem) {
        this.rentableId = rentableItem;
    }

    public void setUserItem(String userItem) {
        this.userId = userItem;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    // </editor-fold>
    
    @Override
    public String toString() {
        return "ReservationItem [rentableId=" + rentableId + ", userId ="
                + userId + ", date=" + date + ", comment=" + comment;   
    }
    
    
}
