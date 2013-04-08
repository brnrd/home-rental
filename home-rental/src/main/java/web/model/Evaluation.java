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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "evaluation_id")
    private Long id;
    
    @Column(name = "target_property")
    @OneToOne(mappedBy = "target_property", cascade = CascadeType.ALL)
    private Integer property;
    
    @Column(name="cleanliness")
    private Integer cleanliness;
    
    @Column(name="confort")
    private Integer confort;
    
    @Column(name="qa_price")
    private Integer qaPrice;
    
    public Evaluation() {}
    
    // <editor-fold defaultstate="collapsed" desc="Getter/setter">
    public Long getId() {
        return id;
    }

    public Integer getProperty() {
        return property;
    }

    public void setProperty(Integer property) {
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
