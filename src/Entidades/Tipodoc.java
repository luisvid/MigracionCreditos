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
@Table(name = "TIPODOC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipodoc.findAll", query = "SELECT t FROM Tipodoc t"),
    @NamedQuery(name = "Tipodoc.findByCodi47", query = "SELECT t FROM Tipodoc t WHERE t.codi47 = :codi47"),
    @NamedQuery(name = "Tipodoc.findByDeta47", query = "SELECT t FROM Tipodoc t WHERE t.deta47 = :deta47"),
    @NamedQuery(name = "Tipodoc.findByTido47", query = "SELECT t FROM Tipodoc t WHERE t.tido47 = :tido47")})
public class Tipodoc implements Serializable {
    @OneToMany(mappedBy = "codi47")
    private List<Persona> personaList;
    @Column(name = "tipoDocAFIP")
    private BigInteger tipoDocAFIP;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODI_47")
    private BigDecimal codi47;
    @Column(name = "DETA_47")
    private String deta47;
    @Column(name = "TIDO_47")
    private String tido47;
    @OneToMany(mappedBy = "codi47")
    private Collection<Persona> personaCollection;

    public Tipodoc() {
    }

    public Tipodoc(BigDecimal codi47) {
        this.codi47 = codi47;
    }

    public BigDecimal getCodi47() {
        return codi47;
    }

    public void setCodi47(BigDecimal codi47) {
        this.codi47 = codi47;
    }

    public String getDeta47() {
        return deta47;
    }

    public void setDeta47(String deta47) {
        this.deta47 = deta47;
    }

    public String getTido47() {
        return tido47;
    }

    public void setTido47(String tido47) {
        this.tido47 = tido47;
    }

    @XmlTransient
    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codi47 != null ? codi47.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipodoc)) {
            return false;
        }
        Tipodoc other = (Tipodoc) object;
        if ((this.codi47 == null && other.codi47 != null) || (this.codi47 != null && !this.codi47.equals(other.codi47))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Tipodoc[ codi47=" + codi47 + " ]";
    }

    public BigInteger getTipoDocAFIP() {
        return tipoDocAFIP;
    }

    public void setTipoDocAFIP(BigInteger tipoDocAFIP) {
        this.tipoDocAFIP = tipoDocAFIP;
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }
    
}
