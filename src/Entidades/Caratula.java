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
@Table(name = "Caratula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Caratula.findAll", query = "SELECT c FROM Caratula c"),
    @NamedQuery(name = "Caratula.findById", query = "SELECT c FROM Caratula c WHERE c.id = :id"),
    @NamedQuery(name = "Caratula.findByActualizada", query = "SELECT c FROM Caratula c WHERE c.actualizada = :actualizada"),
    @NamedQuery(name = "Caratula.findByComprobantes", query = "SELECT c FROM Caratula c WHERE c.comprobantes = :comprobantes"),
    @NamedQuery(name = "Caratula.findByFechaActualizacion", query = "SELECT c FROM Caratula c WHERE c.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Caratula.findByFechaCobranza", query = "SELECT c FROM Caratula c WHERE c.fechaCobranza = :fechaCobranza"),
    @NamedQuery(name = "Caratula.findByFechaEnvio", query = "SELECT c FROM Caratula c WHERE c.fechaEnvio = :fechaEnvio"),
    @NamedQuery(name = "Caratula.findByGrupo", query = "SELECT c FROM Caratula c WHERE c.grupo = :grupo"),
    @NamedQuery(name = "Caratula.findByImporte", query = "SELECT c FROM Caratula c WHERE c.importe = :importe"),
    @NamedQuery(name = "Caratula.findByBancoId", query = "SELECT c FROM Caratula c WHERE c.bancoId = :bancoId"),
    @NamedQuery(name = "Caratula.findByObservaciones", query = "SELECT c FROM Caratula c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "Caratula.findByBancoCODIBA", query = "SELECT c FROM Caratula c WHERE c.bancoCODIBA = :bancoCODIBA")})
public class Caratula implements Serializable {
    @Column(name =     "fechaActualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name =     "fechaCobranza")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCobranza;
    @Column(name =     "fechaEnvio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "actualizada")
    private BigInteger actualizada;
    @Column(name = "comprobantes")
    private BigInteger comprobantes;
    @Column(name = "grupo")
    private BigInteger grupo;
    @Column(name = "importe")
    private Double importe;
    @Column(name = "banco_id")
    private BigInteger bancoId;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "banco_CODI_BA")
    private BigInteger bancoCODIBA;
    @OneToMany(mappedBy = "caratulaId")
    private Collection<Ctacte> ctacteCollection;

    public Caratula() {
    }

    public Caratula(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getActualizada() {
        return actualizada;
    }

    public void setActualizada(BigInteger actualizada) {
        this.actualizada = actualizada;
    }

    public BigInteger getComprobantes() {
        return comprobantes;
    }

    public void setComprobantes(BigInteger comprobantes) {
        this.comprobantes = comprobantes;
    }

    public BigInteger getGrupo() {
        return grupo;
    }

    public void setGrupo(BigInteger grupo) {
        this.grupo = grupo;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public BigInteger getBancoId() {
        return bancoId;
    }

    public void setBancoId(BigInteger bancoId) {
        this.bancoId = bancoId;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigInteger getBancoCODIBA() {
        return bancoCODIBA;
    }

    public void setBancoCODIBA(BigInteger bancoCODIBA) {
        this.bancoCODIBA = bancoCODIBA;
    }

    @XmlTransient
    public Collection<Ctacte> getCtacteCollection() {
        return ctacteCollection;
    }

    public void setCtacteCollection(Collection<Ctacte> ctacteCollection) {
        this.ctacteCollection = ctacteCollection;
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
        if (!(object instanceof Caratula)) {
            return false;
        }
        Caratula other = (Caratula) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Caratula[ id=" + id + " ]";
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCobranza() {
        return fechaCobranza;
    }

    public void setFechaCobranza(Date fechaCobranza) {
        this.fechaCobranza = fechaCobranza;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
    
}
