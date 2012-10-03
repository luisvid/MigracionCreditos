/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
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
@Table(name = "PersonaRev")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaRev.findAll", query = "SELECT p FROM PersonaRev p"),
    @NamedQuery(name = "PersonaRev.findByIdpersona", query = "SELECT p FROM PersonaRev p WHERE p.idpersona = :idpersona"),
    @NamedQuery(name = "PersonaRev.findByCodi01", query = "SELECT p FROM PersonaRev p WHERE p.codi01 = :codi01"),
    @NamedQuery(name = "PersonaRev.findByNom12", query = "SELECT p FROM PersonaRev p WHERE p.nom12 = :nom12"),
    @NamedQuery(name = "PersonaRev.findBySexo12", query = "SELECT p FROM PersonaRev p WHERE p.sexo12 = :sexo12"),
    @NamedQuery(name = "PersonaRev.findByZona12", query = "SELECT p FROM PersonaRev p WHERE p.zona12 = :zona12"),
    @NamedQuery(name = "PersonaRev.findByFena12", query = "SELECT p FROM PersonaRev p WHERE p.fena12 = :fena12"),
    @NamedQuery(name = "PersonaRev.findByEciv12", query = "SELECT p FROM PersonaRev p WHERE p.eciv12 = :eciv12"),
    @NamedQuery(name = "PersonaRev.findByNudo12", query = "SELECT p FROM PersonaRev p WHERE p.nudo12 = :nudo12"),
    @NamedQuery(name = "PersonaRev.findByCedu12", query = "SELECT p FROM PersonaRev p WHERE p.cedu12 = :cedu12"),
    @NamedQuery(name = "PersonaRev.findByCpos12", query = "SELECT p FROM PersonaRev p WHERE p.cpos12 = :cpos12"),
    @NamedQuery(name = "PersonaRev.findByExpe12", query = "SELECT p FROM PersonaRev p WHERE p.expe12 = :expe12"),
    @NamedQuery(name = "PersonaRev.findByCuil12", query = "SELECT p FROM PersonaRev p WHERE p.cuil12 = :cuil12"),
    @NamedQuery(name = "PersonaRev.findByTipo12", query = "SELECT p FROM PersonaRev p WHERE p.tipo12 = :tipo12"),
    @NamedQuery(name = "PersonaRev.findByCalle", query = "SELECT p FROM PersonaRev p WHERE p.calle = :calle"),
    @NamedQuery(name = "PersonaRev.findByNumero", query = "SELECT p FROM PersonaRev p WHERE p.numero = :numero"),
    @NamedQuery(name = "PersonaRev.findByNombreDir", query = "SELECT p FROM PersonaRev p WHERE p.nom12 = :nom12 and p.calle = :calle and p.numero = :numero"),
    @NamedQuery(name = "PersonaRev.findByPiso", query = "SELECT p FROM PersonaRev p WHERE p.piso = :piso"),
    @NamedQuery(name = "PersonaRev.findByDpto", query = "SELECT p FROM PersonaRev p WHERE p.dpto = :dpto"),
    @NamedQuery(name = "PersonaRev.findByIdbarrio", query = "SELECT p FROM PersonaRev p WHERE p.idbarrio = :idbarrio"),
    @NamedQuery(name = "PersonaRev.findByLocalidad", query = "SELECT p FROM PersonaRev p WHERE p.localidad = :localidad"),
    @NamedQuery(name = "PersonaRev.findByFechaalta", query = "SELECT p FROM PersonaRev p WHERE p.fechaalta = :fechaalta"),
    @NamedQuery(name = "PersonaRev.findByFechabaja", query = "SELECT p FROM PersonaRev p WHERE p.fechabaja = :fechabaja"),
    @NamedQuery(name = "PersonaRev.findByMotivobaja", query = "SELECT p FROM PersonaRev p WHERE p.motivobaja = :motivobaja"),
    @NamedQuery(name = "PersonaRev.findByRazonsocial", query = "SELECT p FROM PersonaRev p WHERE p.razonsocial = :razonsocial"),
    @NamedQuery(name = "PersonaRev.findByIdorigen", query = "SELECT p FROM PersonaRev p WHERE p.idorigen = :idorigen"),
    @NamedQuery(name = "PersonaRev.findByDniNombre", query = "SELECT p FROM PersonaRev p WHERE p.nom12 = :nom12 AND p.nudo12 = :nudo12"),
    @NamedQuery(name = "PersonaRev.findByCuilNombre", query = "SELECT p FROM PersonaRev p WHERE p.nom12 = :nom12 AND p.cuil12 = :cuil12"),
    @NamedQuery(name = "PersonaRev.findByCuilRazonSocial", query = "SELECT p FROM PersonaRev p WHERE p.razonsocial = :razonsocial AND p.cuil12 = :cuil12"),
    @NamedQuery(name = "PersonaRev.findXCuit", query = "SELECT p FROM PersonaRev p WHERE p.cuil12 = :cuil12 AND p.idpersona > 4857"), 
    @NamedQuery(name = "PersonaRev.findByOrigen", query = "SELECT p FROM PersonaRev p WHERE p.origen = :origen")})
