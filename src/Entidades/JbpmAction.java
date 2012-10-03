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
@Table(name = "JBPM_ACTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JbpmAction.findAll", query = "SELECT j FROM JbpmAction j"),
    @NamedQuery(name = "JbpmAction.findById", query = "SELECT j FROM JbpmAction j WHERE j.id = :id"),
    @NamedQuery(name = "JbpmAction.findByClass1", query = "SELECT j FROM JbpmAction j WHERE j.class1 = :class1"),
    @NamedQuery(name = "JbpmAction.findByName", query = "SELECT j FROM JbpmAction j WHERE j.name = :name"),
    @NamedQuery(name = "JbpmAction.findByIspropagationallowed", query = "SELECT j FROM JbpmAction j WHERE j.ispropagationallowed = :ispropagationallowed"),
    @NamedQuery(name = "JbpmAction.findByActionexpression", query = "SELECT j FROM JbpmAction j WHERE j.actionexpression = :actionexpression"),
    @NamedQuery(name = "JbpmAction.findByIsasync", query = "SELECT j FROM JbpmAction j WHERE j.isasync = :isasync"),
    @NamedQuery(name = "JbpmAction.findByTimername", query = "SELECT j FROM JbpmAction j WHERE j.timername = :timername"),
    @NamedQuery(name = "JbpmAction.findByDuedate", query = "SELECT j FROM JbpmAction j WHERE j.duedate = :duedate"),
    @NamedQuery(name = "JbpmAction.findByRepeat", query = "SELECT j FROM JbpmAction j WHERE j.repeat = :repeat"),
    @NamedQuery(name = "JbpmAction.findByTransitionname", query = "SELECT j FROM JbpmAction j WHERE j.transitionname = :transitionname"),
    @NamedQuery(name = "JbpmAction.findByEventindex", query = "SELECT j FROM JbpmAction j WHERE j.eventindex = :eventindex"),
    @NamedQuery(name = "JbpmAction.findByExceptionhandlerindex", query = "SELECT j FROM JbpmAction j WHERE j.exceptionhandlerindex = :exceptionhandlerindex")})
public class JbpmAction implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_")
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "class")
    private char class1;
    @Column(name = "NAME_")
    private String name;
    @Column(name = "ISPROPAGATIONALLOWED_")
    private Short ispropagationallowed;
    @Column(name = "ACTIONEXPRESSION_")
    private String actionexpression;
    @Column(name = "ISASYNC_")
    private Short isasync;
    @Lob
    @Column(name = "EXPRESSION_")
    private String expression;
    @Column(name = "TIMERNAME_")
    private String timername;
    @Column(name = "DUEDATE_")
    private String duedate;
    @Column(name = "REPEAT_")
    private String repeat;
    @Column(name = "TRANSITIONNAME_")
    private String transitionname;
    @Column(name = "EVENTINDEX_")
    private Integer eventindex;
    @Column(name = "EXCEPTIONHANDLERINDEX_")
    private Integer exceptionhandlerindex;
    @JoinColumn(name = "PROCESSDEFINITION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmProcessdefinition processdefinition;
    @JoinColumn(name = "EXCEPTIONHANDLER_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmExceptionhandler exceptionhandler;
    @JoinColumn(name = "EVENT_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmEvent event;
    @JoinColumn(name = "ACTIONDELEGATION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmDelegation actiondelegation;
    @OneToMany(mappedBy = "timeraction")
    private Collection<JbpmAction> jbpmActionCollection;
    @JoinColumn(name = "TIMERACTION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmAction timeraction;
    @OneToMany(mappedBy = "referencedaction")
    private Collection<JbpmAction> jbpmActionCollection1;
    @JoinColumn(name = "REFERENCEDACTION_", referencedColumnName = "ID_")
    @ManyToOne
    private JbpmAction referencedaction;
    @OneToMany(mappedBy = "script")
    private Collection<JbpmNode> jbpmNodeCollection;
    @OneToMany(mappedBy = "action")
    private Collection<JbpmNode> jbpmNodeCollection1;

    public JbpmAction() {
    }

    public JbpmAction(BigDecimal id) {
        this.id = id;
    }

    public JbpmAction(BigDecimal id, char class1) {
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

    public Short getIspropagationallowed() {
        return ispropagationallowed;
    }

    public void setIspropagationallowed(Short ispropagationallowed) {
        this.ispropagationallowed = ispropagationallowed;
    }

    public String getActionexpression() {
        return actionexpression;
    }

    public void setActionexpression(String actionexpression) {
        this.actionexpression = actionexpression;
    }

    public Short getIsasync() {
        return isasync;
    }

    public void setIsasync(Short isasync) {
        this.isasync = isasync;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTimername() {
        return timername;
    }

    public void setTimername(String timername) {
        this.timername = timername;
    }

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getTransitionname() {
        return transitionname;
    }

    public void setTransitionname(String transitionname) {
        this.transitionname = transitionname;
    }

    public Integer getEventindex() {
        return eventindex;
    }

    public void setEventindex(Integer eventindex) {
        this.eventindex = eventindex;
    }

    public Integer getExceptionhandlerindex() {
        return exceptionhandlerindex;
    }

    public void setExceptionhandlerindex(Integer exceptionhandlerindex) {
        this.exceptionhandlerindex = exceptionhandlerindex;
    }

    public JbpmProcessdefinition getProcessdefinition() {
        return processdefinition;
    }

    public void setProcessdefinition(JbpmProcessdefinition processdefinition) {
        this.processdefinition = processdefinition;
    }

    public JbpmExceptionhandler getExceptionhandler() {
        return exceptionhandler;
    }

    public void setExceptionhandler(JbpmExceptionhandler exceptionhandler) {
        this.exceptionhandler = exceptionhandler;
    }

    public JbpmEvent getEvent() {
        return event;
    }

    public void setEvent(JbpmEvent event) {
        this.event = event;
    }

    public JbpmDelegation getActiondelegation() {
        return actiondelegation;
    }

    public void setActiondelegation(JbpmDelegation actiondelegation) {
        this.actiondelegation = actiondelegation;
    }

    @XmlTransient
    public Collection<JbpmAction> getJbpmActionCollection() {
        return jbpmActionCollection;
    }

    public void setJbpmActionCollection(Collection<JbpmAction> jbpmActionCollection) {
        this.jbpmActionCollection = jbpmActionCollection;
    }

    public JbpmAction getTimeraction() {
        return timeraction;
    }

    public void setTimeraction(JbpmAction timeraction) {
        this.timeraction = timeraction;
    }

    @XmlTransient
    public Collection<JbpmAction> getJbpmActionCollection1() {
        return jbpmActionCollection1;
    }

    public void setJbpmActionCollection1(Collection<JbpmAction> jbpmActionCollection1) {
        this.jbpmActionCollection1 = jbpmActionCollection1;
    }

    public JbpmAction getReferencedaction() {
        return referencedaction;
    }

    public void setReferencedaction(JbpmAction referencedaction) {
        this.referencedaction = referencedaction;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JbpmAction)) {
            return false;
        }
        JbpmAction other = (JbpmAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.JbpmAction[ id=" + id + " ]";
    }
    
}
