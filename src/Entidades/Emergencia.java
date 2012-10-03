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
@Table(name = "Emergencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emergencia.findAll", query = "SELECT e FROM Emergencia e"),
    @NamedQuery(name = "Emergencia.findById", query = "SELECT e FROM Emergencia e WHERE e.id = :id"),
    @NamedQuery(name = "Emergencia.findByExpediente", query = "SELECT e FROM Emergencia e WHERE e.expediente = :expediente")})
public class Emergencia implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "expediente")
    private String expediente;
    @JoinColumn(name = "persona_IDPERSONA", referencedColumnName = "IDPERSONA")
    @ManyToOne
    private Persona personaIDPERSONA;
    @JoinColumn(name = "emergenciaPeriodo_id", referencedColumnName = "id")
    @ManyToOne
    private EmergenciaPeriodo emergenciaPeriodoid;

    public Emergencia() {
    }

    public Emergencia(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getExpediente() {
        return expediente;
    }

    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }

    public Persona getPersonaIDPERSONA() {
        return personaIDPERSONA;
    }

    public void setPersonaIDPERSONA(Persona personaIDPERSONA) {
        this.personaIDPERSONA = personaIDPERSONA;
    }

    public EmergenciaPeriodo getEmergenciaPeriodoid() {
        return emergenciaPeriodoid;
    }

    public void setEmergenciaPeriodoid(EmergenciaPeriodo emergenciaPeriodoid) {
        this.emergenciaPeriodoid = emergenciaPeriodoid;
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
        if (!(object instanceof Emergencia)) {
            return false;
        }
        Emergencia other = (Emergencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Emergencia[ id=" + id + " ]";
    }
    
}
