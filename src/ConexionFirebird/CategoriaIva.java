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
@Table(name = "CATEGORIA_IVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoriaIva.findAll", query = "SELECT c FROM CategoriaIva c"),
    @NamedQuery(name = "CategoriaIva.findByNuri", query = "SELECT c FROM CategoriaIva c WHERE c.nuri = :nuri"),
    @NamedQuery(name = "CategoriaIva.findByCodigo", query = "SELECT c FROM CategoriaIva c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CategoriaIva.findByDescrip", query = "SELECT c FROM CategoriaIva c WHERE c.descrip = :descrip")})
public class CategoriaIva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NURI")
    private Integer nuri;
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "DESCRIP")
    private String descrip;
    @JoinColumn(name = "SOLICITA_CUIT", referencedColumnName = "COD")
    @ManyToOne
    private DNosi solicitaCuit;
    @OneToMany(mappedBy = "categoriaIvaNuri")
    private List<Personas> personasList;

    public CategoriaIva() {
    }

    public CategoriaIva(Integer nuri) {
        this.nuri = nuri;
    }

    public CategoriaIva(Integer nuri, String codigo, String descrip) {
        this.nuri = nuri;
        this.codigo = codigo;
        this.descrip = descrip;
    }

    public Integer getNuri() {
        return nuri;
    }

    public void setNuri(Integer nuri) {
        this.nuri = nuri;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public DNosi getSolicitaCuit() {
        return solicitaCuit;
    }

    public void setSolicitaCuit(DNosi solicitaCuit) {
        this.solicitaCuit = solicitaCuit;
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
        if (!(object instanceof CategoriaIva)) {
            return false;
        }
        CategoriaIva other = (CategoriaIva) object;
        if ((this.nuri == null && other.nuri != null) || (this.nuri != null && !this.nuri.equals(other.nuri))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ConexionFirebird.CategoriaIva[ nuri=" + nuri + " ]";
    }
    
}
