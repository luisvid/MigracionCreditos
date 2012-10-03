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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "DomicilioObjetoi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DomicilioObjetoi.findAll", query = "SELECT d FROM DomicilioObjetoi d"),
    @NamedQuery(name = "DomicilioObjetoi.findById", query = "SELECT d FROM DomicilioObjetoi d WHERE d.id = :id")})
public class DomicilioObjetoi implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @JoinColumn(name = "objetoi_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi objetoiId;
    @JoinColumn(name = "domicilio_id", referencedColumnName = "id")
    @ManyToOne
    private Domicilio domicilioId;

    public DomicilioObjetoi() {
    }

    public DomicilioObjetoi(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Objetoi getObjetoiId() {
        return objetoiId;
    }

    public void setObjetoiId(Objetoi objetoiId) {
        this.objetoiId = objetoiId;
    }

    public Domicilio getDomicilioId() {
        return domicilioId;
    }

    public void setDomicilioId(Domicilio domicilioId) {
        this.domicilioId = domicilioId;
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
        if (!(object instanceof DomicilioObjetoi)) {
            return false;
        }
        DomicilioObjetoi other = (DomicilioObjetoi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.DomicilioObjetoi[ id=" + id + " ]";
    }
    
}
