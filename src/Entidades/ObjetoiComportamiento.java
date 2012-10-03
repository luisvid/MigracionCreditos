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
@Table(name = "ObjetoiComportamiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetoiComportamiento.findAll", query = "SELECT o FROM ObjetoiComportamiento o"),
    @NamedQuery(name = "ObjetoiComportamiento.findById", query = "SELECT o FROM ObjetoiComportamiento o WHERE o.id = :id"),
    @NamedQuery(name = "ObjetoiComportamiento.findByComportamientoPago", query = "SELECT o FROM ObjetoiComportamiento o WHERE o.comportamientoPago = :comportamientoPago"),
    @NamedQuery(name = "ObjetoiComportamiento.findByFecha", query = "SELECT o FROM ObjetoiComportamiento o WHERE o.fecha = :fecha"),
    @NamedQuery(name = "ObjetoiComportamiento.findByFechaHasta", query = "SELECT o FROM ObjetoiComportamiento o WHERE o.fechaHasta = :fechaHasta"),
    @NamedQuery(name = "ObjetoiComportamiento.findByObservaciones", query = "SELECT o FROM ObjetoiComportamiento o WHERE o.observaciones = :observaciones")})
public class ObjetoiComportamiento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "comportamientoPago")
    private String comportamientoPago;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "fechaHasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "objetoi_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi objetoiId;

    public ObjetoiComportamiento() {
    }

    public ObjetoiComportamiento(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getComportamientoPago() {
        return comportamientoPago;
    }

    public void setComportamientoPago(String comportamientoPago) {
        this.comportamientoPago = comportamientoPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
        if (!(object instanceof ObjetoiComportamiento)) {
            return false;
        }
        ObjetoiComportamiento other = (ObjetoiComportamiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ObjetoiComportamiento[ id=" + id + " ]";
    }
    
}