public class PersonaRev implements Serializable {
    @Column(name = "FECHAALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaalta;
    @Column(name = "FECHABAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabaja;
    @OneToMany(mappedBy = "personaIDPERSONA")
    private List<Domicilio> domicilioList;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDPERSONA")
    private BigDecimal idpersona;
    @Column(name = "CODI_01")
    private BigInteger codi01;
    @Column(name = "NOM_12")
    private String nom12;
    @Column(name = "SEXO_12")
    private String sexo12;
    @Column(name = "ZONA_12")
    private String zona12;
    @Column(name = "FENA_12")
    private String fena12;
    @Column(name = "ECIV_12")
    private BigInteger eciv12;
    @Column(name = "NUDO_12")
    private String nudo12;
    @Column(name = "CEDU_12")
    private BigInteger cedu12;
    @Column(name = "CPOS_12")
    private String cpos12;
    @Column(name = "EXPE_12")
    private String expe12;
    @Column(name = "PAIS_12")
    private BigInteger pais12;
    @Column(name = "CUIL_12")
    private BigInteger cuil12;
    @Column(name = "CODI_08")
    private BigInteger codi08;
    @Column(name = "CODI_47")
    private BigInteger codi47;
    @Column(name = "IDLOCALIDAD")
    private BigInteger idlocalidad;
    @Column(name = "TIPO_12")
    private String tipo12;
    @Column(name = "IDCALLE")
    private BigInteger idcalle;
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
    @Column(name = "CODI_21")
    private BigInteger codi21;
    @Column(name = "MOTIVOBAJA")
    private String motivobaja;
    @Column(name = "RAZONSOCIAL")
    private String razonsocial;
    @Column(name = "IDORIGEN")
    private BigInteger idorigen;
    @Column(name = "ORIGEN")
    private String origen;

    public PersonaRev() {
    }

    public PersonaRev(BigDecimal idpersona) {
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

    public String getNom12() {
        return nom12;
    }

    public void setNom12(String nom12) {
        this.nom12 = nom12;
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

    public String getFena12() {
        return fena12;
    }

    public void setFena12(String fena12) {
        this.fena12 = fena12;
    }

    public BigInteger getEciv12() {
        return eciv12;
    }

    public void setEciv12(BigInteger eciv12) {
        this.eciv12 = eciv12;
    }

    public String getNudo12() {
        return nudo12;
    }

    public void setNudo12(String nudo12) {
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

    public BigInteger getPais12() {
        return pais12;
    }

    public void setPais12(BigInteger pais12) {
        this.pais12 = pais12;
    }

    public BigInteger getCuil12() {
        return cuil12;
    }

    public void setCuil12(BigInteger cuil12) {
        this.cuil12 = cuil12;
    }

    public BigInteger getCodi08() {
        return codi08;
    }

    public void setCodi08(BigInteger codi08) {
        this.codi08 = codi08;
    }

    public BigInteger getCodi47() {
        return codi47;
    }

    public void setCodi47(BigInteger codi47) {
        this.codi47 = codi47;
    }

    public BigInteger getIdlocalidad() {
        return idlocalidad;
    }

    public void setIdlocalidad(BigInteger idlocalidad) {
        this.idlocalidad = idlocalidad;
    }

    public String getTipo12() {
        return tipo12;
    }

    public void setTipo12(String tipo12) {
        this.tipo12 = tipo12;
    }

    public BigInteger getIdcalle() {
        return idcalle;
    }

    public void setIdcalle(BigInteger idcalle) {
        this.idcalle = idcalle;
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

    public BigInteger getCodi21() {
        return codi21;
    }

    public void setCodi21(BigInteger codi21) {
        this.codi21 = codi21;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaRev)) {
            return false;
        }
        PersonaRev other = (PersonaRev) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PersonaRev[ idpersona=" + idpersona + " ]";
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

    @XmlTransient
    public List<Domicilio> getDomicilioList() {
        return domicilioList;
    }

    public void setDomicilioList(List<Domicilio> domicilioList) {
        this.domicilioList = domicilioList;
    }
}
