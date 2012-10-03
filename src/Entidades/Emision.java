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
@Table(name = "Emision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emision.findAll", query = "SELECT e FROM Emision e"),
    @NamedQuery(name = "Emision.findById", query = "SELECT e FROM Emision e WHERE e.id = :id"),
    @NamedQuery(name = "Emision.findByCantidad", query = "SELECT e FROM Emision e WHERE e.cantidad = :cantidad"),
    @NamedQuery(name = "Emision.findByCerrada", query = "SELECT e FROM Emision e WHERE e.cerrada = :cerrada"),
    @NamedQuery(name = "Emision.findByFacturada", query = "SELECT e FROM Emision e WHERE e.facturada = :facturada"),
    @NamedQuery(name = "Emision.findByFechaEmision", query = "SELECT e FROM Emision e WHERE e.fechaEmision = :fechaEmision"),
    @NamedQuery(name = "Emision.findByImporte", query = "SELECT e FROM Emision e WHERE e.importe = :importe"),
    @NamedQuery(name = "Emision.findByOrdenEmision", query = "SELECT e FROM Emision e WHERE e.ordenEmision = :ordenEmision")})
public class Emision implements Serializable {
    @Column(name =     "fechaEmision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEmision;
    @Column(name = "filtroFechaVencimiento")
    private String filtroFechaVencimiento;
    @Column(name = "criterio")
    private String criterio;
    @Column(name = "fechaHasta")
    private String fechaHasta;
    @Column(name = "moneda")
    private String moneda;
    @Column(name = "usuario")
    private String usuario;
    @Column(name = "valorDesde")
    private String valorDesde;
    @Column(name = "valorHasta")
    private String valorHasta;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "cerrada")
    private Short cerrada;
    @Column(name = "facturada")
    private Short facturada;
    @Column(name = "importe")
    private Double importe;
    @Column(name = "ordenEmision")
    private Integer ordenEmision;
    @JoinColumn(name = "leyenda_id", referencedColumnName = "id")
    @ManyToOne
    private Leyenda leyendaId;
    @OneToMany(mappedBy = "emisionId")
    private Collection<Emideta> emidetaCollection;
    @OneToMany(mappedBy = "emisionId")
    private Collection<Cuota> cuotaCollection;

    public Emision() {
    }

    public Emision(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Short getCerrada() {
        return cerrada;
    }

    public void setCerrada(Short cerrada) {
        this.cerrada = cerrada;
    }

    public Short getFacturada() {
        return facturada;
    }

    public void setFacturada(Short facturada) {
        this.facturada = facturada;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Integer getOrdenEmision() {
        return ordenEmision;
    }

    public void setOrdenEmision(Integer ordenEmision) {
        this.ordenEmision = ordenEmision;
    }

    public Leyenda getLeyendaId() {
        return leyendaId;
    }

    public void setLeyendaId(Leyenda leyendaId) {
        this.leyendaId = leyendaId;
    }

    @XmlTransient
    public Collection<Emideta> getEmidetaCollection() {
        return emidetaCollection;
    }

    public void setEmidetaCollection(Collection<Emideta> emidetaCollection) {
        this.emidetaCollection = emidetaCollection;
    }

    @XmlTransient
    public Collection<Cuota> getCuotaCollection() {
        return cuotaCollection;
    }

    public void setCuotaCollection(Collection<Cuota> cuotaCollection) {
        this.cuotaCollection = cuotaCollection;
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
        if (!(object instanceof Emision)) {
            return false;
        }
        Emision other = (Emision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Emision[ id=" + id + " ]";
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFiltroFechaVencimiento() {
        return filtroFechaVencimiento;
    }

    public void setFiltroFechaVencimiento(String filtroFechaVencimiento) {
        this.filtroFechaVencimiento = filtroFechaVencimiento;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getValorDesde() {
        return valorDesde;
    }

    public void setValorDesde(String valorDesde) {
        this.valorDesde = valorDesde;
    }

    public String getValorHasta() {
        return valorHasta;
    }

    public void setValorHasta(String valorHasta) {
        this.valorHasta = valorHasta;
    }
    
}
