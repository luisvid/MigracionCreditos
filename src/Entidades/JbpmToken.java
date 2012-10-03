/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "JBPM_TOKEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmToken.findAll", query = "SELECT j FROM JbpmToken j"),
    @NamedQuery(name = "JbpmToken.findById", query = "SELECT j FROM JbpmToken j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmToken.findByVersion", query = "SELECT j FROM JbpmToken j WHERE j.version = :version"),
    @NamedQuery(name = "JbpmToken.findByName", query = "SELECT j FROM JbpmToken j WHERE j.name = :name"),
    @NamedQuery(name = "JbpmToken.findByStart", query = "SELECT j FROM JbpmToken j WHERE j.start = :start"),
    @NamedQuery(name = "JbpmToken.findByEnd", query = "SELECT j FROM JbpmToken j WHERE j.end = :end"),
    @NamedQuery(name = "JbpmToken.findByNodeenter", query = "SELECT j FROM JbpmToken j WHERE j.nodeenter = :nodeenter"),
    @NamedQuery(name = "JbpmToken.findByNextlogindex", query = "SELECT j FROM JbpmToken j WHERE j.nextlogindex = :nextlogindex"),
    @NamedQuery(name = "JbpmToken.findByIsabletoreactivateparent", query = "SELECT j FROM JbpmToken j WHERE j.isabletoreactivateparent = :isabletoreactivateparent"),
    @NamedQuery(name = "JbpmToken.findByIsterminationimplicit", query = "SELECT j FROM JbpmToken j WHERE j.isterminationimplicit = :isterminationimplicit"),
    @NamedQuery(name = "JbpmToken.findByIssuspended", query = "SELECT j FROM JbpmToken j WHERE j.issuspended = :issuspended"),
    @NamedQuery(name = "JbpmToken.findByLock", query = "SELECT j FROM JbpmToken j WHERE j.lock = :lock")})
public class JbpmToken implements Serializable {
    @Column(name =     "START_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Column(name =     "END_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    @Column(name =     "NODEENTER_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nodeenter;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "VERSION_")
    private int version;
    @Column(name = "NAME_")
    private String name;
    @Column(name = "NEXTLOGINDEX_")
    private Integer nextlogindex;
    @Column(name = "ISABLETOREACTIVATEPARENT_")
    private Short isabletoreactivateparent;
    @Column(name = "ISTERMINATIONIMPLICIT_")
    private Short isterminationimplicit;
    @Column(name = "ISSUSPENDED_")
    private Short issuspended;
    @Column(name = "LOCK_")
    private String lock;
    @OneToMany(mappedBy = "superprocesstoken")
    private Collection<JbpmProcessinstance> jbpmProcessinstanceCollection;
    @OneToMany(mappedBy = "roottoken")
    private Collection<JbpmProcessinstance> jbpmProcessinstanceCollection1;
    @OneToMany(mappedBy = "parent")
    private Collection<JbpmToken> jbpmTokenCollection;
    @JoinColumn(name = "PARENT_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmToken parent;
    @JoinColumn(name = "SUBPROCESSINSTANCE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessinstance subprocessinstance;
    @JoinColumn(name = "PROCESSINSTANCE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessinstance processinstance;
    @JoinColumn(name = "NODE_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode node;

    public JbpmToken() {
    }

    public JbpmToken(BigDecimal id) {
        this.id = id;
    }

    public JbpmToken(BigDecimal id, int version) {
        this.id = id;
        this.version = version;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getNodeenter() {
        return nodeenter;
    }

    public void setNodeenter(Date nodeenter) {
        this.nodeenter = nodeenter;
    }

    public Integer getNextlogindex() {
        return nextlogindex;
    }

    public void setNextlogindex(Integer nextlogindex) {
        this.nextlogindex = nextlogindex;
    }

    public Short getIsabletoreactivateparent() {
        return isabletoreactivateparent;
    }

    public void setIsabletoreactivateparent(Short isabletoreactivateparent) {
        this.isabletoreactivateparent = isabletoreactivateparent;
    }

    public Short getIsterminationimplicit() {
        return isterminationimplicit;
    }

    public void setIsterminationimplicit(Short isterminationimplicit) {
        this.isterminationimplicit = isterminationimplicit;
    }

    public Short getIssuspended() {
        return issuspended;
    }

    public void setIssuspended(Short issuspended) {
        this.issuspended = issuspended;
    }

    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }

    @XmlTransient
    public Collection<JbpmProcessinstance> getJbpmProcessinstanceCollection() {
        return jbpmProcessinstanceCollection;
    }

    public void setJbpmProcessinstanceCollection(Collection<JbpmProcessinstance> jbpmProcessinstanceCollection) {
        this.jbpmProcessinstanceCollection = jbpmProcessinstanceCollection;
    }

    @XmlTransient
    public Collection<JbpmProcessinstance> getJbpmProcessinstanceCollection1() {
        return jbpmProcessinstanceCollection1;
    }

    public void setJbpmProcessinstanceCollection1(Collection<JbpmProcessinstance> jbpmProcessinstanceCollection1) {
        this.jbpmProcessinstanceCollection1 = jbpmProcessinstanceCollection1;
    }

    @XmlTransient
    public Collection<JbpmToken> getJbpmTokenCollection() {
        return jbpmTokenCollection;
    }

    public void setJbpmTokenCollection(Collection<JbpmToken> jbpmTokenCollection) {
        this.jbpmTokenCollection = jbpmTokenCollection;
    }

    public JbpmToken getParent() {
        return parent;
    }

    public void setParent(JbpmToken parent) {
        this.parent = parent;
    }

    public JbpmProcessinstance getSubprocessinstance() {
        return subprocessinstance;
    }

    public void setSubprocessinstance(JbpmProcessinstance subprocessinstance) {
        this.subprocessinstance = subprocessinstance;
    }

    public JbpmProcessinstance getProcessinstance() {
        return processinstance;
    }

    public void setProcessinstance(JbpmProcessinstance processinstance) {
        this.processinstance = processinstance;
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
        if (!(object instanceof JbpmToken)) {
            return false;
        }
        JbpmToken other = (JbpmToken) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmToken[ id=" + id + " ]";
    }

/*    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getNodeenter() {
        return nodeenter;
    }

    public void setNodeenter(Date nodeenter) {
        this.nodeenter = nodeenter;
    }*/

/*    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getNodeenter() {
        return nodeenter;
    }

    public void setNodeenter(Date nodeenter) {
        this.nodeenter = nodeenter;
    }*/


    
   
  
  
}
