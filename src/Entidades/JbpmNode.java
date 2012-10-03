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
@Table(name = "JBPM_NODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmNode.findAll", query = "SELECT j FROM JbpmNode j"),
    @NamedQuery(name = "JbpmNode.findById", query = "SELECT j FROM JbpmNode j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmNode.findByClass1", query = "SELECT j FROM JbpmNode j WHERE j.class1 = :class1"),
    @NamedQuery(name = "JbpmNode.findByName", query = "SELECT j FROM JbpmNode j WHERE j.name = :name"),
    @NamedQuery(name = "JbpmNode.findByIsasync", query = "SELECT j FROM JbpmNode j WHERE j.isasync = :isasync"),
    @NamedQuery(name = "JbpmNode.findByIsasyncexcl", query = "SELECT j FROM JbpmNode j WHERE j.isasyncexcl = :isasyncexcl"),
    @NamedQuery(name = "JbpmNode.findBySubprocname", query = "SELECT j FROM JbpmNode j WHERE j.subprocname = :subprocname"),
    @NamedQuery(name = "JbpmNode.findByDecisionexpression", query = "SELECT j FROM JbpmNode j WHERE j.decisionexpression = :decisionexpression"),
    @NamedQuery(name = "JbpmNode.findByParentlockmode", query = "SELECT j FROM JbpmNode j WHERE j.parentlockmode = :parentlockmode"),
    @NamedQuery(name = "JbpmNode.findBySignal", query = "SELECT j FROM JbpmNode j WHERE j.signal = :signal"),
    @NamedQuery(name = "JbpmNode.findByCreatetasks", query = "SELECT j FROM JbpmNode j WHERE j.createtasks = :createtasks"),
    @NamedQuery(name = "JbpmNode.findByEndtasks", query = "SELECT j FROM JbpmNode j WHERE j.endtasks = :endtasks"),
    @NamedQuery(name = "JbpmNode.findByNodecollectionindex", query = "SELECT j FROM JbpmNode j WHERE j.nodecollectionindex = :nodecollectionindex")})
