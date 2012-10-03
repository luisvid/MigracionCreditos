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
@Table(name = "BonDetalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonDetalle.findAll", query = "SELECT b FROM BonDetalle b"),
    @NamedQuery(name = "BonDetalle.findById", query = "SELECT b FROM BonDetalle b WHERE b.id = :id"),
    @NamedQuery(name = "BonDetalle.findByEsEfectiva", query = "SELECT b FROM BonDetalle b WHERE b.esEfectiva = :esEfectiva"),
    @NamedQuery(name = "BonDetalle.findByFechaBonificada", query = "SELECT b FROM BonDetalle b WHERE b.fechaBonificada = :fechaBonificada"),
    @NamedQuery(name = "BonDetalle.findByMonto", query = "SELECT b FROM BonDetalle b WHERE b.monto = :monto")})
public class BonDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "esEfectiva")
    private Short esEfectiva;
    @Column(name = "fechaBonificada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBonificada;
    @Column(name = "monto")
    private Double monto;
    @JoinColumn(name = "cuota_id", referencedColumnName = "id")
    @ManyToOne
    private Cuota cuotaId;
    @JoinColumn(name = "bonificacion_id", referencedColumnName = "id")
    @ManyToOne
    private Bonificacion bonificacionId;

    public BonDetalle() {
    }

    public BonDetalle(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Short getEsEfectiva() {
        return esEfectiva;
    }

    public void setEsEfectiva(Short esEfectiva) {
        this.esEfectiva = esEfectiva;
    }

    public Date getFechaBonificada() {
        return fechaBonificada;
    }

    public void setFechaBonificada(Date fechaBonificada) {
        this.fechaBonificada = fechaBonificada;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Cuota getCuotaId() {
        return cuotaId;
    }

    public void setCuotaId(Cuota cuotaId) {
        this.cuotaId = cuotaId;
    }

    public Bonificacion getBonificacionId() {
        return bonificacionId;
    }

    public void setBonificacionId(Bonificacion bonificacionId) {
        this.bonificacionId = bonificacionId;
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
        if (!(object instanceof BonDetalle)) {
            return false;
        }
        BonDetalle other = (BonDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BonDetalle[ id=" + id + " ]";
    }
    
}
