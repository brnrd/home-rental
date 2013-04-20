package web.model;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class NewProperty {
    
    private Property property;
    private PropertyOptions options;
    private String rentStart;
    private String rentStop;
    
    public NewProperty() {}

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public PropertyOptions getOptions() {
        return options;
    }

    public void setOptions(PropertyOptions options) {
        this.options = options;
    }

    public String getRentStart() {
        return rentStart;
    }

    public void setRentStart(String rentStart) {
        this.rentStart = rentStart;
    }

    public String getRentStop() {
        return rentStop;
    }

    public void setRentStop(String rentStop) {
        this.rentStop = rentStop;
    }
    
    
    
    

}
