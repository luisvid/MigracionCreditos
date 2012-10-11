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
@Table(name = "CuotaBonTasa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CuotaBonTasa.findAll", query = "SELECT c FROM CuotaBonTasa c"),
    @NamedQuery(name = "CuotaBonTasa.findById", query = "SELECT c FROM CuotaBonTasa c WHERE c.id = :id"),
    @NamedQuery(name = "CuotaBonTasa.findByCapital", query = "SELECT c FROM CuotaBonTasa c WHERE c.capital = :capital"),
    @NamedQuery(name = "CuotaBonTasa.findByCompensatorio", query = "SELECT c FROM CuotaBonTasa c WHERE c.compensatorio = :compensatorio"),
    @NamedQuery(name = "CuotaBonTasa.findByFechaVencimiento", query = "SELECT c FROM CuotaBonTasa c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "CuotaBonTasa.findByMoratorio", query = "SELECT c FROM CuotaBonTasa c WHERE c.moratorio = :moratorio"),
    @NamedQuery(name = "CuotaBonTasa.findByNumero", query = "SELECT c FROM CuotaBonTasa c WHERE c.numero = :numero"),
    @NamedQuery(name = "CuotaBonTasa.findByPunitorio", query = "SELECT c FROM CuotaBonTasa c WHERE c.punitorio = :punitorio"),
    @NamedQuery(name = "CuotaBonTasa.findByTasaCompensatorio", query = "SELECT c FROM CuotaBonTasa c WHERE c.tasaCompensatorio = :tasaCompensatorio"),
    @NamedQuery(name = "CuotaBonTasa.findByTasaMoratorio", query = "SELECT c FROM CuotaBonTasa c WHERE c.tasaMoratorio = :tasaMoratorio"),
    @NamedQuery(name = "CuotaBonTasa.findByTasaPunitorio", query = "SELECT c FROM CuotaBonTasa c WHERE c.tasaPunitorio = :tasaPunitorio"),
    @NamedQuery(name = "CuotaBonTasa.findByTipoEstadoBonCuota", query = "SELECT c FROM CuotaBonTasa c WHERE c.tipoEstadoBonCuota = :tipoEstadoBonCuota")})
public class CuotaBonTasa implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "capital")
    private Double capital;
    @Column(name = "compensatorio")
    private Double compensatorio;
    @Column(name = "fechaVencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @Column(name = "moratorio")
    private Double moratorio;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "punitorio")
    private Double punitorio;
    @Column(name = "tasaCompensatorio")
    private Double tasaCompensatorio;
    @Column(name = "tasaMoratorio")
    private Double tasaMoratorio;
    @Column(name = "tasaPunitorio")
    private Double tasaPunitorio;
    @Column(name = "tipoEstadoBonCuota")
    private String tipoEstadoBonCuota;
    @JoinColumn(name = "bonTasa_id", referencedColumnName = "id")
    @ManyToOne
    private BonTasa bonTasaid;

    public CuotaBonTasa() {
    }

    public CuotaBonTasa(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getCompensatorio() {
        return compensatorio;
    }

    public void setCompensatorio(Double compensatorio) {
        this.compensatorio = compensatorio;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Double getMoratorio() {
        return moratorio;
    }

    public void setMoratorio(Double moratorio) {
        this.moratorio = moratorio;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getPunitorio() {
        return punitorio;
    }

    public void setPunitorio(Double punitorio) {
        this.punitorio = punitorio;
    }

    public Double getTasaCompensatorio() {
        return tasaCompensatorio;
    }

    public void setTasaCompensatorio(Double tasaCompensatorio) {
        this.tasaCompensatorio = tasaCompensatorio;
    }

    public Double getTasaMoratorio() {
        return tasaMoratorio;
    }

    public void setTasaMoratorio(Double tasaMoratorio) {
        this.tasaMoratorio = tasaMoratorio;
    }

    public Double getTasaPunitorio() {
        return tasaPunitorio;
    }

    public void setTasaPunitorio(Double tasaPunitorio) {
        this.tasaPunitorio = tasaPunitorio;
    }

    public String getTipoEstadoBonCuota() {
        return tipoEstadoBonCuota;
    }

    public void setTipoEstadoBonCuota(String tipoEstadoBonCuota) {
        this.tipoEstadoBonCuota = tipoEstadoBonCuota;
    }

    public BonTasa getBonTasaid() {
        return bonTasaid;
    }

    public void setBonTasaid(BonTasa bonTasaid) {
        this.bonTasaid = bonTasaid;
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
        if (!(object instanceof CuotaBonTasa)) {
            return false;
        }
        CuotaBonTasa other = (CuotaBonTasa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.CuotaBonTasa[ id=" + id + " ]";
    }
    
}
