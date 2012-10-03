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
@Table(name = "JBPM_EVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmEvent.findAll", query = "SELECT j FROM JbpmEvent j"),
    @NamedQuery(name = "JbpmEvent.findById", query = "SELECT j FROM JbpmEvent j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmEvent.findByEventtype", query = "SELECT j FROM JbpmEvent j WHERE j.eventtype = :eventtype"),
    @NamedQuery(name = "JbpmEvent.findByType", query = "SELECT j FROM JbpmEvent j WHERE j.type = :type"),
    @NamedQuery(name = "JbpmEvent.findByGraphelement", query = "SELECT j FROM JbpmEvent j WHERE j.graphelement = :graphelement")})
public class JbpmEvent implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Column(name = "EVENTTYPE_")
    private String eventtype;
    @Column(name = "TYPE_")
    private Character type;
    @Column(name = "GRAPHELEMENT_")
    private BigInteger graphelement;
    @OneToMany(mappedBy = "event")
    private Collection<JbpmAction> jbpmActionCollection;
    @JoinColumn(name = "TRANSITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmTransition transition;
    @JoinColumn(name = "TASK_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmTask task;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;
    @JoinColumn(name = "NODE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode node;

    public JbpmEvent() {
    }

    public JbpmEvent(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype;
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

    @XmlTransient
    public Collection<JbpmAction> getJbpmActionCollection() {
        return jbpmActionCollection;
    }

    public void setJbpmActionCollection(Collection<JbpmAction> jbpmActionCollection) {
        this.jbpmActionCollection = jbpmActionCollection;
    }

    public JbpmTransition getTransition() {
        return transition;
    }

    public void setTransition(JbpmTransition transition) {
        this.transition = transition;
    }

    public JbpmTask getTask() {
        return task;
    }

    public void setTask(JbpmTask task) {
        this.task = task;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
    }

    public JbpmNode getNode() {
        return node;
    }

    public void setNode(JbpmNode node) {
        this.node = node;
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
        if (!(object instanceof JbpmEvent)) {
            return false;
        }
        JbpmEvent other = (JbpmEvent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmEvent[ id=" + id + " ]";
    }
    
}
