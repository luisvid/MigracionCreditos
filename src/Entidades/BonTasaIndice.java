/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisv
 */
@Entity
@Table(name = "BonTasaIndice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonTasaIndice.findAll", query = "SELECT b FROM BonTasaIndice b"),
    @NamedQuery(name = "BonTasaIndice.findById", query = "SELECT b FROM BonTasaIndice b WHERE b.id = :id"),
    @NamedQuery(name = "BonTasaIndice.findByCalculaDias", query = "SELECT b FROM BonTasaIndice b WHERE b.calculaDias = :calculaDias"),
    @NamedQuery(name = "BonTasaIndice.findByCalculaMes", query = "SELECT b FROM BonTasaIndice b WHERE b.calculaMes = :calculaMes")})
public class BonTasaIndice implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "calculaDias")
    private Integer calculaDias;
    @Column(name = "calculaMes")
    private Integer calculaMes;

    public BonTasaIndice() {
    }

    public BonTasaIndice(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getCalculaDias() {
        return calculaDias;
    }

    public void setCalculaDias(Integer calculaDias) {
        this.calculaDias = calculaDias;
    }

    public Integer getCalculaMes() {
        return calculaMes;
    }

    public void setCalculaMes(Integer calculaMes) {
        this.calculaMes = calculaMes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BonTasaIndice)) {
            return false;
        }
        BonTasaIndice other = (BonTasaIndice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BonTasaIndice[ id=" + id + " ]";
    }
    
}
