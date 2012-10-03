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
@Table(name = "JBPM_PROCESSINSTANCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmProcessinstance.findAll", query = "SELECT j FROM JbpmProcessinstance j"),
    @NamedQuery(name = "JbpmProcessinstance.findById", query = "SELECT j FROM JbpmProcessinstance j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmProcessinstance.findByVersion", query = "SELECT j FROM JbpmProcessinstance j WHERE j.version = :version"),
    @NamedQuery(name = "JbpmProcessinstance.findByKey", query = "SELECT j FROM JbpmProcessinstance j WHERE j.key = :key"),
    @NamedQuery(name = "JbpmProcessinstance.findByStart", query = "SELECT j FROM JbpmProcessinstance j WHERE j.start = :start"),
    @NamedQuery(name = "JbpmProcessinstance.findByEnd", query = "SELECT j FROM JbpmProcessinstance j WHERE j.end = :end"),
    @NamedQuery(name = "JbpmProcessinstance.findByIssuspended", query = "SELECT j FROM JbpmProcessinstance j WHERE j.issuspended = :issuspended")})
public class JbpmProcessinstance implements Serializable {
    @Column(name =     "START_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start;
    @Column(name =     "END_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end;
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "VERSION_")
    private int version;
    @Column(name = "KEY_")
    private String key;
    @Column(name = "ISSUSPENDED_")
    private Short issuspended;
    @OneToMany(mappedBy = "procesoAprobacionID")
    private Collection<Objetoi> objetoiCollection;
    @JoinColumn(name = "SUPERPROCESSTOKEN_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmToken superprocesstoken;
    @JoinColumn(name = "ROOTTOKEN_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmToken roottoken;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;
    @OneToMany(mappedBy = "subprocessinstance")
    private Collection<JbpmToken> jbpmTokenCollection;
    @OneToMany(mappedBy = "processinstance")
    private Collection<JbpmToken> jbpmTokenCollection1;

    public JbpmProcessinstance() {
    }

    public JbpmProcessinstance(BigDecimal id) {
        this.id = id;
    }

    public JbpmProcessinstance(BigDecimal id, int version) {
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Short getIssuspended() {
        return issuspended;
    }

    public void setIssuspended(Short issuspended) {
        this.issuspended = issuspended;
    }

    @XmlTransient
    public Collection<Objetoi> getObjetoiCollection() {
        return objetoiCollection;
    }

    public void setObjetoiCollection(Collection<Objetoi> objetoiCollection) {
        this.objetoiCollection = objetoiCollection;
    }

    public JbpmToken getSuperprocesstoken() {
        return superprocesstoken;
    }

    public void setSuperprocesstoken(JbpmToken superprocesstoken) {
        this.superprocesstoken = superprocesstoken;
    }

    public JbpmToken getRoottoken() {
        return roottoken;
    }

    public void setRoottoken(JbpmToken roottoken) {
        this.roottoken = roottoken;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
    }

    @XmlTransient
    public Collection<JbpmToken> getJbpmTokenCollection() {
        return jbpmTokenCollection;
    }

    public void setJbpmTokenCollection(Collection<JbpmToken> jbpmTokenCollection) {
        this.jbpmTokenCollection = jbpmTokenCollection;
    }

    @XmlTransient
    public Collection<JbpmToken> getJbpmTokenCollection1() {
        return jbpmTokenCollection1;
    }

    public void setJbpmTokenCollection1(Collection<JbpmToken> jbpmTokenCollection1) {
        this.jbpmTokenCollection1 = jbpmTokenCollection1;
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
        if (!(object instanceof JbpmProcessinstance)) {
            return false;
        }
        JbpmProcessinstance other = (JbpmProcessinstance) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmProcessinstance[ id=" + id + " ]";
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
    }*/


}
