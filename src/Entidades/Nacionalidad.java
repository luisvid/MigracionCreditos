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
@Table(name = "NACIONALIDAD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nacionalidad.findAll", query = "SELECT n FROM Nacionalidad n"),
    @NamedQuery(name = "Nacionalidad.findByIdnacionalidad", query = "SELECT n FROM Nacionalidad n WHERE n.idnacionalidad = :idnacionalidad"),
    @NamedQuery(name = "Nacionalidad.findByNacionalidad", query = "SELECT n FROM Nacionalidad n WHERE n.nacionalidad = :nacionalidad")})
public class Nacionalidad implements Serializable {
    @OneToMany(mappedBy = "pais12")
    private List<Persona> personaList;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "IDNACIONALIDAD")
    private BigDecimal idnacionalidad;
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;
    @OneToMany(mappedBy = "pais12")
    private Collection<Persona> personaCollection;

    public Nacionalidad() {
    }

    public Nacionalidad(BigDecimal idnacionalidad) {
        this.idnacionalidad = idnacionalidad;
    }

    public BigDecimal getIdnacionalidad() {
        return idnacionalidad;
    }

    public void setIdnacionalidad(BigDecimal idnacionalidad) {
        this.idnacionalidad = idnacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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
        hash += (idnacionalidad != null ? idnacionalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacionalidad)) {
            return false;
        }
        Nacionalidad other = (Nacionalidad) object;
        if ((this.idnacionalidad == null && other.idnacionalidad != null) || (this.idnacionalidad != null && !this.idnacionalidad.equals(other.idnacionalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Nacionalidad[ idnacionalidad=" + idnacionalidad + " ]";
    }

    @XmlTransient
    public List<Persona> getPersonaList() {
        return personaList;
    }

    public void setPersonaList(List<Persona> personaList) {
        this.personaList = personaList;
    }
    
}
