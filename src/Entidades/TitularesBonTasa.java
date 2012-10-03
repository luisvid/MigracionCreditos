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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luisv
 */
@Entity
@Table(name = "TitularesBonTasa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TitularesBonTasa.findAll", query = "SELECT t FROM TitularesBonTasa t"),
    @NamedQuery(name = "TitularesBonTasa.findById", query = "SELECT t FROM TitularesBonTasa t WHERE t.id = :id")})
public class TitularesBonTasa implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;

    public TitularesBonTasa() {
    }

    public TitularesBonTasa(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
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
        if (!(object instanceof TitularesBonTasa)) {
            return false;
        }
        TitularesBonTasa other = (TitularesBonTasa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.TitularesBonTasa[ id=" + id + " ]";
    }
    
}
