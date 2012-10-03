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
@Table(name = "JBPM_MODULEDEFINITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmModuledefinition.findAll", query = "SELECT j FROM JbpmModuledefinition j"),
    @NamedQuery(name = "JbpmModuledefinition.findById", query = "SELECT j FROM JbpmModuledefinition j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmModuledefinition.findByClass1", query = "SELECT j FROM JbpmModuledefinition j WHERE j.class1 = :class1"),
    @NamedQuery(name = "JbpmModuledefinition.findByName", query = "SELECT j FROM JbpmModuledefinition j WHERE j.name = :name")})
public class JbpmModuledefinition implements Serializable {
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
    @OneToMany(mappedBy = "taskmgmtdefinition")
    private Collection<JbpmTask> jbpmTaskCollection;
    @OneToMany(mappedBy = "taskmgmtdefinition")
    private Collection<JbpmSwimlane> jbpmSwimlaneCollection;
    @JoinColumn(name = "STARTTASK_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmTask starttask;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;

    public JbpmModuledefinition() {
    }

    public JbpmModuledefinition(BigDecimal id) {
        this.id = id;
    }

    public JbpmModuledefinition(BigDecimal id, char class1) {
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

    @XmlTransient
    public Collection<JbpmTask> getJbpmTaskCollection() {
        return jbpmTaskCollection;
    }

    public void setJbpmTaskCollection(Collection<JbpmTask> jbpmTaskCollection) {
        this.jbpmTaskCollection = jbpmTaskCollection;
    }

    @XmlTransient
    public Collection<JbpmSwimlane> getJbpmSwimlaneCollection() {
        return jbpmSwimlaneCollection;
    }

    public void setJbpmSwimlaneCollection(Collection<JbpmSwimlane> jbpmSwimlaneCollection) {
        this.jbpmSwimlaneCollection = jbpmSwimlaneCollection;
    }

    public JbpmTask getStarttask() {
        return starttask;
    }

    public void setStarttask(JbpmTask starttask) {
        this.starttask = starttask;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
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
        if (!(object instanceof JbpmModuledefinition)) {
            return false;
        }
        JbpmModuledefinition other = (JbpmModuledefinition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmModuledefinition[ id=" + id + " ]";
    }
    
}
