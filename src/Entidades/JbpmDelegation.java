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
@Table(name = "JBPM_DELEGATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmDelegation.findAll", query = "SELECT j FROM JbpmDelegation j"),
    @NamedQuery(name = "JbpmDelegation.findById", query = "SELECT j FROM JbpmDelegation j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmDelegation.findByConfigtype", query = "SELECT j FROM JbpmDelegation j WHERE j.configtype = :configtype")})
public class JbpmDelegation implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Lob
    @Column(name = "CLASSNAME_")
    private String classname;
    @Lob
    @Column(name = "CONFIGURATION_")
    private String configuration;
    @Column(name = "CONFIGTYPE_")
    private String configtype;
    @OneToMany(mappedBy = "taskcontrollerdelegation")
    private Collection<JbpmTaskcontroller> jbpmTaskcontrollerCollection;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;
    @OneToMany(mappedBy = "assignmentdelegation")
    private Collection<JbpmTask> jbpmTaskCollection;
    @OneToMany(mappedBy = "actiondelegation")
    private Collection<JbpmAction> jbpmActionCollection;
    @OneToMany(mappedBy = "assignmentdelegation")
    private Collection<JbpmSwimlane> jbpmSwimlaneCollection;
    @OneToMany(mappedBy = "decisiondelegation")
    private Collection<JbpmNode> jbpmNodeCollection;

    public JbpmDelegation() {
    }

    public JbpmDelegation(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getConfiguration() {
        return configuration;
    }

    public void setConfiguration(String configuration) {
        this.configuration = configuration;
    }

    public String getConfigtype() {
        return configtype;
    }

    public void setConfigtype(String configtype) {
        this.configtype = configtype;
    }

    @XmlTransient
    public Collection<JbpmTaskcontroller> getJbpmTaskcontrollerCollection() {
        return jbpmTaskcontrollerCollection;
    }

    public void setJbpmTaskcontrollerCollection(Collection<JbpmTaskcontroller> jbpmTaskcontrollerCollection) {
        this.jbpmTaskcontrollerCollection = jbpmTaskcontrollerCollection;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
    }

    @XmlTransient
    public Collection<JbpmTask> getJbpmTaskCollection() {
        return jbpmTaskCollection;
    }

    public void setJbpmTaskCollection(Collection<JbpmTask> jbpmTaskCollection) {
        this.jbpmTaskCollection = jbpmTaskCollection;
    }

    @XmlTransient
    public Collection<JbpmAction> getJbpmActionCollection() {
        return jbpmActionCollection;
    }

    public void setJbpmActionCollection(Collection<JbpmAction> jbpmActionCollection) {
        this.jbpmActionCollection = jbpmActionCollection;
    }

    @XmlTransient
    public Collection<JbpmSwimlane> getJbpmSwimlaneCollection() {
        return jbpmSwimlaneCollection;
    }

    public void setJbpmSwimlaneCollection(Collection<JbpmSwimlane> jbpmSwimlaneCollection) {
        this.jbpmSwimlaneCollection = jbpmSwimlaneCollection;
    }

    @XmlTransient
    public Collection<JbpmNode> getJbpmNodeCollection() {
        return jbpmNodeCollection;
    }

    public void setJbpmNodeCollection(Collection<JbpmNode> jbpmNodeCollection) {
        this.jbpmNodeCollection = jbpmNodeCollection;
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
        if (!(object instanceof JbpmDelegation)) {
            return false;
        }
        JbpmDelegation other = (JbpmDelegation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmDelegation[ id=" + id + " ]";
    }
    
}
