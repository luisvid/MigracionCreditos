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
@Table(name = "ObjetoiEmergencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetoiEmergencia.findAll", query = "SELECT o FROM ObjetoiEmergencia o"),
    @NamedQuery(name = "ObjetoiEmergencia.findById", query = "SELECT o FROM ObjetoiEmergencia o WHERE o.id = :id"),
    @NamedQuery(name = "ObjetoiEmergencia.findByAdhiereLey", query = "SELECT o FROM ObjetoiEmergencia o WHERE o.adhiereLey = :adhiereLey"),
    @NamedQuery(name = "ObjetoiEmergencia.findByCodigo", query = "SELECT o FROM ObjetoiEmergencia o WHERE o.codigo = :codigo")})
public class ObjetoiEmergencia implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "adhiereLey")
    private short adhiereLey;
    @Column(name = "codigo")
    private String codigo;
    @JoinColumn(name = "credito_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi creditoId;

    public ObjetoiEmergencia() {
    }

    public ObjetoiEmergencia(BigDecimal id) {
        this.id = id;
    }

    public ObjetoiEmergencia(BigDecimal id, short adhiereLey) {
        this.id = id;
        this.adhiereLey = adhiereLey;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public short getAdhiereLey() {
        return adhiereLey;
    }

    public void setAdhiereLey(short adhiereLey) {
        this.adhiereLey = adhiereLey;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Objetoi getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Objetoi creditoId) {
        this.creditoId = creditoId;
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
        if (!(object instanceof ObjetoiEmergencia)) {
            return false;
        }
        ObjetoiEmergencia other = (ObjetoiEmergencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ObjetoiEmergencia[ id=" + id + " ]";
    }
    
}
