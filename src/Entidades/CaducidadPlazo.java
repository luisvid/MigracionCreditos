/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "CaducidadPlazo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaducidadPlazo.findAll", query = "SELECT c FROM CaducidadPlazo c"),
    @NamedQuery(name = "CaducidadPlazo.findById", query = "SELECT c FROM CaducidadPlazo c WHERE c.id = :id"),
    @NamedQuery(name = "CaducidadPlazo.findByAnulada", query = "SELECT c FROM CaducidadPlazo c WHERE c.anulada = :anulada"),
    @NamedQuery(name = "CaducidadPlazo.findByFecha", query = "SELECT c FROM CaducidadPlazo c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CaducidadPlazo.findByUsuariocauserK", query = "SELECT c FROM CaducidadPlazo c WHERE c.usuariocauserK = :usuariocauserK")})
public class CaducidadPlazo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "anulada")
    private short anulada;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "usuario_causerK")
    private String usuariocauserK;
    @OneToMany(mappedBy = "caducidadPlazoid")
    private Collection<Objetoi> objetoiCollection;
    @JoinColumn(name = "cuota_id", referencedColumnName = "id")
    @ManyToOne
    private Cuota cuotaId;

    public CaducidadPlazo() {
    }

    public CaducidadPlazo(BigDecimal id) {
        this.id = id;
    }

    public CaducidadPlazo(BigDecimal id, short anulada) {
        this.id = id;
        this.anulada = anulada;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public short getAnulada() {
        return anulada;
    }

    public void setAnulada(short anulada) {
        this.anulada = anulada;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuariocauserK() {
        return usuariocauserK;
    }

    public void setUsuariocauserK(String usuariocauserK) {
        this.usuariocauserK = usuariocauserK;
    }

    @XmlTransient
    public Collection<Objetoi> getObjetoiCollection() {
        return objetoiCollection;
    }

    public void setObjetoiCollection(Collection<Objetoi> objetoiCollection) {
        this.objetoiCollection = objetoiCollection;
    }

    public Cuota getCuotaId() {
        return cuotaId;
    }

    public void setCuotaId(Cuota cuotaId) {
        this.cuotaId = cuotaId;
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
        if (!(object instanceof CaducidadPlazo)) {
            return false;
        }
        CaducidadPlazo other = (CaducidadPlazo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CaducidadPlazo[ id=" + id + " ]";
    }
    
}
