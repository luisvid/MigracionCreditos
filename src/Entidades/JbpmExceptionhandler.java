/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "JBPM_EXCEPTIONHANDLER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmExceptionhandler.findAll", query = "SELECT j FROM JbpmExceptionhandler j"),
    @NamedQuery(name = "JbpmExceptionhandler.findById", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmExceptionhandler.findByType", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.type = :type"),
    @NamedQuery(name = "JbpmExceptionhandler.findByGraphelement", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.graphelement = :graphelement"),
    @NamedQuery(name = "JbpmExceptionhandler.findByProcessdefinition", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.processdefinition = :processdefinition"),
    @NamedQuery(name = "JbpmExceptionhandler.findByGraphelementindex", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.graphelementindex = :graphelementindex"),
    @NamedQuery(name = "JbpmExceptionhandler.findByNode", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.node = :node"),
    @NamedQuery(name = "JbpmExceptionhandler.findByTransition", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.transition = :transition"),
    @NamedQuery(name = "JbpmExceptionhandler.findByTask", query = "SELECT j FROM JbpmExceptionhandler j WHERE j.task = :task")})
public class JbpmExceptionhandler implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Lob
    @Column(name = "EXCEPTIONCLASSNAME_")
    private String exceptionclassname;
    @Column(name = "TYPE_")
    private Character type;
    @Column(name = "GRAPHELEMENT_")
    private BigInteger graphelement;
    @Column(name = "PROCESSDEFINITION_")
    private BigInteger processdefinition;
    @Column(name = "GRAPHELEMENTINDEX_")
    private Integer graphelementindex;
    @Column(name = "NODE_")
    private BigInteger node;
    @Column(name = "TRANSITION_")
    private BigInteger transition;
    @Column(name = "TASK_")
    private BigInteger task;
    @OneToMany(mappedBy = "exceptionhandler")
    private Collection<JbpmAction> jbpmActionCollection;

    public JbpmExceptionhandler() {
    }

    public JbpmExceptionhandler(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getExceptionclassname() {
        return exceptionclassname;
    }

    public void setExceptionclassname(String exceptionclassname) {
        this.exceptionclassname = exceptionclassname;
    }

    public Character getType() {
        return type;
    }

    public void setType(Character type) {
        this.type = type;
    }

    public BigInteger getGraphelement() {
        return graphelement;
    }

    public void setGraphelement(BigInteger graphelement) {
        this.graphelement = graphelement;
    }

    public BigInteger getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(BigInteger processdefinition) {
        this.processdefinition = processdefinition;
    }

    public Integer getGraphelementindex() {
        return graphelementindex;
    }

    public void setGraphelementindex(Integer graphelementindex) {
        this.graphelementindex = graphelementindex;
    }

    public BigInteger getNode() {
        return node;
    }

    public void setNode(BigInteger node) {
        this.node = node;
    }

    public BigInteger getTransition() {
        return transition;
    }

    public void setTransition(BigInteger transition) {
        this.transition = transition;
    }

    public BigInteger getTask() {
        return task;
    }

    public void setTask(BigInteger task) {
        this.task = task;
    }

    @XmlTransient
    public Collection<JbpmAction> getJbpmActionCollection() {
        return jbpmActionCollection;
    }

    public void setJbpmActionCollection(Collection<JbpmAction> jbpmActionCollection) {
        this.jbpmActionCollection = jbpmActionCollection;
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
        if (!(object instanceof JbpmExceptionhandler)) {
            return false;
        }
        JbpmExceptionhandler other = (JbpmExceptionhandler) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmExceptionhandler[ id=" + id + " ]";
    }
    
}
