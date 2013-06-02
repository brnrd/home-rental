package web.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Bernard <bernard.debecker@gmail.com>, R. FONCIER <ro.foncier@gmail.com>
 */

@Entity
@Table(name = "evaluation")
public class Evaluation implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evaluation_id")
    private Integer id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="target_property")
    private Property property;
    
    @Column(name="cleanliness")
    private Integer cleanliness;
    
    @Column(name="confort")
    private Integer confort;
    
    @Column(name="qa_price")
    private Integer qaPrice;
    
    public Evaluation() {}
    
    public Evaluation(Property property, Integer clean, Integer confort, Integer qa_price) {
        this.property = property;
        this.cleanliness = clean;
        this.confort = confort;
        this.qaPrice = qa_price;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Integer getId() {
        return id;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
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
    // </editor-fold>
}