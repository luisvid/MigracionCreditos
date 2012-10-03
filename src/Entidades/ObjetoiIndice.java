/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author analian
 */
@Entity
@Table(name = "ObjetoiIndice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetoiIndice.findAll", query = "SELECT o FROM ObjetoiIndice o"),
    @NamedQuery(name = "ObjetoiIndice.findById", query = "SELECT o FROM ObjetoiIndice o WHERE o.id = :id"),
    @NamedQuery(name = "ObjetoiIndice.findByDiasAntes", query = "SELECT o FROM ObjetoiIndice o WHERE o.diasAntes = :diasAntes"),
    @NamedQuery(name = "ObjetoiIndice.findByTipoTasa", query = "SELECT o FROM ObjetoiIndice o WHERE o.tipoTasa = :tipoTasa"),
    @NamedQuery(name = "ObjetoiIndice.findByValorMas", query = "SELECT o FROM ObjetoiIndice o WHERE o.valorMas = :valorMas"),
    @NamedQuery(name = "ObjetoiIndice.findByValorPor", query = "SELECT o FROM ObjetoiIndice o WHERE o.valorPor = :valorPor")})
public class ObjetoiIndice implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private BigDecimal id;
    @Column(name = "diasAntes")
    private Integer diasAntes;
    @Column(name = "tipoTasa")
    private String tipoTasa;
    @Column(name = "valorMas")
    private Integer valorMas;
    @Column(name = "valorPor")
    private Double valorPor;
    @JoinColumn(name = "credito_id", referencedColumnName = "id")
    @ManyToOne
    private Objetoi creditoId;
    @JoinColumn(name = "indice_id", referencedColumnName = "id")
    @ManyToOne
    private Indice indiceId;

    public ObjetoiIndice() {
    }

    public ObjetoiIndice(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Integer getDiasAntes() {
        return diasAntes;
    }

    public void setDiasAntes(Integer diasAntes) {
        this.diasAntes = diasAntes;
    }

    public String getTipoTasa() {
        return tipoTasa;
    }

    public void setTipoTasa(String tipoTasa) {
        this.tipoTasa = tipoTasa;
    }

    public Integer getValorMas() {
        return valorMas;
    }

    public void setValorMas(Integer valorMas) {
        this.valorMas = valorMas;
    }

    public Double getValorPor() {
        return valorPor;
    }

    public void setValorPor(Double valorPor) {
        this.valorPor = valorPor;
    }

    public Objetoi getCreditoId() {
        return creditoId;
    }

    public void setCreditoId(Objetoi creditoId) {
        this.creditoId = creditoId;
    }

    public Indice getIndiceId() {
        return indiceId;
    }

    public void setIndiceId(Indice indiceId) {
        this.indiceId = indiceId;
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
        if (!(object instanceof ObjetoiIndice)) {
            return false;
        }
        ObjetoiIndice other = (ObjetoiIndice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.ObjetoiIndice[ id=" + id + " ]";
    }
    
}