public class JbpmNode implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "CLASS_")
    private char class1;
    @Column(name = "NAME_")
    private String name;
    @Lob
    @Column(name = "DESCRIPTION_")
    private String description;
    @Column(name = "ISASYNC_")
    private Short isasync;
    @Column(name = "ISASYNCEXCL_")
    private Short isasyncexcl;
    @Column(name = "SUBPROCNAME_")
    private String subprocname;
    @Column(name = "DECISIONEXPRESSION_")
    private String decisionexpression;
    @Column(name = "PARENTLOCKMODE_")
    private String parentlockmode;
    @Column(name = "SIGNAL_")
    private Integer signal;
    @Column(name = "CREATETASKS_")
    private Short createtasks;
    @Column(name = "ENDTASKS_")
    private Short endtasks;
    @Column(name = "NODECOLLECTIONINDEX_")
    private Integer nodecollectionindex;
    @OneToMany(mappedBy = "startstate")
    private Collection<JbpmTask> jbpmTaskCollection;
    @OneToMany(mappedBy = "tasknode")
    private Collection<JbpmTask> jbpmTaskCollection1;
    @OneToMany(mappedBy = "node")
    private Collection<JbpmEvent> jbpmEventCollection;
    @OneToMany(mappedBy = "to")
    private Collection<JbpmTransition> jbpmTransitionCollection;
    @OneToMany(mappedBy = "from1")
    private Collection<JbpmTransition> jbpmTransitionCollection1;
    @OneToMany(mappedBy = "node")
    private Collection<JbpmToken> jbpmTokenCollection;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;
    @JoinColumn(name = "SUBPROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition subprocessdefinition;
    @OneToMany(mappedBy = "superstate")
    private Collection<JbpmNode> jbpmNodeCollection;
    @JoinColumn(name = "SUPERSTATE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode superstate;
    @JoinColumn(name = "DECISIONDELEGATION", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmDelegation decisiondelegation;
    @JoinColumn(name = "SCRIPT_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmAction script;
    @JoinColumn(name = "ACTION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmAction action;
    @OneToMany(mappedBy = "startstate")
    private Collection<JbpmProcessdefinition> jbpmProcessdefinitionCollection;

    public JbpmNode() {
    }

    public JbpmNode(BigDecimal id) {
        this.id = id;
    }

    public JbpmNode(BigDecimal id, char class1) {
        this.id = id;
        this.class1 = class1;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public char getClass1() {
        return class1;
    }

    public void setClass1(char class1) {
        this.class1 = class1;
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

    public Short getIsasync() {
        return isasync;
    }

    public void setIsasync(Short isasync) {
        this.isasync = isasync;
    }

    public Short getIsasyncexcl() {
        return isasyncexcl;
    }

    public void setIsasyncexcl(Short isasyncexcl) {
        this.isasyncexcl = isasyncexcl;
    }

    public String getSubprocname() {
        return subprocname;
    }

    public void setSubprocname(String subprocname) {
        this.subprocname = subprocname;
    }

    public String getDecisionexpression() {
        return decisionexpression;
    }

    public void setDecisionexpression(String decisionexpression) {
        this.decisionexpression = decisionexpression;
    }

    public String getParentlockmode() {
        return parentlockmode;
    }

    public void setParentlockmode(String parentlockmode) {
        this.parentlockmode = parentlockmode;
    }

    public Integer getSignal() {
        return signal;
    }

    public void setSignal(Integer signal) {
        this.signal = signal;
    }

    public Short getCreatetasks() {
        return createtasks;
    }

    public void setCreatetasks(Short createtasks) {
        this.createtasks = createtasks;
    }

    public Short getEndtasks() {
        return endtasks;
    }

    public void setEndtasks(Short endtasks) {
        this.endtasks = endtasks;
    }

    public Integer getNodecollectionindex() {
        return nodecollectionindex;
    }

    public void setNodecollectionindex(Integer nodecollectionindex) {
        this.nodecollectionindex = nodecollectionindex;
    }

    @XmlTransient
    public Collection<JbpmTask> getJbpmTaskCollection() {
        return jbpmTaskCollection;
    }

    public void setJbpmTaskCollection(Collection<JbpmTask> jbpmTaskCollection) {
        this.jbpmTaskCollection = jbpmTaskCollection;
    }

    @XmlTransient
    public Collection<JbpmTask> getJbpmTaskCollection1() {
        return jbpmTaskCollection1;
    }

    public void setJbpmTaskCollection1(Collection<JbpmTask> jbpmTaskCollection1) {
        this.jbpmTaskCollection1 = jbpmTaskCollection1;
    }

    @XmlTransient
    public Collection<JbpmEvent> getJbpmEventCollection() {
        return jbpmEventCollection;
    }

    public void setJbpmEventCollection(Collection<JbpmEvent> jbpmEventCollection) {
        this.jbpmEventCollection = jbpmEventCollection;
    }

    @XmlTransient
    public Collection<JbpmTransition> getJbpmTransitionCollection() {
        return jbpmTransitionCollection;
    }

    public void setJbpmTransitionCollection(Collection<JbpmTransition> jbpmTransitionCollection) {
        this.jbpmTransitionCollection = jbpmTransitionCollection;
    }

    @XmlTransient
    public Collection<JbpmTransition> getJbpmTransitionCollection1() {
        return jbpmTransitionCollection1;
    }

    public void setJbpmTransitionCollection1(Collection<JbpmTransition> jbpmTransitionCollection1) {
        this.jbpmTransitionCollection1 = jbpmTransitionCollection1;
    }

    @XmlTransient
    public Collection<JbpmToken> getJbpmTokenCollection() {
        return jbpmTokenCollection;
    }

    public void setJbpmTokenCollection(Collection<JbpmToken> jbpmTokenCollection) {
        this.jbpmTokenCollection = jbpmTokenCollection;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
    }

    public JbpmProcessdefinition getSubprocessdefinition() {
        return subprocessdefinition;
    }

    public void setSubprocessdefinition(JbpmProcessdefinition subprocessdefinition) {
        this.subprocessdefinition = subprocessdefinition;
    }

    @XmlTransient
    public Collection<JbpmNode> getJbpmNodeCollection() {
        return jbpmNodeCollection;
    }

    public void setJbpmNodeCollection(Collection<JbpmNode> jbpmNodeCollection) {
        this.jbpmNodeCollection = jbpmNodeCollection;
    }

    public JbpmNode getSuperstate() {
        return superstate;
    }

    public void setSuperstate(JbpmNode superstate) {
        this.superstate = superstate;
    }

    public JbpmDelegation getDecisiondelegation() {
        return decisiondelegation;
    }

    public void setDecisiondelegation(JbpmDelegation decisiondelegation) {
        this.decisiondelegation = decisiondelegation;
    }

    public JbpmAction getScript() {
        return script;
    }

    public void setScript(JbpmAction script) {
        this.script = script;
    }

    public JbpmAction getAction() {
        return action;
    }

    public void setAction(JbpmAction action) {
        this.action = action;
    }

    @XmlTransient
    public Collection<JbpmProcessdefinition> getJbpmProcessdefinitionCollection() {
        return jbpmProcessdefinitionCollection;
    }

    public void setJbpmProcessdefinitionCollection(Collection<JbpmProcessdefinition> jbpmProcessdefinitionCollection) {
        this.jbpmProcessdefinitionCollection = jbpmProcessdefinitionCollection;
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
        if (!(object instanceof JbpmNode)) {
            return false;
        }
        JbpmNode other = (JbpmNode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmNode[ id=" + id + " ]";
    }
    
}
