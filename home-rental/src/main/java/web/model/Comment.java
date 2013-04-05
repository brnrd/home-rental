package web.model;

import java.util.Calendar;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class Comment {
    
    private String id;
    private String creator;
    private String property;
    private Calendar date;
    private Calendar modified;
    private String message;
    
    public Comment() {
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Calendar getModified() {
        return modified;
    }

    public void setModified(Calendar modified) {
        this.modified = modified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    // </editor-fold>
}
