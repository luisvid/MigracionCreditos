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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "Requisito")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Requisito.findAll", query = "SELECT r FROM Requisito r"),
    @NamedQuery(name = "Requisito.findById", query = "SELECT r FROM Requisito r WHERE r.id = :id"),
    @NamedQuery(name = "Requisito.findByDescripcion", query = "SELECT r FROM Requisito r WHERE r.descripcion = :descripcion"),
    @NamedQuery(name = "Requisito.findByNombre", query = "SELECT r FROM Requisito r WHERE r.nombre = :nombre"),
    @NamedQuery(name = "Requisito.findByObligatorio", query = "SELECT r FROM Requisito r WHERE r.obligatorio = :obligatorio"),
    @NamedQuery(name = "Requisito.findByTipo", query = "SELECT r FROM Requisito r WHERE r.tipo = :tipo")})
public class Requisito implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "obligatorio")
    private Short obligatorio;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "linea_id", referencedColumnName = "id")
    @ManyToOne
    private Linea lineaId;
    @OneToMany(mappedBy = "requisitoId")
    private Collection<Desembolso> desembolsoCollection;

    public Requisito() {
    }

    public Requisito(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Short getObligatorio() {
        return obligatorio;
    }

    public void setObligatorio(Short obligatorio) {
        this.obligatorio = obligatorio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Linea getLineaId() {
        return lineaId;
    }

    public void setLineaId(Linea lineaId) {
        this.lineaId = lineaId;
    }

    @XmlTransient
    public Collection<Desembolso> getDesembolsoCollection() {
        return desembolsoCollection;
    }

    public void setDesembolsoCollection(Collection<Desembolso> desembolsoCollection) {
        this.desembolsoCollection = desembolsoCollection;
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
        if (!(object instanceof Requisito)) {
            return false;
        }
        Requisito other = (Requisito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Requisito[ id=" + id + " ]";
    }
    
}
