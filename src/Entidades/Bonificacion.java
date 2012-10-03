/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "Bonificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bonificacion.findAll", query = "SELECT b FROM Bonificacion b"),
    @NamedQuery(name = "Bonificacion.findById", query = "SELECT b FROM Bonificacion b WHERE b.id = :id"),
    @NamedQuery(name = "Bonificacion.findByAcumulaPorLinea", query = "SELECT b FROM Bonificacion b WHERE b.acumulaPorLinea = :acumulaPorLinea"),
    @NamedQuery(name = "Bonificacion.findByAcumulaPorPersona", query = "SELECT b FROM Bonificacion b WHERE b.acumulaPorPersona = :acumulaPorPersona"),
    @NamedQuery(name = "Bonificacion.findByCuotasNoPagas", query = "SELECT b FROM Bonificacion b WHERE b.cuotasNoPagas = :cuotasNoPagas"),
    @NamedQuery(name = "Bonificacion.findByMaximoInteres", query = "SELECT b FROM Bonificacion b WHERE b.maximoInteres = :maximoInteres"),
    @NamedQuery(name = "Bonificacion.findByMaximoMonto", query = "SELECT b FROM Bonificacion b WHERE b.maximoMonto = :maximoMonto"),
    @NamedQuery(name = "Bonificacion.findByNombre", query = "SELECT b FROM Bonificacion b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Bonificacion.findByTasaBonificada", query = "SELECT b FROM Bonificacion b WHERE b.tasaBonificada = :tasaBonificada"),
    @NamedQuery(name = "Bonificacion.findByTipoAmortizacion", query = "SELECT b FROM Bonificacion b WHERE b.tipoAmortizacion = :tipoAmortizacion"),
    @NamedQuery(name = "Bonificacion.findByEnteBonificadorid", query = "SELECT b FROM Bonificacion b WHERE b.enteBonificadorid = :enteBonificadorid"),
    @NamedQuery(name = "Bonificacion.findByFechaCaducidad", query = "SELECT b FROM Bonificacion b WHERE b.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "Bonificacion.findByMaximoAcumuladoPorBeneficiario", query = "SELECT b FROM Bonificacion b WHERE b.maximoAcumuladoPorBeneficiario = :maximoAcumuladoPorBeneficiario"),
    @NamedQuery(name = "Bonificacion.findByMaximoAcumuladoPorLinea", query = "SELECT b FROM Bonificacion b WHERE b.maximoAcumuladoPorLinea = :maximoAcumuladoPorLinea"),
    @NamedQuery(name = "Bonificacion.findByMaximoCuotasBonificadas", query = "SELECT b FROM Bonificacion b WHERE b.maximoCuotasBonificadas = :maximoCuotasBonificadas"),
    @NamedQuery(name = "Bonificacion.findByMinimoInteres", query = "SELECT b FROM Bonificacion b WHERE b.minimoInteres = :minimoInteres"),
    @NamedQuery(name = "Bonificacion.findByMontoAcumuladoPorBanco", query = "SELECT b FROM Bonificacion b WHERE b.montoAcumuladoPorBanco = :montoAcumuladoPorBanco"),
    @NamedQuery(name = "Bonificacion.findByTipoDeBonificacion", query = "SELECT b FROM Bonificacion b WHERE b.tipoDeBonificacion = :tipoDeBonificacion"),
    @NamedQuery(name = "Bonificacion.findByInterno", query = "SELECT b FROM Bonificacion b WHERE b.interno = :interno"),
    @NamedQuery(name = "Bonificacion.findByEnteBonificadoid", query = "SELECT b FROM Bonificacion b WHERE b.enteBonificadoid = :enteBonificadoid"),
    @NamedQuery(name = "Bonificacion.findByEnteBonificadorCODIBA", query = "SELECT b FROM Bonificacion b WHERE b.enteBonificadorCODIBA = :enteBonificadorCODIBA")})
public class Bonificacion implements Serializable {
    @Column(name = "fechaCaducidad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCaducidad;
    @OneToMany(mappedBy = "bonificacionId")
    private Collection<BonDetalle> bonDetalleCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "acumulaPorLinea")
    private Short acumulaPorLinea;
    @Column(name = "acumulaPorPersona")
    private Short acumulaPorPersona;
    @Column(name = "cuotasNoPagas")
    private Integer cuotasNoPagas;
    @Column(name = "maximoInteres")
    private Double maximoInteres;
    @Column(name = "maximoMonto")
    private Double maximoMonto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tasaBonificada")
    private Double tasaBonificada;
    @Column(name = "tipoAmortizacion")
    private Integer tipoAmortizacion;
    @Column(name = "enteBonificador_id")
    private BigInteger enteBonificadorid;
    @Column(name = "maximoAcumuladoPorBeneficiario")
    private Double maximoAcumuladoPorBeneficiario;
    @Column(name = "maximoAcumuladoPorLinea")
    private Double maximoAcumuladoPorLinea;
    @Column(name = "maximoCuotasBonificadas")
    private Integer maximoCuotasBonificadas;
    @Column(name = "minimoInteres")
    private Double minimoInteres;
    @Column(name = "montoAcumuladoPorBanco")
    private Double montoAcumuladoPorBanco;
    @Column(name = "tipoDeBonificacion")
    private String tipoDeBonificacion;
    @Column(name = "interno")
    private Short interno;
    @Column(name = "enteBonificado_id")
    private BigInteger enteBonificadoid;
    @Column(name = "enteBonificador_CODI_BA")
    private BigInteger enteBonificadorCODIBA;
    @OneToMany(mappedBy = "idBonificacion")
    private Collection<ObjetoiBonificacion> objetoiBonificacionCollection;
    @JoinColumn(name = "conceptoAforo_concepto", referencedColumnName = "concepto")
    @ManyToOne
    private CConcepto conceptoAforoconcepto;

