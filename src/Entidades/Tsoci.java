/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "TSOCI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tsoci.findAll", query = "SELECT t FROM Tsoci t"),
    @NamedQuery(name = "Tsoci.findByCodi21", query = "SELECT t FROM Tsoci t WHERE t.codi21 = :codi21"),
    @NamedQuery(name = "Tsoci.findByAbre21", query = "SELECT t FROM Tsoci t WHERE t.abre21 = :abre21"),
    @NamedQuery(name = "Tsoci.findByDeta21", query = "SELECT t FROM Tsoci t WHERE t.deta21 = :deta21")})
public class Tsoci implements Serializable {
    @OneToMany(mappedBy = "codi21")
    private List<Persona> personaList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "CODI_21")
    private BigDecimal codi21;
    @Column(name = "ABRE_21")
    private String abre21;
    @Column(name = "DETA_21")
    private String deta21;
    @OneToMany(mappedBy = "codi21")
    private Collection<Persona> personaCollection;

    public Tsoci() {
    }

    public Tsoci(BigDecimal codi21) {
        this.codi21 = codi21;
    }

    public BigDecimal getCodi21() {
        return codi21;
    }

    public void setCodi21(BigDecimal codi21) {
        this.codi21 = codi21;
    }

    public String getAbre21() {
        return abre21;
    }

    public void setAbre21(String abre21) {
        this.abre21 = abre21;
    }

    public String getDeta21() {
        return deta21;
    }

    public void setDeta21(String deta21) {
        this.deta21 = deta21;
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
        hash += (codi21 != null ? codi21.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tsoci)) {
            return false;
        }
        Tsoci other = (Tsoci) object;
        if ((this.codi21 == null && other.codi21 != null) || (this.codi21 != null && !this.codi21.equals(other.codi21))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Tsoci[ codi21=" + codi21 + " ]";
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }
    
}
