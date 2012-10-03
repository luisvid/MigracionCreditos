/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "Tipomov")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipomov.findAll", query = "SELECT t FROM Tipomov t"),
    @NamedQuery(name = "Tipomov.findById", query = "SELECT t FROM Tipomov t WHERE t.id = :id"),
    @NamedQuery(name = "Tipomov.findByAbreviatura", query = "SELECT t FROM Tipomov t WHERE t.abreviatura = :abreviatura"),
    @NamedQuery(name = "Tipomov.findByDetalle", query = "SELECT t FROM Tipomov t WHERE t.detalle = :detalle"),
    @NamedQuery(name = "Tipomov.findByFuncion", query = "SELECT t FROM Tipomov t WHERE t.funcion = :funcion"),
    @NamedQuery(name = "Tipomov.findByManual", query = "SELECT t FROM Tipomov t WHERE t.manual = :manual")})
public class Tipomov implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "abreviatura")
    private String abreviatura;
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "funcion")
    private BigInteger funcion;
    @Column(name = "manual")
    private BigInteger manual;
    @OneToMany(mappedBy = "tipomovId")
    private Collection<Ctacte> ctacteCollection;

    public Tipomov() {
    }

    public Tipomov(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public BigInteger getFuncion() {
        return funcion;
    }

    public void setFuncion(BigInteger funcion) {
        this.funcion = funcion;
    }

    public BigInteger getManual() {
        return manual;
    }

    public void setManual(BigInteger manual) {
        this.manual = manual;
    }

    @XmlTransient
    public Collection<Ctacte> getCtacteCollection() {
        return ctacteCollection;
    }

    public void setCtacteCollection(Collection<Ctacte> ctacteCollection) {
        this.ctacteCollection = ctacteCollection;
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
        if (!(object instanceof Tipomov)) {
            return false;
        }
        Tipomov other = (Tipomov) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Tipomov[ id=" + id + " ]";
    }
    
}
