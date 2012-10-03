/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "vpersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vpersona.findAll", query = "SELECT v FROM Vpersona v"),
    @NamedQuery(name = "Vpersona.findByIdpersona", query = "SELECT v FROM Vpersona v WHERE v.idpersona = :idpersona"),
    @NamedQuery(name = "Vpersona.findByCodi01", query = "SELECT v FROM Vpersona v WHERE v.codi01 = :codi01"),
    @NamedQuery(name = "Vpersona.findByNomb12", query = "SELECT v FROM Vpersona v WHERE v.nomb12 = :nomb12"),
    @NamedQuery(name = "Vpersona.findBySexo12", query = "SELECT v FROM Vpersona v WHERE v.sexo12 = :sexo12"),
    @NamedQuery(name = "Vpersona.findByZona12", query = "SELECT v FROM Vpersona v WHERE v.zona12 = :zona12"),
    @NamedQuery(name = "Vpersona.findByFena12", query = "SELECT v FROM Vpersona v WHERE v.fena12 = :fena12"),
    @NamedQuery(name = "Vpersona.findByEciv12", query = "SELECT v FROM Vpersona v WHERE v.eciv12 = :eciv12"),
    @NamedQuery(name = "Vpersona.findByNudo12", query = "SELECT v FROM Vpersona v WHERE v.nudo12 = :nudo12"),
    @NamedQuery(name = "Vpersona.findByCedu12", query = "SELECT v FROM Vpersona v WHERE v.cedu12 = :cedu12"),
    @NamedQuery(name = "Vpersona.findByCpos12", query = "SELECT v FROM Vpersona v WHERE v.cpos12 = :cpos12"),
    @NamedQuery(name = "Vpersona.findByExpe12", query = "SELECT v FROM Vpersona v WHERE v.expe12 = :expe12"),
    @NamedQuery(name = "Vpersona.findByPais12", query = "SELECT v FROM Vpersona v WHERE v.pais12 = :pais12"),
    @NamedQuery(name = "Vpersona.findByCuil12", query = "SELECT v FROM Vpersona v WHERE v.cuil12 = :cuil12"),
    @NamedQuery(name = "Vpersona.findByCodi08", query = "SELECT v FROM Vpersona v WHERE v.codi08 = :codi08"),
    @NamedQuery(name = "Vpersona.findByCodi47", query = "SELECT v FROM Vpersona v WHERE v.codi47 = :codi47"),
    @NamedQuery(name = "Vpersona.findByIdlocalidad", query = "SELECT v FROM Vpersona v WHERE v.idlocalidad = :idlocalidad"),
    @NamedQuery(name = "Vpersona.findByTipo12", query = "SELECT v FROM Vpersona v WHERE v.tipo12 = :tipo12"),
    @NamedQuery(name = "Vpersona.findByIdcalle", query = "SELECT v FROM Vpersona v WHERE v.idcalle = :idcalle"),
    @NamedQuery(name = "Vpersona.findByCalle", query = "SELECT v FROM Vpersona v WHERE v.calle = :calle"),
    @NamedQuery(name = "Vpersona.findByNumero", query = "SELECT v FROM Vpersona v WHERE v.numero = :numero"),
    @NamedQuery(name = "Vpersona.findByPiso", query = "SELECT v FROM Vpersona v WHERE v.piso = :piso"),
    @NamedQuery(name = "Vpersona.findByDpto", query = "SELECT v FROM Vpersona v WHERE v.dpto = :dpto"),
    @NamedQuery(name = "Vpersona.findByIdbarrio", query = "SELECT v FROM Vpersona v WHERE v.idbarrio = :idbarrio"),
    @NamedQuery(name = "Vpersona.findByLocalidad", query = "SELECT v FROM Vpersona v WHERE v.localidad = :localidad"),
    @NamedQuery(name = "Vpersona.findByCodi21", query = "SELECT v FROM Vpersona v WHERE v.codi21 = :codi21"),
    @NamedQuery(name = "Vpersona.findByFechaalta", query = "SELECT v FROM Vpersona v WHERE v.fechaalta = :fechaalta"),
    @NamedQuery(name = "Vpersona.findByFechabaja", query = "SELECT v FROM Vpersona v WHERE v.fechabaja = :fechabaja"),
    @NamedQuery(name = "Vpersona.findByMotivobaja", query = "SELECT v FROM Vpersona v WHERE v.motivobaja = :motivobaja"),
    @NamedQuery(name = "Vpersona.findByRazonsocial", query = "SELECT v FROM Vpersona v WHERE v.razonsocial = :razonsocial")})
public class Vpersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "IDPERSONA", nullable = false)
    @Id
    private BigInteger idpersona;
    @Column(name = "CODI_01")
    private BigInteger codi01;
    @Column(name = "NOMB_12", length = 255)
    private String nomb12;
    @Column(name = "SEXO_12", length = 255)
    private String sexo12;
    @Column(name = "ZONA_12", length = 255)
    private String zona12;
    @Column(name = "FENA_12")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fena12;
    @Column(name = "ECIV_12")
    private BigInteger eciv12;
    @Column(name = "NUDO_12")
    private BigInteger nudo12;
    @Column(name = "CEDU_12")
    private BigInteger cedu12;
    @Column(name = "CPOS_12", length = 255)
    private String cpos12;
    @Column(name = "EXPE_12", length = 255)
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
    @Column(name = "TIPO_12", length = 255)
    private String tipo12;
    @Column(name = "IDCALLE")
    private BigInteger idcalle;
    @Column(name = "CALLE", length = 255)
    private String calle;
    @Column(name = "NUMERO", length = 255)
    private String numero;
    @Column(name = "PISO", length = 255)
    private String piso;
    @Column(name = "DPTO", length = 255)
    private String dpto;
    @Column(name = "IDBARRIO")
    private BigInteger idbarrio;
    @Column(name = "LOCALIDAD", length = 255)
    private String localidad;
    @Column(name = "CODI_21")
    private BigInteger codi21;
    @Column(name = "FECHAALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaalta;
    @Column(name = "FECHABAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechabaja;
    @Column(name = "MOTIVOBAJA", length = 255)
    private String motivobaja;
    @Column(name = "RAZONSOCIAL", length = 255)
    private String razonsocial;

    public Vpersona() {
    }

    public BigInteger getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(BigInteger idpersona) {
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
    
}
