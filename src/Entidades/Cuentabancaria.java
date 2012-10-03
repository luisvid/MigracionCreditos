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
@Table(name = "cuentabancaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuentabancaria.findAll", query = "SELECT c FROM Cuentabancaria c"),
    @NamedQuery(name = "Cuentabancaria.findByIdCuentaBancaria", query = "SELECT c FROM Cuentabancaria c WHERE c.idCuentaBancaria = :idCuentaBancaria"),
    @NamedQuery(name = "Cuentabancaria.findByCbu", query = "SELECT c FROM Cuentabancaria c WHERE c.cbu = :cbu"),
    @NamedQuery(name = "Cuentabancaria.findByFechaApertura", query = "SELECT c FROM Cuentabancaria c WHERE c.fechaApertura = :fechaApertura"),
    @NamedQuery(name = "Cuentabancaria.findByNroCuentaBancaria", query = "SELECT c FROM Cuentabancaria c WHERE c.nroCuentaBancaria = :nroCuentaBancaria"),
    @NamedQuery(name = "Cuentabancaria.findByPersonaIDPERSONA", query = "SELECT c FROM Cuentabancaria c WHERE c.personaIDPERSONA = :personaIDPERSONA"),
    @NamedQuery(name = "Cuentabancaria.findByBanco", query = "SELECT c FROM Cuentabancaria c WHERE c.banco = :banco"),
    @NamedQuery(name = "Cuentabancaria.findByTipoCuentaBancaria", query = "SELECT c FROM Cuentabancaria c WHERE c.tipoCuentaBancaria = :tipoCuentaBancaria")})
public class Cuentabancaria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idCuentaBancaria")
    private BigInteger idCuentaBancaria;
    @Column(name = "cbu")
    private String cbu;
    @Column(name = "fechaApertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaApertura;
    @Column(name = "nroCuentaBancaria")
    private String nroCuentaBancaria;
    @Column(name = "persona_IDPERSONA")
    private BigInteger personaIDPERSONA;
    @Column(name = "banco")
    private String banco;
    @Column(name = "tipoCuentaBancaria")
    private String tipoCuentaBancaria;

    public Cuentabancaria() {
    }

    public BigInteger getIdCuentaBancaria() {
        return idCuentaBancaria;
    }

    public void setIdCuentaBancaria(BigInteger idCuentaBancaria) {
        this.idCuentaBancaria = idCuentaBancaria;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getNroCuentaBancaria() {
        return nroCuentaBancaria;
    }

    public void setNroCuentaBancaria(String nroCuentaBancaria) {
        this.nroCuentaBancaria = nroCuentaBancaria;
    }

    public BigInteger getPersonaIDPERSONA() {
        return personaIDPERSONA;
    }

    public void setPersonaIDPERSONA(BigInteger personaIDPERSONA) {
        this.personaIDPERSONA = personaIDPERSONA;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(String tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }
    
}
