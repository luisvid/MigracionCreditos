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
@Table(name = "JBPM_SWIMLANE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmSwimlane.findAll", query = "SELECT j FROM JbpmSwimlane j"),
    @NamedQuery(name = "JbpmSwimlane.findById", query = "SELECT j FROM JbpmSwimlane j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmSwimlane.findByName", query = "SELECT j FROM JbpmSwimlane j WHERE j.name = :name"),
    @NamedQuery(name = "JbpmSwimlane.findByActoridexpression", query = "SELECT j FROM JbpmSwimlane j WHERE j.actoridexpression = :actoridexpression"),
    @NamedQuery(name = "JbpmSwimlane.findByPooledactorsexpression", query = "SELECT j FROM JbpmSwimlane j WHERE j.pooledactorsexpression = :pooledactorsexpression")})
public class JbpmSwimlane implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Column(name = "NAME_")
    private String name;
    @Column(name = "ACTORIDEXPRESSION_")
    private String actoridexpression;
    @Column(name = "POOLEDACTORSEXPRESSION_")
    private String pooledactorsexpression;
    @OneToMany(mappedBy = "swimlane")
    private Collection<JbpmTask> jbpmTaskCollection;
    @JoinColumn(name = "TASKMGMTDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmModuledefinition taskmgmtdefinition;
    @JoinColumn(name = "ASSIGNMENTDELEGATION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmDelegation assignmentdelegation;

    public JbpmSwimlane() {
    }

    public JbpmSwimlane(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActoridexpression() {
        return actoridexpression;
    }

    public void setActoridexpression(String actoridexpression) {
        this.actoridexpression = actoridexpression;
    }

    public String getPooledactorsexpression() {
        return pooledactorsexpression;
    }

    public void setPooledactorsexpression(String pooledactorsexpression) {
        this.pooledactorsexpression = pooledactorsexpression;
    }

    @XmlTransient
    public Collection<JbpmTask> getJbpmTaskCollection() {
        return jbpmTaskCollection;
    }

    public void setJbpmTaskCollection(Collection<JbpmTask> jbpmTaskCollection) {
        this.jbpmTaskCollection = jbpmTaskCollection;
    }

    public JbpmModuledefinition getTaskmgmtdefinition() {
        return taskmgmtdefinition;
    }

    public void setTaskmgmtdefinition(JbpmModuledefinition taskmgmtdefinition) {
        this.taskmgmtdefinition = taskmgmtdefinition;
    }

    public JbpmDelegation getAssignmentdelegation() {
        return assignmentdelegation;
    }

    public void setAssignmentdelegation(JbpmDelegation assignmentdelegation) {
        this.assignmentdelegation = assignmentdelegation;
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
        if (!(object instanceof JbpmSwimlane)) {
            return false;
        }
        JbpmSwimlane other = (JbpmSwimlane) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmSwimlane[ id=" + id + " ]";
    }
    
}
