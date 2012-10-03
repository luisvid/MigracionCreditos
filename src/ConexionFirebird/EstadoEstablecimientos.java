/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConexionFirebird;

import java.io.Serializable;
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
@Table(name = "ESTADO_ESTABLECIMIENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoEstablecimientos.findAll", query = "SELECT e FROM EstadoEstablecimientos e"),
    @NamedQuery(name = "EstadoEstablecimientos.findByNuri", query = "SELECT e FROM EstadoEstablecimientos e WHERE e.nuri = :nuri"),
    @NamedQuery(name = "EstadoEstablecimientos.findByDescrip", query = "SELECT e FROM EstadoEstablecimientos e WHERE e.descrip = :descrip")})
public class EstadoEstablecimientos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NURI")
    private Integer nuri;
    @Basic(optional = false)
    @Column(name = "DESCRIP")
    private String descrip;
    @OneToMany(mappedBy = "estadoNuri")
    private List<Personas> personasList;

    public EstadoEstablecimientos() {
    }

    public EstadoEstablecimientos(Integer nuri) {
        this.nuri = nuri;
    }

    public EstadoEstablecimientos(Integer nuri, String descrip) {
        this.nuri = nuri;
        this.descrip = descrip;
    }

    public Integer getNuri() {
        return nuri;
    }

    public void setNuri(Integer nuri) {
        this.nuri = nuri;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nuri != null ? nuri.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoEstablecimientos)) {
            return false;
        }
        EstadoEstablecimientos other = (EstadoEstablecimientos) object;
        if ((this.nuri == null && other.nuri != null) || (this.nuri != null && !this.nuri.equals(other.nuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.EstadoEstablecimientos[ nuri=" + nuri + " ]";
    }
    
}
