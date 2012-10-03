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
import javax.persistence.Lob;
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
@Table(name = "JBPM_TASK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmTask.findAll", query = "SELECT j FROM JbpmTask j"),
    @NamedQuery(name = "JbpmTask.findById", query = "SELECT j FROM JbpmTask j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmTask.findByName", query = "SELECT j FROM JbpmTask j WHERE j.name = :name"),
    @NamedQuery(name = "JbpmTask.findByIsblocking", query = "SELECT j FROM JbpmTask j WHERE j.isblocking = :isblocking"),
    @NamedQuery(name = "JbpmTask.findByIssignalling", query = "SELECT j FROM JbpmTask j WHERE j.issignalling = :issignalling"),
    @NamedQuery(name = "JbpmTask.findByCondition", query = "SELECT j FROM JbpmTask j WHERE j.condition = :condition"),
    @NamedQuery(name = "JbpmTask.findByDuedate", query = "SELECT j FROM JbpmTask j WHERE j.duedate = :duedate"),
    @NamedQuery(name = "JbpmTask.findByPriority", query = "SELECT j FROM JbpmTask j WHERE j.priority = :priority"),
    @NamedQuery(name = "JbpmTask.findByActoridexpression", query = "SELECT j FROM JbpmTask j WHERE j.actoridexpression = :actoridexpression"),
    @NamedQuery(name = "JbpmTask.findByPooledactorsexpression", query = "SELECT j FROM JbpmTask j WHERE j.pooledactorsexpression = :pooledactorsexpression")})
public class JbpmTask implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Column(name = "NAME_")
    private String name;
    @Lob
    @Column(name = "DESCRIPTION_")
    private String description;
    @Column(name = "ISBLOCKING_")
    private Short isblocking;
    @Column(name = "ISSIGNALLING_")
    private Short issignalling;
    @Column(name = "CONDITION_")
    private String condition;
    @Column(name = "DUEDATE_")
    private String duedate;
    @Column(name = "PRIORITY_")
    private Integer priority;
    @Column(name = "ACTORIDEXPRESSION_")
    private String actoridexpression;
    @Column(name = "POOLEDACTORSEXPRESSION_")
    private String pooledactorsexpression;
    @JoinColumn(name = "TASKCONTROLLER_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmTaskcontroller taskcontroller;
    @JoinColumn(name = "SWIMLANE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmSwimlane swimlane;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;
    @JoinColumn(name = "STARTSTATE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode startstate;
    @JoinColumn(name = "TASKNODE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode tasknode;
    @JoinColumn(name = "TASKMGMTDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmModuledefinition taskmgmtdefinition;
    @JoinColumn(name = "ASSIGNMENTDELEGATION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmDelegation assignmentdelegation;
    @OneToMany(mappedBy = "task")
    private Collection<JbpmEvent> jbpmEventCollection;
    @OneToMany(mappedBy = "starttask")
    private Collection<JbpmModuledefinition> jbpmModuledefinitionCollection;

    public JbpmTask() {
    }

    public JbpmTask(BigDecimal id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getIsblocking() {
        return isblocking;
    }

    public void setIsblocking(Short isblocking) {
        this.isblocking = isblocking;
    }

    public Short getIssignalling() {
        return issignalling;
    }

    public void setIssignalling(Short issignalling) {
        this.issignalling = issignalling;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public JbpmTaskcontroller getTaskcontroller() {
        return taskcontroller;
    }

    public void setTaskcontroller(JbpmTaskcontroller taskcontroller) {
        this.taskcontroller = taskcontroller;
    }

    public JbpmSwimlane getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(JbpmSwimlane swimlane) {
        this.swimlane = swimlane;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
    }

    public JbpmNode getStartstate() {
        return startstate;
    }

    public void setStartstate(JbpmNode startstate) {
        this.startstate = startstate;
    }

    public JbpmNode getTasknode() {
        return tasknode;
    }

    public void setTasknode(JbpmNode tasknode) {
        this.tasknode = tasknode;
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

    @XmlTransient
    public Collection<JbpmEvent> getJbpmEventCollection() {
        return jbpmEventCollection;
    }

    public void setJbpmEventCollection(Collection<JbpmEvent> jbpmEventCollection) {
        this.jbpmEventCollection = jbpmEventCollection;
    }

    @XmlTransient
    public Collection<JbpmModuledefinition> getJbpmModuledefinitionCollection() {
        return jbpmModuledefinitionCollection;
    }

    public void setJbpmModuledefinitionCollection(Collection<JbpmModuledefinition> jbpmModuledefinitionCollection) {
        this.jbpmModuledefinitionCollection = jbpmModuledefinitionCollection;
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
        if (!(object instanceof JbpmTask)) {
            return false;
        }
        JbpmTask other = (JbpmTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmTask[ id=" + id + " ]";
    }
    
}
