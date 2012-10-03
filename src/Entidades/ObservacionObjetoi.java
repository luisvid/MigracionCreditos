/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "ObservacionObjetoi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObservacionObjetoi.findAll", query = "SELECT o FROM ObservacionObjetoi o"),
    @NamedQuery(name = "ObservacionObjetoi.findById", query = "SELECT o FROM ObservacionObjetoi o WHERE o.id = :id"),
    @NamedQuery(name = "ObservacionObjetoi.findByFecha", query = "SELECT o FROM ObservacionObjetoi o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "ObservacionObjetoi.findByObservacion", query = "SELECT o FROM ObservacionObjetoi o WHERE o.observacion = :observacion")})
public class ObservacionObjetoi implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "observacion")
    private String observacion;
    @JoinColumn(name = "objetoi_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi objetoiId;

    public ObservacionObjetoi() {
    }

    public ObservacionObjetoi(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Objetoi getObjetoiId() {
        return objetoiId;
    }

    public void setObjetoiId(Objetoi objetoiId) {
        this.objetoiId = objetoiId;
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
        if (!(object instanceof ObservacionObjetoi)) {
            return false;
        }
        ObservacionObjetoi other = (ObservacionObjetoi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ObservacionObjetoi[ id=" + id + " ]";
    }
    
}
