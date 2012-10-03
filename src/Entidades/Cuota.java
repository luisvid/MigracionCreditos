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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "Cuota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuota.findAll", query = "SELECT c FROM Cuota c"),
    @NamedQuery(name = "Cuota.findById", query = "SELECT c FROM Cuota c WHERE c.id = :id"),
    @NamedQuery(name = "Cuota.findByCapital", query = "SELECT c FROM Cuota c WHERE c.capital = :capital"),
    @NamedQuery(name = "Cuota.findByCompensatorio", query = "SELECT c FROM Cuota c WHERE c.compensatorio = :compensatorio"),
    @NamedQuery(name = "Cuota.findByFechaGeneracion", query = "SELECT c FROM Cuota c WHERE c.fechaGeneracion = :fechaGeneracion"),
    @NamedQuery(name = "Cuota.findByFechaVencimiento", query = "SELECT c FROM Cuota c WHERE c.fechaVencimiento = :fechaVencimiento"),
    @NamedQuery(name = "Cuota.findByMoratorio", query = "SELECT c FROM Cuota c WHERE c.moratorio = :moratorio"),
    @NamedQuery(name = "Cuota.findByNumero", query = "SELECT c FROM Cuota c WHERE c.numero = :numero"),
    @NamedQuery(name = "Cuota.findByPunitorio", query = "SELECT c FROM Cuota c WHERE c.punitorio = :punitorio"),
    @NamedQuery(name = "Cuota.findByTasaCompensatorio", query = "SELECT c FROM Cuota c WHERE c.tasaCompensatorio = :tasaCompensatorio"),
    @NamedQuery(name = "Cuota.findByTasaMoratorio", query = "SELECT c FROM Cuota c WHERE c.tasaMoratorio = :tasaMoratorio"),
    @NamedQuery(name = "Cuota.findByTasaPunitorio", query = "SELECT c FROM Cuota c WHERE c.tasaPunitorio = :tasaPunitorio"),
    @NamedQuery(name = "Cuota.findByBonificacion", query = "SELECT c FROM Cuota c WHERE c.bonificacion = :bonificacion"),
    @NamedQuery(name = "Cuota.findByEstado", query = "SELECT c FROM Cuota c WHERE c.estado = :estado"),
    @NamedQuery(name = "Cuota.findByGastos", query = "SELECT c FROM Cuota c WHERE c.gastos = :gastos"),
    @NamedQuery(name = "Cuota.findByMultas", query = "SELECT c FROM Cuota c WHERE c.multas = :multas"),
    @NamedQuery(name = "Cuota.findByCredito_id", query = "SELECT c FROM Cuota c WHERE c.creditoId = :Objetoi"),
    @NamedQuery(name = "Cuota.findByDetalle", query = "SELECT c FROM Cuota c WHERE c.detalle = :detalle")})
public class Cuota implements Serializable {
    @Column(name =     "fechaGeneracion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaGeneracion;
    @Column(name =     "fechaVencimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVencimiento;
    @OneToMany(mappedBy = "cuotaId")
    private Collection<CaducidadPlazo> caducidadPlazoCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
   // @GeneratedValue(strategy= GenerationType.AUTO)
    private BigDecimal id;
    @Column(name = "capital")
    private Double capital;
    @Column(name = "compensatorio")
    private Double compensatorio;
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
    @Column(name = "bonificacion")
    private Double bonificacion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "gastos")
    private Double gastos;
    @Column(name = "multas")
    private Double multas;
    @Column(name = "detalle")
    private String detalle;
    @OneToMany(mappedBy = "cuotaId")
    private Collection<Ctacte> ctacteCollection;
    @JoinColumn(name = "credito_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi creditoId;
    @JoinColumn(name = "emision_id", referencedColumnName = "id")
    @ManyToOne
    private Emision emisionId;
    @OneToMany(mappedBy = "cuotaId")
    private Collection<BonDetalle> bonDetalleCollection;

    public Cuota() {
    }

    public Cuota(BigDecimal id) {
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

    public Double getBonificacion() {
        return bonificacion;
    }

    public void setBonificacion(Double bonificacion) {
        this.bonificacion = bonificacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getGastos() {
        return gastos;
    }

    public void setGastos(Double gastos) {
        this.gastos = gastos;
    }

    public Double getMultas() {
        return multas;
    }

    public void setMultas(Double multas) {
        this.multas = multas;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @XmlTransient
    public Collection<Ctacte> getCtacteCollection() {
        return ctacteCollection;
    }

    public void setCtacteCollection(Collection<Ctacte> ctacteCollection) {
        this.ctacteCollection = ctacteCollection;
    }

    public Objetoi getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Objetoi creditoId) {
        this.creditoId = creditoId;
    }

    public Emision getEmisionId() {
        return emisionId;
    }

    public void setEmisionId(Emision emisionId) {
        this.emisionId = emisionId;
    }

    @XmlTransient
    public Collection<BonDetalle> getBonDetalleCollection() {
        return bonDetalleCollection;
    }

    public void setBonDetalleCollection(Collection<BonDetalle> bonDetalleCollection) {
        this.bonDetalleCollection = bonDetalleCollection;
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
        if (!(object instanceof Cuota)) {
            return false;
        }
        Cuota other = (Cuota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Cuota[ id=" + id + " ]";
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @XmlTransient
    public Collection<CaducidadPlazo> getCaducidadPlazoCollection() {
        return caducidadPlazoCollection;
    }

    public void setCaducidadPlazoCollection(Collection<CaducidadPlazo> caducidadPlazoCollection) {
        this.caducidadPlazoCollection = caducidadPlazoCollection;
    }
    
}
