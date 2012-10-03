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
@Table(name = "JBPM_PROCESSDEFINITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmProcessdefinition.findAll", query = "SELECT j FROM JbpmProcessdefinition j"),
    @NamedQuery(name = "JbpmProcessdefinition.findById", query = "SELECT j FROM JbpmProcessdefinition j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmProcessdefinition.findByClass1", query = "SELECT j FROM JbpmProcessdefinition j WHERE j.class1 = :class1"),
    @NamedQuery(name = "JbpmProcessdefinition.findByName", query = "SELECT j FROM JbpmProcessdefinition j WHERE j.name = :name"),
    @NamedQuery(name = "JbpmProcessdefinition.findByVersion", query = "SELECT j FROM JbpmProcessdefinition j WHERE j.version = :version"),
    @NamedQuery(name = "JbpmProcessdefinition.findByIsterminationimplicit", query = "SELECT j FROM JbpmProcessdefinition j WHERE j.isterminationimplicit = :isterminationimplicit")})
public class JbpmProcessdefinition implements Serializable {
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
    @Column(name = "VERSION_")
    private Integer version;
    @Column(name = "ISTERMINATIONIMPLICIT_")
    private Short isterminationimplicit;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmDelegation> jbpmDelegationCollection;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmTask> jbpmTaskCollection;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmAction> jbpmActionCollection;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmEvent> jbpmEventCollection;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmProcessinstance> jbpmProcessinstanceCollection;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmTransition> jbpmTransitionCollection;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmModuledefinition> jbpmModuledefinitionCollection;
    @OneToMany(mappedBy = "processdefinition")
    private Collection<JbpmNode> jbpmNodeCollection;
    @OneToMany(mappedBy = "subprocessdefinition")
    private Collection<JbpmNode> jbpmNodeCollection1;
    @JoinColumn(name = "STARTSTATE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode startstate;

    public JbpmProcessdefinition() {
    }

    public JbpmProcessdefinition(BigDecimal id) {
        this.id = id;
    }

    public JbpmProcessdefinition(BigDecimal id, char class1) {
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Short getIsterminationimplicit() {
        return isterminationimplicit;
    }

    public void setIsterminationimplicit(Short isterminationimplicit) {
        this.isterminationimplicit = isterminationimplicit;
    }

    @XmlTransient
    public Collection<JbpmDelegation> getJbpmDelegationCollection() {
        return jbpmDelegationCollection;
    }

    public void setJbpmDelegationCollection(Collection<JbpmDelegation> jbpmDelegationCollection) {
        this.jbpmDelegationCollection = jbpmDelegationCollection;
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
    public Collection<JbpmEvent> getJbpmEventCollection() {
        return jbpmEventCollection;
    }

    public void setJbpmEventCollection(Collection<JbpmEvent> jbpmEventCollection) {
        this.jbpmEventCollection = jbpmEventCollection;
    }

    @XmlTransient
    public Collection<JbpmProcessinstance> getJbpmProcessinstanceCollection() {
        return jbpmProcessinstanceCollection;
    }

    public void setJbpmProcessinstanceCollection(Collection<JbpmProcessinstance> jbpmProcessinstanceCollection) {
        this.jbpmProcessinstanceCollection = jbpmProcessinstanceCollection;
    }

    @XmlTransient
    public Collection<JbpmTransition> getJbpmTransitionCollection() {
        return jbpmTransitionCollection;
    }

    public void setJbpmTransitionCollection(Collection<JbpmTransition> jbpmTransitionCollection) {
        this.jbpmTransitionCollection = jbpmTransitionCollection;
    }

    @XmlTransient
    public Collection<JbpmModuledefinition> getJbpmModuledefinitionCollection() {
        return jbpmModuledefinitionCollection;
    }

    public void setJbpmModuledefinitionCollection(Collection<JbpmModuledefinition> jbpmModuledefinitionCollection) {
        this.jbpmModuledefinitionCollection = jbpmModuledefinitionCollection;
    }

    @XmlTransient
    public Collection<JbpmNode> getJbpmNodeCollection() {
        return jbpmNodeCollection;
    }

    public void setJbpmNodeCollection(Collection<JbpmNode> jbpmNodeCollection) {
        this.jbpmNodeCollection = jbpmNodeCollection;
    }

    @XmlTransient
    public Collection<JbpmNode> getJbpmNodeCollection1() {
        return jbpmNodeCollection1;
    }

    public void setJbpmNodeCollection1(Collection<JbpmNode> jbpmNodeCollection1) {
        this.jbpmNodeCollection1 = jbpmNodeCollection1;
    }

    public JbpmNode getStartstate() {
        return startstate;
    }

    public void setStartstate(JbpmNode startstate) {
        this.startstate = startstate;
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
        if (!(object instanceof JbpmProcessdefinition)) {
            return false;
        }
        JbpmProcessdefinition other = (JbpmProcessdefinition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmProcessdefinition[ id=" + id + " ]";
    }
    
}
