/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "SubTipoLinea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubTipoLinea.findAll", query = "SELECT s FROM SubTipoLinea s"),
    @NamedQuery(name = "SubTipoLinea.findById", query = "SELECT s FROM SubTipoLinea s WHERE s.id = :id"),
    @NamedQuery(name = "SubTipoLinea.findByNombre", query = "SELECT s FROM SubTipoLinea s WHERE s.nombre = :nombre")})
public class SubTipoLinea implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "subTipoLineaid")
    private Collection<Objetoi> objetoiCollection;

    public SubTipoLinea() {
    }

    public SubTipoLinea(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Objetoi> getObjetoiCollection() {
        return objetoiCollection;
    }

    public void setObjetoiCollection(Collection<Objetoi> objetoiCollection) {
        this.objetoiCollection = objetoiCollection;
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
        if (!(object instanceof SubTipoLinea)) {
            return false;
        }
        SubTipoLinea other = (SubTipoLinea) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.SubTipoLinea[ id=" + id + " ]";
    }
    
}
