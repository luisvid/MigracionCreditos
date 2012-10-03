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
@Table(name = "QQIngresados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "QQIngresados.findAll", query = "SELECT q FROM QQIngresados q"),
    @NamedQuery(name = "QQIngresados.findById", query = "SELECT q FROM QQIngresados q WHERE q.id = :id"),
    @NamedQuery(name = "QQIngresados.findByFechaIngreso", query = "SELECT q FROM QQIngresados q WHERE q.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "QQIngresados.findByQqIngreso", query = "SELECT q FROM QQIngresados q WHERE q.qqIngreso = :qqIngreso"),
    @NamedQuery(name = "QQIngresados.findByQqIngresoStr", query = "SELECT q FROM QQIngresados q WHERE q.qqIngresoStr = :qqIngresoStr")})
public class QQIngresados implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "fechaIngreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "qqIngreso")
    private Double qqIngreso;
    @Column(name = "qqIngresoStr")
    private String qqIngresoStr;
    @JoinColumn(name = "vinedo_id", referencedColumnName = "id")
    @ManyToOne
    private Vinedo vinedoId;

    public QQIngresados() {
    }

    public QQIngresados(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Double getQqIngreso() {
        return qqIngreso;
    }

    public void setQqIngreso(Double qqIngreso) {
        this.qqIngreso = qqIngreso;
    }

    public String getQqIngresoStr() {
        return qqIngresoStr;
    }

    public void setQqIngresoStr(String qqIngresoStr) {
        this.qqIngresoStr = qqIngresoStr;
    }

    public Vinedo getVinedoId() {
        return vinedoId;
    }

    public void setVinedoId(Vinedo vinedoId) {
        this.vinedoId = vinedoId;
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
        if (!(object instanceof QQIngresados)) {
            return false;
        }
        QQIngresados other = (QQIngresados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.QQIngresados[ id=" + id + " ]";
    }
    
}
