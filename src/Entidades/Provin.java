/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "PROVIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Provin.findAll", query = "SELECT p FROM Provin p"),
    @NamedQuery(name = "Provin.findByCodi08", query = "SELECT p FROM Provin p WHERE p.codi08 = :codi08"),
    @NamedQuery(name = "Provin.findByDeta08", query = "SELECT p FROM Provin p WHERE p.deta08 = :deta08"),
    @NamedQuery(name = "Provin.findByAbre08", query = "SELECT p FROM Provin p WHERE p.abre08 = :abre08"),
    @NamedQuery(name = "Provin.findByCdgi08", query = "SELECT p FROM Provin p WHERE p.cdgi08 = :cdgi08")})
public class Provin implements Serializable {
    @OneToMany(mappedBy = "codi08")
    private List<Persona> personaList;
    @OneToMany(mappedBy = "codi08")
    private List<Localidad> localidadList;
    @OneToMany(mappedBy = "provinciaCODI08")
    private List<Domicilio> domicilioList;
    @Column(name = "codigoAFIP")
    private BigInteger codigoAFIP;
    @Column(name = "codigoDGR")
    private BigInteger codigoDGR;
    @OneToMany(mappedBy = "provinciaCODI08")
    private Collection<Domicilio> domicilioCollection;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODI_08")
    private BigDecimal codi08;
    @Basic(optional = false)
    @Column(name = "DETA_08")
    private String deta08;
    @Basic(optional = false)
    @Column(name = "ABRE_08")
    private String abre08;
    @Column(name = "CDGI_08")
    private BigInteger cdgi08;
    @OneToMany(mappedBy = "codi08")
    private Collection<Persona> personaCollection;
    @OneToMany(mappedBy = "codi08")
    private Collection<Localidad> localidadCollection;
    @JoinColumn(name = "idpais", referencedColumnName = "idPais")
    @ManyToOne
    private Pais idpais;

    public Provin() {
    }

    public Provin(BigDecimal codi08) {
        this.codi08 = codi08;
    }

    public Provin(BigDecimal codi08, String deta08, String abre08) {
        this.codi08 = codi08;
        this.deta08 = deta08;
        this.abre08 = abre08;
    }

    public BigDecimal getCodi08() {
        return codi08;
    }

    public void setCodi08(BigDecimal codi08) {
        this.codi08 = codi08;
    }

    public String getDeta08() {
        return deta08;
    }

    public void setDeta08(String deta08) {
        this.deta08 = deta08;
    }

    public String getAbre08() {
        return abre08;
    }

    public void setAbre08(String abre08) {
        this.abre08 = abre08;
    }

    public BigInteger getCdgi08() {
        return cdgi08;
    }

    public void setCdgi08(BigInteger cdgi08) {
        this.cdgi08 = cdgi08;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @XmlTransient
    public Collection<Localidad> getLocalidadCollection() {
        return localidadCollection;
    }

    public void setLocalidadCollection(Collection<Localidad> localidadCollection) {
        this.localidadCollection = localidadCollection;
    }

    public Pais getIdpais() {
        return idpais;
    }

    public void setIdpais(Pais idpais) {
        this.idpais = idpais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codi08 != null ? codi08.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Provin)) {
            return false;
        }
        Provin other = (Provin) object;
        if ((this.codi08 == null && other.codi08 != null) || (this.codi08 != null && !this.codi08.equals(other.codi08))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Provin[ codi08=" + codi08 + " ]";
    }

    @XmlTransient
    public Collection<Domicilio> getDomicilioCollection() {
        return domicilioCollection;
    }

    public void setDomicilioCollection(Collection<Domicilio> domicilioCollection) {
        this.domicilioCollection = domicilioCollection;
    }

    public BigInteger getCodigoAFIP() {
        return codigoAFIP;
    }

    public void setCodigoAFIP(BigInteger codigoAFIP) {
        this.codigoAFIP = codigoAFIP;
    }

    public BigInteger getCodigoDGR() {
        return codigoDGR;
    }

    public void setCodigoDGR(BigInteger codigoDGR) {
        this.codigoDGR = codigoDGR;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }

    @XmlTransient
    public List<Localidad> getLocalidadList() {
        return localidadList;
    }

    public void setLocalidadList(List<Localidad> localidadList) {
        this.localidadList = localidadList;
    }

    @XmlTransient
    public List<Domicilio> getDomicilioList() {
        return domicilioList;
    }

    public void setDomicilioList(List<Domicilio> domicilioList) {
        this.domicilioList = domicilioList;
    }
    
}
