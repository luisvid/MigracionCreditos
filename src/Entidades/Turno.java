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
@Table(name = "Turno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turno.findAll", query = "SELECT t FROM Turno t"),
    @NamedQuery(name = "Turno.findById", query = "SELECT t FROM Turno t WHERE t.id = :id"),
    @NamedQuery(name = "Turno.findByAtencion", query = "SELECT t FROM Turno t WHERE t.atencion = :atencion"),
    @NamedQuery(name = "Turno.findByEstado", query = "SELECT t FROM Turno t WHERE t.estado = :estado"),
    @NamedQuery(name = "Turno.findByLlegada", query = "SELECT t FROM Turno t WHERE t.llegada = :llegada"),
    @NamedQuery(name = "Turno.findByMail", query = "SELECT t FROM Turno t WHERE t.mail = :mail"),
    @NamedQuery(name = "Turno.findByMonto", query = "SELECT t FROM Turno t WHERE t.monto = :monto"),
    @NamedQuery(name = "Turno.findByNombre", query = "SELECT t FROM Turno t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Turno.findByNumeroAtencion", query = "SELECT t FROM Turno t WHERE t.numeroAtencion = :numeroAtencion"),
    @NamedQuery(name = "Turno.findByObjeto", query = "SELECT t FROM Turno t WHERE t.objeto = :objeto"),
    @NamedQuery(name = "Turno.findByObservaciones", query = "SELECT t FROM Turno t WHERE t.observaciones = :observaciones"),
    @NamedQuery(name = "Turno.findByAsesorcauserK", query = "SELECT t FROM Turno t WHERE t.asesorcauserK = :asesorcauserK"),
    @NamedQuery(name = "Turno.findByTipoConsulta", query = "SELECT t FROM Turno t WHERE t.tipoConsulta = :tipoConsulta"),
    @NamedQuery(name = "Turno.findByUnidadId", query = "SELECT t FROM Turno t WHERE t.unidadId = :unidadId")})
public class Turno implements Serializable {
    @Column(name =     "atencion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date atencion;
    @Column(name =     "llegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date llegada;
    @OneToMany(mappedBy = "turnoId")
    private Collection<BonTasa> bonTasaCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "estado")
    private String estado;
    @Column(name = "mail")
    private String mail;
    @Column(name = "monto")
    private Double monto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "numeroAtencion")
    private BigInteger numeroAtencion;
    @Column(name = "objeto")
    private String objeto;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "asesor_causerK")
    private String asesorcauserK;
    @Column(name = "tipoConsulta")
    private String tipoConsulta;
    @Column(name = "unidadId")
    private BigInteger unidadId;
    @OneToMany(mappedBy = "turnoId")
    private Collection<Objetoi> objetoiCollection;
    @JoinColumn(name = "linea_id", referencedColumnName = "id")
    @ManyToOne
    private Linea lineaId;

    public Turno() {
    }

    public Turno(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigInteger getNumeroAtencion() {
        return numeroAtencion;
    }

    public void setNumeroAtencion(BigInteger numeroAtencion) {
        this.numeroAtencion = numeroAtencion;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getAsesorcauserK() {
        return asesorcauserK;
    }

    public void setAsesorcauserK(String asesorcauserK) {
        this.asesorcauserK = asesorcauserK;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public BigInteger getUnidadId() {
        return unidadId;
    }

    public void setUnidadId(BigInteger unidadId) {
        this.unidadId = unidadId;
    }

    @XmlTransient
    public Collection<Objetoi> getObjetoiCollection() {
        return objetoiCollection;
    }

    public void setObjetoiCollection(Collection<Objetoi> objetoiCollection) {
        this.objetoiCollection = objetoiCollection;
    }

    public Linea getLineaId() {
        return lineaId;
    }

    public void setLineaId(Linea lineaId) {
        this.lineaId = lineaId;
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
        if (!(object instanceof Turno)) {
            return false;
        }
        Turno other = (Turno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Turno[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<BonTasa> getBonTasaCollection() {
        return bonTasaCollection;
    }

    public void setBonTasaCollection(Collection<BonTasa> bonTasaCollection) {
        this.bonTasaCollection = bonTasaCollection;
    }

    public Date getAtencion() {
        return atencion;
    }

    public void setAtencion(Date atencion) {
        this.atencion = atencion;
    }

    public Date getLlegada() {
        return llegada;
    }

    public void setLlegada(Date llegada) {
        this.llegada = llegada;
    }
    
}
