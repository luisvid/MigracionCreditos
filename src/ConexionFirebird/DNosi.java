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
@Table(name = "D_NOSI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DNosi.findAll", query = "SELECT d FROM DNosi d"),
    @NamedQuery(name = "DNosi.findByCod", query = "SELECT d FROM DNosi d WHERE d.cod = :cod"),
    @NamedQuery(name = "DNosi.findByDescrip", query = "SELECT d FROM DNosi d WHERE d.descrip = :descrip")})
public class DNosi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "COD")
    private String cod;
    @Basic(optional = false)
    @Column(name = "DESCRIP")
    private String descrip;
    @OneToMany(mappedBy = "solicitaCuit")
    private List<CategoriaIva> categoriaIvaList;
    @OneToMany(mappedBy = "baja")
    private List<Personas> personasList;
    @OneToMany(mappedBy = "inhabilitado")
    private List<Personas> personasList1;

    public DNosi() {
    }

    public DNosi(String cod) {
        this.cod = cod;
    }

    public DNosi(String cod, String descrip) {
        this.cod = cod;
        this.descrip = descrip;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    @XmlTransient
    public List<CategoriaIva> getCategoriaIvaList() {
        return categoriaIvaList;
    }

    public void setCategoriaIvaList(List<CategoriaIva> categoriaIvaList) {
        this.categoriaIvaList = categoriaIvaList;
    }

    @XmlTransient
    public List<Personas> getPersonasList() {
        return personasList;
    }

    public void setPersonasList(List<Personas> personasList) {
        this.personasList = personasList;
    }

    @XmlTransient
    public List<Personas> getPersonasList1() {
        return personasList1;
    }

    public void setPersonasList1(List<Personas> personasList1) {
        this.personasList1 = personasList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cod != null ? cod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DNosi)) {
            return false;
        }
        DNosi other = (DNosi) object;
        if ((this.cod == null && other.cod != null) || (this.cod != null && !this.cod.equals(other.cod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.DNosi[ cod=" + cod + " ]";
    }
    
}
