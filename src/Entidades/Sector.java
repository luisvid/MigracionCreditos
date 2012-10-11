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
@Table(name = "Sector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sector.findAll", query = "SELECT s FROM Sector s"),
    @NamedQuery(name = "Sector.findById", query = "SELECT s FROM Sector s WHERE s.id = :id"),
    @NamedQuery(name = "Sector.findByDetalle", query = "SELECT s FROM Sector s WHERE s.detalle = :detalle"),
    @NamedQuery(name = "Sector.findByNombre", query = "SELECT s FROM Sector s WHERE s.nombre = :nombre")})
public class Sector implements Serializable {
    @OneToMany(mappedBy = "sectorId")
    private Collection<BonTasa> bonTasaCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "sectorId")
    private Collection<Linea> lineaCollection;

    public Sector() {
    }

    public Sector(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Linea> getLineaCollection() {
        return lineaCollection;
    }

    public void setLineaCollection(Collection<Linea> lineaCollection) {
        this.lineaCollection = lineaCollection;
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
        if (!(object instanceof Sector)) {
            return false;
        }
        Sector other = (Sector) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Sector[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<BonTasa> getBonTasaCollection() {
        return bonTasaCollection;
    }

    public void setBonTasaCollection(Collection<BonTasa> bonTasaCollection) {
        this.bonTasaCollection = bonTasaCollection;
    }
    
}
