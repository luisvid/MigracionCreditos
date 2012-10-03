/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
@Table(name = "JBPM_TASKCONTROLLER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmTaskcontroller.findAll", query = "SELECT j FROM JbpmTaskcontroller j"),
    @NamedQuery(name = "JbpmTaskcontroller.findById", query = "SELECT j FROM JbpmTaskcontroller j WHERE j.id = :id")})
public class JbpmTaskcontroller implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @JoinColumn(name = "TASKCONTROLLERDELEGATION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmDelegation taskcontrollerdelegation;
    @OneToMany(mappedBy = "taskcontroller")
    private Collection<JbpmTask> jbpmTaskCollection;

    public JbpmTaskcontroller() {
    }

    public JbpmTaskcontroller(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public JbpmDelegation getTaskcontrollerdelegation() {
        return taskcontrollerdelegation;
    }

    public void setTaskcontrollerdelegation(JbpmDelegation taskcontrollerdelegation) {
        this.taskcontrollerdelegation = taskcontrollerdelegation;
    }

    @XmlTransient
    public Collection<JbpmTask> getJbpmTaskCollection() {
        return jbpmTaskCollection;
    }

    public void setJbpmTaskCollection(Collection<JbpmTask> jbpmTaskCollection) {
        this.jbpmTaskCollection = jbpmTaskCollection;
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
        if (!(object instanceof JbpmTaskcontroller)) {
            return false;
        }
        JbpmTaskcontroller other = (JbpmTaskcontroller) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmTaskcontroller[ id=" + id + " ]";
    }
    
}
