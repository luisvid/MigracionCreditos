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
@Table(name = "PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdpersona", query = "SELECT p FROM Persona p WHERE p.idpersona = :idpersona"),
    @NamedQuery(name = "Persona.findByCodi01", query = "SELECT p FROM Persona p WHERE p.codi01 = :codi01"),
    @NamedQuery(name = "Persona.findByNomb12", query = "SELECT p FROM Persona p WHERE p.nomb12 = :nomb12"),
    @NamedQuery(name = "Persona.findBySexo12", query = "SELECT p FROM Persona p WHERE p.sexo12 = :sexo12"),
    @NamedQuery(name = "Persona.findByZona12", query = "SELECT p FROM Persona p WHERE p.zona12 = :zona12"),
    @NamedQuery(name = "Persona.findByFena12", query = "SELECT p FROM Persona p WHERE p.fena12 = :fena12"),
    @NamedQuery(name = "Persona.findByEciv12", query = "SELECT p FROM Persona p WHERE p.eciv12 = :eciv12"),
    @NamedQuery(name = "Persona.findByNudo12", query = "SELECT p FROM Persona p WHERE p.nudo12 = :nudo12"),
    @NamedQuery(name = "Persona.findByCedu12", query = "SELECT p FROM Persona p WHERE p.cedu12 = :cedu12"),
    @NamedQuery(name = "Persona.findByCpos12", query = "SELECT p FROM Persona p WHERE p.cpos12 = :cpos12"),
    @NamedQuery(name = "Persona.findByExpe12", query = "SELECT p FROM Persona p WHERE p.expe12 = :expe12"),
    @NamedQuery(name = "Persona.findByCuil12", query = "SELECT p FROM Persona p WHERE p.cuil12 = :cuil12"),
    @NamedQuery(name = "Persona.findByTipo12", query = "SELECT p FROM Persona p WHERE p.tipo12 = :tipo12"),
    @NamedQuery(name = "Persona.findByCalle", query = "SELECT p FROM Persona p WHERE p.calle = :calle"),
    @NamedQuery(name = "Persona.findByNumero", query = "SELECT p FROM Persona p WHERE p.numero = :numero"),
    @NamedQuery(name = "Persona.findByPiso", query = "SELECT p FROM Persona p WHERE p.piso = :piso"),
    @NamedQuery(name = "Persona.findByDpto", query = "SELECT p FROM Persona p WHERE p.dpto = :dpto"),
    @NamedQuery(name = "Persona.findByIdbarrio", query = "SELECT p FROM Persona p WHERE p.idbarrio = :idbarrio"),
    @NamedQuery(name = "Persona.findByLocalidad", query = "SELECT p FROM Persona p WHERE p.localidad = :localidad"),
    @NamedQuery(name = "Persona.findByFechaalta", query = "SELECT p FROM Persona p WHERE p.fechaalta = :fechaalta"),
    @NamedQuery(name = "Persona.findByFechabaja", query = "SELECT p FROM Persona p WHERE p.fechabaja = :fechabaja"),
    @NamedQuery(name = "Persona.findByMotivobaja", query = "SELECT p FROM Persona p WHERE p.motivobaja = :motivobaja"),
    @NamedQuery(name = "Persona.findByRazonsocial", query = "SELECT p FROM Persona p WHERE p.razonsocial = :razonsocial"),
    @NamedQuery(name = "Persona.findByIdorigen", query = "SELECT p FROM Persona p WHERE p.idorigen = :idorigen"),
    @NamedQuery(name = "Persona.findByOrigen", query = "SELECT p FROM Persona p WHERE p.origen = :origen"),
    @NamedQuery(name = "Persona.findXCuit", query = "SELECT p FROM Persona p WHERE p.cuil12 = :cuil12"), 
    @NamedQuery(name = "Persona.findXCuitOrDni", query = "SELECT p FROM Persona p WHERE p.cuil12 = :cuil12 OR p.nudo12 = :nudo12")})
