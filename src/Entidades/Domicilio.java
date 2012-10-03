/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "Domicilio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Domicilio.findAll", query = "SELECT d FROM Domicilio d"),
    @NamedQuery(name = "Domicilio.findById", query = "SELECT d FROM Domicilio d WHERE d.id = :id"),
    @NamedQuery(name = "Domicilio.findByBarrioNom", query = "SELECT d FROM Domicilio d WHERE d.barrioNom = :barrioNom"),
    @NamedQuery(name = "Domicilio.findByCalleNom", query = "SELECT d FROM Domicilio d WHERE d.calleNom = :calleNom"),
    @NamedQuery(name = "Domicilio.findByDepartamentoNom", query = "SELECT d FROM Domicilio d WHERE d.departamentoNom = :departamentoNom"),
    @NamedQuery(name = "Domicilio.findByLocalidadNom", query = "SELECT d FROM Domicilio d WHERE d.localidadNom = :localidadNom"),
    @NamedQuery(name = "Domicilio.findByLote", query = "SELECT d FROM Domicilio d WHERE d.lote = :lote"),
    @NamedQuery(name = "Domicilio.findByManzana", query = "SELECT d FROM Domicilio d WHERE d.manzana = :manzana"),
    @NamedQuery(name = "Domicilio.findByNumero", query = "SELECT d FROM Domicilio d WHERE d.numero = :numero"),
    @NamedQuery(name = "Domicilio.findByPersona", query = "SELECT d FROM Domicilio d WHERE d.personaIDPERSONA = :persona"),
    @NamedQuery(name = "Domicilio.findByProvinciaNom", query = "SELECT d FROM Domicilio d WHERE d.provinciaNom = :provinciaNom"),
    @NamedQuery(name = "Domicilio.findByTipo", query = "SELECT d FROM Domicilio d WHERE d.tipo = :tipo")})
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "barrioNom")
    private String barrioNom;
    @Column(name = "calleNom")
    private String calleNom;
    @Column(name = "departamentoNom")
    private String departamentoNom;
    @Column(name = "localidadNom")
    private String localidadNom;
    @Column(name = "lote")
    private String lote;
    @Column(name = "manzana")
    private String manzana;
    @Column(name = "numero")
    private String numero;
    @Column(name = "provinciaNom")
    private String provinciaNom;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "provincia_CODI_08", referencedColumnName = "CODI_08")
    @ManyToOne
    private Provin provinciaCODI08;
    @JoinColumn(name = "persona_IDPERSONA", referencedColumnName = "IDPERSONA")
    @ManyToOne
    private PersonaRev personaIDPERSONA;
    //  @JoinColumn(name = "persona_IDPERSONA", referencedColumnName = "IDPERSONA")
    //  @ManyToOne
    // private Persona personaIDPERSONA1;
    @JoinColumn(name = "localidad_IDLOCALIDAD", referencedColumnName = "IDLOCALIDAD")
    @ManyToOne
    private Localidad localidadIDLOCALIDAD;
    @JoinColumn(name = "calle_IDCALLE", referencedColumnName = "IDCALLE")
    @ManyToOne
    private Calle calleIDCALLE;
    @JoinColumn(name = "barrio_CODI_BRR", referencedColumnName = "CODI_BRR")
    @ManyToOne
    private Barrio barrioCODIBRR;

    public Domicilio() {
    }

    public Domicilio(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getBarrioNom() {
        return barrioNom;
    }

    public void setBarrioNom(String barrioNom) {
        this.barrioNom = barrioNom;
    }

    public String getCalleNom() {
        return calleNom;
    }

    public void setCalleNom(String calleNom) {
        this.calleNom = calleNom;
    }

    public String getDepartamentoNom() {
        return departamentoNom;
    }

    public void setDepartamentoNom(String departamentoNom) {
        this.departamentoNom = departamentoNom;
    }

    public String getLocalidadNom() {
        return localidadNom;
    }

    public void setLocalidadNom(String localidadNom) {
        this.localidadNom = localidadNom;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getProvinciaNom() {
        return provinciaNom;
    }

    public void setProvinciaNom(String provinciaNom) {
        this.provinciaNom = provinciaNom;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Provin getProvinciaCODI08() {
        return provinciaCODI08;
    }

    public void setProvinciaCODI08(Provin provinciaCODI08) {
        this.provinciaCODI08 = provinciaCODI08;
    }

    public PersonaRev getPersonaIDPERSONA() {
        return personaIDPERSONA;
    }

    public void setPersonaIDPERSONA(PersonaRev personaIDPERSONA) {
        this.personaIDPERSONA = personaIDPERSONA;
    }

    //  public Persona getPersonaIDPERSONA1() {
    //      return personaIDPERSONA1;
    //  }
    //  public void setPersonaIDPERSONA1(Persona personaIDPERSONA1) {
    //      this.personaIDPERSONA1 = personaIDPERSONA1;
    //   }
    public Localidad getLocalidadIDLOCALIDAD() {
        return localidadIDLOCALIDAD;
    }

    public void setLocalidadIDLOCALIDAD(Localidad localidadIDLOCALIDAD) {
        this.localidadIDLOCALIDAD = localidadIDLOCALIDAD;
    }

    public Calle getCalleIDCALLE() {
        return calleIDCALLE;
    }

    public void setCalleIDCALLE(Calle calleIDCALLE) {
        this.calleIDCALLE = calleIDCALLE;
    }

    public Barrio getBarrioCODIBRR() {
        return barrioCODIBRR;
    }

    public void setBarrioCODIBRR(Barrio barrioCODIBRR) {
        this.barrioCODIBRR = barrioCODIBRR;
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
        if (!(object instanceof Domicilio)) {
            return false;
        }
        Domicilio other = (Domicilio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Domicilio[ id=" + id + " ]";
    }
}
