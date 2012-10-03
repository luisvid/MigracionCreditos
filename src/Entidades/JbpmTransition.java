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
@Table(name = "JBPM_TRANSITION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmTransition.findAll", query = "SELECT j FROM JbpmTransition j"),
    @NamedQuery(name = "JbpmTransition.findById", query = "SELECT j FROM JbpmTransition j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmTransition.findByName", query = "SELECT j FROM JbpmTransition j WHERE j.name = :name"),
    @NamedQuery(name = "JbpmTransition.findByCondition", query = "SELECT j FROM JbpmTransition j WHERE j.condition = :condition"),
    @NamedQuery(name = "JbpmTransition.findByFromindex", query = "SELECT j FROM JbpmTransition j WHERE j.fromindex = :fromindex")})
public class JbpmTransition implements Serializable {
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
    @Column(name = "CONDITION_")
    private String condition;
    @Column(name = "FROMINDEX_")
    private Integer fromindex;
    @OneToMany(mappedBy = "transition")
    private Collection<JbpmEvent> jbpmEventCollection;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;
    @JoinColumn(name = "TO_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode to;
    @JoinColumn(name = "FROM_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmNode from1;

    public JbpmTransition() {
    }

    public JbpmTransition(BigDecimal id) {
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Integer getFromindex() {
        return fromindex;
    }

    public void setFromindex(Integer fromindex) {
        this.fromindex = fromindex;
    }

    @XmlTransient
    public Collection<JbpmEvent> getJbpmEventCollection() {
        return jbpmEventCollection;
    }

    public void setJbpmEventCollection(Collection<JbpmEvent> jbpmEventCollection) {
        this.jbpmEventCollection = jbpmEventCollection;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
    }

    public JbpmNode getTo() {
        return to;
    }

    public void setTo(JbpmNode to) {
        this.to = to;
    }

    public JbpmNode getFrom1() {
        return from1;
    }

    public void setFrom1(JbpmNode from1) {
        this.from1 = from1;
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
        if (!(object instanceof JbpmTransition)) {
            return false;
        }
        JbpmTransition other = (JbpmTransition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmTransition[ id=" + id + " ]";
    }
    
}
