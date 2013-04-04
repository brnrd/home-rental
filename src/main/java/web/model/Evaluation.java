package web.model;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
class Evaluation {
    
    private String id;
    private String property;
    private Integer cleanliness;
    private Integer confort;
    private Integer qaPrice;
    
    public Evaluation() {
        
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

    public Integer getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(Integer cleanliness) {
        this.cleanliness = cleanliness;
    }

    public Integer getConfort() {
        return confort;
    }

    public void setConfort(Integer confort) {
        this.confort = confort;
    }

    public Integer getQaPrice() {
        return qaPrice;
    }

    public void setQaPrice(Integer qaPrice) {
        this.qaPrice = qaPrice;
    }
    
}
