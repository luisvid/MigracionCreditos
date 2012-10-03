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
@Table(name = "Vinedo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vinedo.findAll", query = "SELECT v FROM Vinedo v"),
    @NamedQuery(name = "Vinedo.findById", query = "SELECT v FROM Vinedo v WHERE v.id = :id"),
    @NamedQuery(name = "Vinedo.findByCodigo", query = "SELECT v FROM Vinedo v WHERE v.codigo = :codigo"),
    @NamedQuery(name = "Vinedo.findByDepartamento", query = "SELECT v FROM Vinedo v WHERE v.departamento = :departamento"),
    @NamedQuery(name = "Vinedo.findByHectareas", query = "SELECT v FROM Vinedo v WHERE v.hectareas = :hectareas"),
    @NamedQuery(name = "Vinedo.findByObservaciones", query = "SELECT v FROM Vinedo v WHERE v.observaciones = :observaciones"),
    @NamedQuery(name = "Vinedo.findByQqestimados", query = "SELECT v FROM Vinedo v WHERE v.qqestimados = :qqestimados"),
    @NamedQuery(name = "Vinedo.findByEstadoSolicitudINV", query = "SELECT v FROM Vinedo v WHERE v.estadoSolicitudINV = :estadoSolicitudINV"),
    @NamedQuery(name = "Vinedo.findByFechaInformeINV", query = "SELECT v FROM Vinedo v WHERE v.fechaInformeINV = :fechaInformeINV"),
    @NamedQuery(name = "Vinedo.findByFechaSolicitado", query = "SELECT v FROM Vinedo v WHERE v.fechaSolicitado = :fechaSolicitado"),
    @NamedQuery(name = "Vinedo.findByObservacionINV", query = "SELECT v FROM Vinedo v WHERE v.observacionINV = :observacionINV"),
    @NamedQuery(name = "Vinedo.findByQqaprobado", query = "SELECT v FROM Vinedo v WHERE v.qqaprobado = :qqaprobado"),
    @NamedQuery(name = "Vinedo.findByFechaAceptadoInforme2", query = "SELECT v FROM Vinedo v WHERE v.fechaAceptadoInforme2 = :fechaAceptadoInforme2"),
    @NamedQuery(name = "Vinedo.findByFechaSolicitudInforme2", query = "SELECT v FROM Vinedo v WHERE v.fechaSolicitudInforme2 = :fechaSolicitudInforme2")})
public class Vinedo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "codigo")
    private String codigo;
    @Column(name = "departamento")
    private String departamento;
    @Column(name = "hectareas")
    private Double hectareas;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "qqestimados")
    private Double qqestimados;
    @Column(name = "estadoSolicitudINV")
    private String estadoSolicitudINV;
    @Column(name = "fechaInformeINV")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInformeINV;
    @Column(name = "fechaSolicitado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitado;
    @Column(name = "observacionINV")
    private String observacionINV;
    @Column(name = "qqaprobado")
    private Double qqaprobado;
    @Column(name = "fechaAceptadoInforme2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAceptadoInforme2;
    @Column(name = "fechaSolicitudInforme2")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitudInforme2;
    @OneToMany(mappedBy = "vinedoId")
    private Collection<QQIngresados> qQIngresadosCollection;
    @JoinColumn(name = "credito_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi creditoId;
    @JoinColumn(name = "localidad_IDLOCALIDAD", referencedColumnName = "IDLOCALIDAD")
    @ManyToOne
    private Localidad localidadIDLOCALIDAD;

    public Vinedo() {
    }

    public Vinedo(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Double getHectareas() {
        return hectareas;
    }

    public void setHectareas(Double hectareas) {
        this.hectareas = hectareas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getQqestimados() {
        return qqestimados;
    }

    public void setQqestimados(Double qqestimados) {
        this.qqestimados = qqestimados;
    }

    public String getEstadoSolicitudINV() {
        return estadoSolicitudINV;
    }

    public void setEstadoSolicitudINV(String estadoSolicitudINV) {
        this.estadoSolicitudINV = estadoSolicitudINV;
    }

    public Date getFechaInformeINV() {
        return fechaInformeINV;
    }

    public void setFechaInformeINV(Date fechaInformeINV) {
        this.fechaInformeINV = fechaInformeINV;
    }

    public Date getFechaSolicitado() {
        return fechaSolicitado;
    }

    public void setFechaSolicitado(Date fechaSolicitado) {
        this.fechaSolicitado = fechaSolicitado;
    }

    public String getObservacionINV() {
        return observacionINV;
    }

    public void setObservacionINV(String observacionINV) {
        this.observacionINV = observacionINV;
    }

    public Double getQqaprobado() {
        return qqaprobado;
    }

    public void setQqaprobado(Double qqaprobado) {
        this.qqaprobado = qqaprobado;
    }

    public Date getFechaAceptadoInforme2() {
        return fechaAceptadoInforme2;
    }

    public void setFechaAceptadoInforme2(Date fechaAceptadoInforme2) {
        this.fechaAceptadoInforme2 = fechaAceptadoInforme2;
    }

    public Date getFechaSolicitudInforme2() {
        return fechaSolicitudInforme2;
    }

    public void setFechaSolicitudInforme2(Date fechaSolicitudInforme2) {
        this.fechaSolicitudInforme2 = fechaSolicitudInforme2;
    }

    @XmlTransient
    public Collection<QQIngresados> getQQIngresadosCollection() {
        return qQIngresadosCollection;
    }

    public void setQQIngresadosCollection(Collection<QQIngresados> qQIngresadosCollection) {
        this.qQIngresadosCollection = qQIngresadosCollection;
    }

    public Objetoi getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Objetoi creditoId) {
        this.creditoId = creditoId;
    }

    public Localidad getLocalidadIDLOCALIDAD() {
        return localidadIDLOCALIDAD;
    }

    public void setLocalidadIDLOCALIDAD(Localidad localidadIDLOCALIDAD) {
        this.localidadIDLOCALIDAD = localidadIDLOCALIDAD;
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
        if (!(object instanceof Vinedo)) {
            return false;
        }
        Vinedo other = (Vinedo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Vinedo[ id=" + id + " ]";
    }
    
}
