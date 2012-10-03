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
@Table(name = "Leyenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Leyenda.findAll", query = "SELECT l FROM Leyenda l"),
    @NamedQuery(name = "Leyenda.findById", query = "SELECT l FROM Leyenda l WHERE l.id = :id"),
    @NamedQuery(name = "Leyenda.findByNombre", query = "SELECT l FROM Leyenda l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Leyenda.findByTexto", query = "SELECT l FROM Leyenda l WHERE l.texto = :texto")})
public class Leyenda implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "texto")
    private String texto;
    @OneToMany(mappedBy = "leyendaId")
    private Collection<Emision> emisionCollection;

    public Leyenda() {
    }

    public Leyenda(BigDecimal id) {
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @XmlTransient
    public Collection<Emision> getEmisionCollection() {
        return emisionCollection;
    }

    public void setEmisionCollection(Collection<Emision> emisionCollection) {
        this.emisionCollection = emisionCollection;
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
        if (!(object instanceof Leyenda)) {
            return false;
        }
        Leyenda other = (Leyenda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Leyenda[ id=" + id + " ]";
    }
    
}
