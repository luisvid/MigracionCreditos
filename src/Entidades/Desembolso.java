/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "Desembolso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Desembolso.findAll", query = "SELECT d FROM Desembolso d"),
    @NamedQuery(name = "Desembolso.findById", query = "SELECT d FROM Desembolso d WHERE d.id = :id"),
    @NamedQuery(name = "Desembolso.findByFecha", query = "SELECT d FROM Desembolso d WHERE d.fecha = :fecha"),
    @NamedQuery(name = "Desembolso.findByImporte", query = "SELECT d FROM Desembolso d WHERE d.importe = :importe"),
    @NamedQuery(name = "Desembolso.findByNumero", query = "SELECT d FROM Desembolso d WHERE d.numero = :numero"),
    @NamedQuery(name = "Desembolso.findByObservacion", query = "SELECT d FROM Desembolso d WHERE d.observacion = :observacion"),
    @NamedQuery(name = "Desembolso.findByEstado", query = "SELECT d FROM Desembolso d WHERE d.estado = :estado"),
    @NamedQuery(name = "Desembolso.findByFechaReal", query = "SELECT d FROM Desembolso d WHERE d.fechaReal = :fechaReal"),
    @NamedQuery(name = "Desembolso.findByImporteGastos", query = "SELECT d FROM Desembolso d WHERE d.importeGastos = :importeGastos"),
    @NamedQuery(name = "Desembolso.findByImporteMultas", query = "SELECT d FROM Desembolso d WHERE d.importeMultas = :importeMultas"),
    @NamedQuery(name = "Desembolso.findByImporteReal", query = "SELECT d FROM Desembolso d WHERE d.importeReal = :importeReal"),
    @NamedQuery(name = "Desembolso.findByNroOrden", query = "SELECT d FROM Desembolso d WHERE d.nroOrden = :nroOrden"),
    @NamedQuery(name = "Desembolso.findByCuentaInterbankingId", query = "SELECT d FROM Desembolso d WHERE d.cuentaInterbankingId = :cuentaInterbankingId"),
    @NamedQuery(name = "Desembolso.findByCotizacion", query = "SELECT d FROM Desembolso d WHERE d.cotizacion = :cotizacion"),
    @NamedQuery(name = "Desembolso.findByFechaSolicitud", query = "SELECT d FROM Desembolso d WHERE d.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Desembolso.findByOrdepagoEjercicio", query = "SELECT d FROM Desembolso d WHERE d.ordepagoEjercicio = :ordepagoEjercicio")})
public class Desembolso implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "importe")
    private Double importe;
    @Column(name = "numero")
    private Integer numero;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaReal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReal;
    @Column(name = "importeGastos")
    private Double importeGastos;
    @Column(name = "importeMultas")
    private Double importeMultas;
    @Column(name = "importeReal")
    private Double importeReal;
    @Column(name = "nroOrden")
    private Integer nroOrden;
    @Column(name = "cuentaInterbankingId")
    private BigInteger cuentaInterbankingId;
    @Column(name = "cotizacion")
    private Double cotizacion;
    @Column(name = "fechaSolicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;
    @Column(name = "ordepago_ejercicio")
    private Integer ordepagoEjercicio;
    @JoinColumn(name = "requisito_id", referencedColumnName = "id")
    @ManyToOne
    private Requisito requisitoId;
    @JoinColumn(name = "credito_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi creditoId;

    public Desembolso() {
    }

    public Desembolso(BigDecimal id) {
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

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaReal() {
        return fechaReal;
    }

    public void setFechaReal(Date fechaReal) {
        this.fechaReal = fechaReal;
    }

    public Double getImporteGastos() {
        return importeGastos;
    }

    public void setImporteGastos(Double importeGastos) {
        this.importeGastos = importeGastos;
    }

    public Double getImporteMultas() {
        return importeMultas;
    }

    public void setImporteMultas(Double importeMultas) {
        this.importeMultas = importeMultas;
    }

    public Double getImporteReal() {
        return importeReal;
    }

    public void setImporteReal(Double importeReal) {
        this.importeReal = importeReal;
    }

    public Integer getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(Integer nroOrden) {
        this.nroOrden = nroOrden;
    }

    public BigInteger getCuentaInterbankingId() {
        return cuentaInterbankingId;
    }

    public void setCuentaInterbankingId(BigInteger cuentaInterbankingId) {
        this.cuentaInterbankingId = cuentaInterbankingId;
    }

    public Double getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Double cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Integer getOrdepagoEjercicio() {
        return ordepagoEjercicio;
    }

    public void setOrdepagoEjercicio(Integer ordepagoEjercicio) {
        this.ordepagoEjercicio = ordepagoEjercicio;
    }

    public Requisito getRequisitoId() {
        return requisitoId;
    }

    public void setRequisitoId(Requisito requisitoId) {
        this.requisitoId = requisitoId;
    }

    public Objetoi getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Objetoi creditoId) {
        this.creditoId = creditoId;
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
        if (!(object instanceof Desembolso)) {
            return false;
        }
        Desembolso other = (Desembolso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Desembolso[ id=" + id + " ]";
    }
    
}