public class Persona implements Serializable {
    @Column(name = "FENA_12")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fena12;
    @Column(name = "FECHAALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaalta;
    @Column(name = "FECHABAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabaja;
    @Column(name = "ACTTIVIDADAFIP")
    private String acttividadafip;
    @Column(name = "SITUACIONIVA")
    private String situacioniva;
    @Column(name = "FECHAINICIOACTIVIDAD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechainicioactividad;
    @Column(name = "IIBB")
    private BigInteger iibb;
    @Column(name = "FECHACIERREEJERCICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechacierreejercicio;
    @Column(name = "CANTPERSONAL")
    private BigInteger cantpersonal;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VOLUMENVTA")
    private Double volumenvta;
    @Column(name = "VOLUMENVTAPERIODO")
    private String volumenvtaperiodo;
    @Column(name = "TIPOSECTOR")
    private String tiposector;
    @OneToMany(mappedBy = "personaIDPERSONA")
    private Collection<Objetoi> objetoiCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDPERSONA")
    private BigDecimal idpersona;
    @Column(name = "CODI_01")
    private BigInteger codi01;
    @Column(name = "NOMB_12")
    private String nomb12;
    @Column(name = "SEXO_12")
    private String sexo12;
    @Column(name = "ZONA_12")
    private String zona12;
    @Column(name = "ECIV_12")
    private BigInteger eciv12;
    @Column(name = "NUDO_12")
    private BigInteger nudo12;
    @Column(name = "CEDU_12")
    private BigInteger cedu12;
    @Column(name = "CPOS_12")
    private String cpos12;
    @Column(name = "EXPE_12")
    private String expe12;
    @Column(name = "CUIL_12")
    private BigInteger cuil12;
    @Column(name = "TIPO_12")
    private String tipo12;
    @Column(name = "CALLE")
    private String calle;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "PISO")
    private String piso;
    @Column(name = "DPTO")
    private String dpto;
    @Column(name = "IDBARRIO")
    private BigInteger idbarrio;
    @Column(name = "LOCALIDAD")
    private String localidad;
    @Column(name = "MOTIVOBAJA")
    private String motivobaja;
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;
    @Column(name = "IDORIGEN")
    private BigInteger idorigen;
    @Column(name = "ORIGEN")
    private String origen;
    @JoinColumn(name = "CODI_21", referencedColumnName = "CODI_21")
    @ManyToOne
    private Tsoci codi21;
    @JoinColumn(name = "CODI_47", referencedColumnName = "CODI_47")
    @ManyToOne
    private Tipodoc codi47;
    @JoinColumn(name = "CODI_08", referencedColumnName = "CODI_08")
    @ManyToOne
    private Provin codi08;
    @JoinColumn(name = "PAIS_12", referencedColumnName = "IDNACIONALIDAD")
    @ManyToOne
    private Nacionalidad pais12;
    @JoinColumn(name = "IDLOCALIDAD", referencedColumnName = "IDLOCALIDAD")
    @ManyToOne
    private Localidad idlocalidad;
    @JoinColumn(name = "IDCALLE", referencedColumnName = "IDCALLE")
    @ManyToOne
    private Calle idcalle;

    public Persona() {
    }

    public Persona(BigDecimal idpersona) {
        this.idpersona = idpersona;
    }

    public BigDecimal getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(BigDecimal idpersona) {
        this.idpersona = idpersona;
    }

    public BigInteger getCodi01() {
        return codi01;
    }

    public void setCodi01(BigInteger codi01) {
        this.codi01 = codi01;
    }

    public String getNomb12() {
        return nomb12;
    }

    public void setNomb12(String nomb12) {
        this.nomb12 = nomb12;
    }

    public String getSexo12() {
        return sexo12;
    }

    public void setSexo12(String sexo12) {
        this.sexo12 = sexo12;
    }

    public String getZona12() {
        return zona12;
    }

    public void setZona12(String zona12) {
        this.zona12 = zona12;
    }

    public Date getFena12() {
        return fena12;
    }

    public void setFena12(Date fena12) {
        this.fena12 = fena12;
    }

    public BigInteger getEciv12() {
        return eciv12;
    }

    public void setEciv12(BigInteger eciv12) {
        this.eciv12 = eciv12;
    }

    public BigInteger getNudo12() {
        return nudo12;
    }

    public void setNudo12(BigInteger nudo12) {
        this.nudo12 = nudo12;
    }

    public BigInteger getCedu12() {
        return cedu12;
    }

    public void setCedu12(BigInteger cedu12) {
        this.cedu12 = cedu12;
    }

    public String getCpos12() {
        return cpos12;
    }

    public void setCpos12(String cpos12) {
        this.cpos12 = cpos12;
    }

    public String getExpe12() {
        return expe12;
    }

    public void setExpe12(String expe12) {
        this.expe12 = expe12;
    }

    public BigInteger getCuil12() {
        return cuil12;
    }

    public void setCuil12(BigInteger cuil12) {
        this.cuil12 = cuil12;
    }

    public String getTipo12() {
        return tipo12;
    }

    public void setTipo12(String tipo12) {
        this.tipo12 = tipo12;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public BigInteger getIdbarrio() {
        return idbarrio;
    }

    public void setIdbarrio(BigInteger idbarrio) {
        this.idbarrio = idbarrio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMotivobaja() {
        return motivobaja;
    }

    public void setMotivobaja(String motivobaja) {
        this.motivobaja = motivobaja;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public BigInteger getIdorigen() {
        return idorigen;
    }

    public void setIdorigen(BigInteger idorigen) {
        this.idorigen = idorigen;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Tsoci getCodi21() {
        return codi21;
    }

    public void setCodi21(Tsoci codi21) {
        this.codi21 = codi21;
    }

    public Tipodoc getCodi47() {
        return codi47;
    }

    public void setCodi47(Tipodoc codi47) {
        this.codi47 = codi47;
    }

    public Provin getCodi08() {
        return codi08;
    }

    public void setCodi08(Provin codi08) {
        this.codi08 = codi08;
    }

    public Nacionalidad getPais12() {
        return pais12;
    }

    public void setPais12(Nacionalidad pais12) {
        this.pais12 = pais12;
    }

    public Localidad getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(Localidad idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public Calle getIdcalle() {
        return idcalle;
    }

    public void setIdcalle(Calle idcalle) {
        this.idcalle = idcalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Persona[ idpersona=" + idpersona + " ]";
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public Date getFechabaja() {
        return fechabaja;
    }

    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }

    public String getActtividadafip() {
        return acttividadafip;
    }

    public void setActtividadafip(String acttividadafip) {
        this.acttividadafip = acttividadafip;
    }

    public String getSituacioniva() {
        return situacioniva;
    }

    public void setSituacioniva(String situacioniva) {
        this.situacioniva = situacioniva;
    }

    public Date getFechainicioactividad() {
        return fechainicioactividad;
    }

    public void setFechainicioactividad(Date fechainicioactividad) {
        this.fechainicioactividad = fechainicioactividad;
    }

    public BigInteger getIibb() {
        return iibb;
    }

    public void setIibb(BigInteger iibb) {
        this.iibb = iibb;
    }

    public Date getFechacierreejercicio() {
        return fechacierreejercicio;
    }

    public void setFechacierreejercicio(Date fechacierreejercicio) {
        this.fechacierreejercicio = fechacierreejercicio;
    }

    public BigInteger getCantpersonal() {
        return cantpersonal;
    }

    public void setCantpersonal(BigInteger cantpersonal) {
        this.cantpersonal = cantpersonal;
    }

    public Double getVolumenvta() {
        return volumenvta;
    }

    public void setVolumenvta(Double volumenvta) {
        this.volumenvta = volumenvta;
    }

    public String getVolumenvtaperiodo() {
        return volumenvtaperiodo;
    }

    public void setVolumenvtaperiodo(String volumenvtaperiodo) {
        this.volumenvtaperiodo = volumenvtaperiodo;
    }

    public String getTiposector() {
        return tiposector;
    }

    public void setTiposector(String tiposector) {
        this.tiposector = tiposector;
    }

    @XmlTransient
    public Collection<Objetoi> getObjetoiCollection() {
        return objetoiCollection;
    }

    public void setObjetoiCollection(Collection<Objetoi> objetoiCollection) {
        this.objetoiCollection = objetoiCollection;
    }
    
}
