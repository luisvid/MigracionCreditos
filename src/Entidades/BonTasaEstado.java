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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisv
 */
@Entity
@Table(name = "BonTasaEstado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BonTasaEstado.findAll", query = "SELECT b FROM BonTasaEstado b"),
    @NamedQuery(name = "BonTasaEstado.findById", query = "SELECT b FROM BonTasaEstado b WHERE b.id = :id"),
    @NamedQuery(name = "BonTasaEstado.findByAsesor", query = "SELECT b FROM BonTasaEstado b WHERE b.asesor = :asesor"),
    @NamedQuery(name = "BonTasaEstado.findByFechaCambio", query = "SELECT b FROM BonTasaEstado b WHERE b.fechaCambio = :fechaCambio"),
    @NamedQuery(name = "BonTasaEstado.findByTipoEstadoBonTasa", query = "SELECT b FROM BonTasaEstado b WHERE b.tipoEstadoBonTasa = :tipoEstadoBonTasa")})
public class BonTasaEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "asesor")
    private String asesor;
    @Column(name = "fechaCambio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCambio;
    @Column(name = "tipoEstadoBonTasa")
    private String tipoEstadoBonTasa;

    public BonTasaEstado() {
    }

    public BonTasaEstado(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public String getTipoEstadoBonTasa() {
        return tipoEstadoBonTasa;
    }

    public void setTipoEstadoBonTasa(String tipoEstadoBonTasa) {
        this.tipoEstadoBonTasa = tipoEstadoBonTasa;
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
        if (!(object instanceof BonTasaEstado)) {
            return false;
        }
        BonTasaEstado other = (BonTasaEstado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.BonTasaEstado[ id=" + id + " ]";
    }
    
}