    public Bonificacion() {
    }

    public Bonificacion(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Short getAcumulaPorLinea() {
        return acumulaPorLinea;
    }

    public void setAcumulaPorLinea(Short acumulaPorLinea) {
        this.acumulaPorLinea = acumulaPorLinea;
    }

    public Short getAcumulaPorPersona() {
        return acumulaPorPersona;
    }

    public void setAcumulaPorPersona(Short acumulaPorPersona) {
        this.acumulaPorPersona = acumulaPorPersona;
    }

    public Integer getCuotasNoPagas() {
        return cuotasNoPagas;
    }

    public void setCuotasNoPagas(Integer cuotasNoPagas) {
        this.cuotasNoPagas = cuotasNoPagas;
    }

    public Double getMaximoInteres() {
        return maximoInteres;
    }

    public void setMaximoInteres(Double maximoInteres) {
        this.maximoInteres = maximoInteres;
    }

    public Double getMaximoMonto() {
        return maximoMonto;
    }

    public void setMaximoMonto(Double maximoMonto) {
        this.maximoMonto = maximoMonto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getTasaBonificada() {
        return tasaBonificada;
    }

    public void setTasaBonificada(Double tasaBonificada) {
        this.tasaBonificada = tasaBonificada;
    }

    public Integer getTipoAmortizacion() {
        return tipoAmortizacion;
    }

    public void setTipoAmortizacion(Integer tipoAmortizacion) {
        this.tipoAmortizacion = tipoAmortizacion;
    }

    public BigInteger getEnteBonificadorid() {
        return enteBonificadorid;
    }

    public void setEnteBonificadorid(BigInteger enteBonificadorid) {
        this.enteBonificadorid = enteBonificadorid;
    }

    public Double getMaximoAcumuladoPorBeneficiario() {
        return maximoAcumuladoPorBeneficiario;
    }

    public void setMaximoAcumuladoPorBeneficiario(Double maximoAcumuladoPorBeneficiario) {
        this.maximoAcumuladoPorBeneficiario = maximoAcumuladoPorBeneficiario;
    }

    public Double getMaximoAcumuladoPorLinea() {
        return maximoAcumuladoPorLinea;
    }

    public void setMaximoAcumuladoPorLinea(Double maximoAcumuladoPorLinea) {
        this.maximoAcumuladoPorLinea = maximoAcumuladoPorLinea;
    }

    public Integer getMaximoCuotasBonificadas() {
        return maximoCuotasBonificadas;
    }

    public void setMaximoCuotasBonificadas(Integer maximoCuotasBonificadas) {
        this.maximoCuotasBonificadas = maximoCuotasBonificadas;
    }

    public Double getMinimoInteres() {
        return minimoInteres;
    }

    public void setMinimoInteres(Double minimoInteres) {
        this.minimoInteres = minimoInteres;
    }

    public Double getMontoAcumuladoPorBanco() {
        return montoAcumuladoPorBanco;
    }

    public void setMontoAcumuladoPorBanco(Double montoAcumuladoPorBanco) {
        this.montoAcumuladoPorBanco = montoAcumuladoPorBanco;
    }

    public String getTipoDeBonificacion() {
        return tipoDeBonificacion;
    }

    public void setTipoDeBonificacion(String tipoDeBonificacion) {
        this.tipoDeBonificacion = tipoDeBonificacion;
    }

    public Short getInterno() {
        return interno;
    }

    public void setInterno(Short interno) {
        this.interno = interno;
    }

    public BigInteger getEnteBonificadoid() {
        return enteBonificadoid;
    }

    public void setEnteBonificadoid(BigInteger enteBonificadoid) {
        this.enteBonificadoid = enteBonificadoid;
    }

    public BigInteger getEnteBonificadorCODIBA() {
        return enteBonificadorCODIBA;
    }

    public void setEnteBonificadorCODIBA(BigInteger enteBonificadorCODIBA) {
        this.enteBonificadorCODIBA = enteBonificadorCODIBA;
    }

    @XmlTransient
    public Collection<ObjetoiBonificacion> getObjetoiBonificacionCollection() {
        return objetoiBonificacionCollection;
    }

    public void setObjetoiBonificacionCollection(Collection<ObjetoiBonificacion> objetoiBonificacionCollection) {
        this.objetoiBonificacionCollection = objetoiBonificacionCollection;
    }

    public CConcepto getConceptoAforoconcepto() {
        return conceptoAforoconcepto;
    }

    public void setConceptoAforoconcepto(CConcepto conceptoAforoconcepto) {
        this.conceptoAforoconcepto = conceptoAforoconcepto;
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
        if (!(object instanceof Bonificacion)) {
            return false;
        }
        Bonificacion other = (Bonificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Bonificacion[ id=" + id + " ]";
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    @XmlTransient
    public Collection<BonDetalle> getBonDetalleCollection() {
        return bonDetalleCollection;
    }

    public void setBonDetalleCollection(Collection<BonDetalle> bonDetalleCollection) {
        this.bonDetalleCollection = bonDetalleCollection;
    }
    
}
