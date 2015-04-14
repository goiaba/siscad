/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "avaliacao")
@NamedQueries({
    @NamedQuery(name = "Avaliacao.findAll", query = "SELECT a FROM Avaliacao a"),
    @NamedQuery(name = "Avaliacao.findByDescricao", query = "SELECT a FROM Avaliacao a WHERE a.descricao = :descricao"),
    @NamedQuery(name = "Avaliacao.findByPeso", query = "SELECT a FROM Avaliacao a WHERE a.peso = :peso")})
public class Avaliacao extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="AVALIACAO_ID_GEN", sequenceName="avaliacao_idavaliacao_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AVALIACAO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idavaliacao")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "abreviacao")
    private String abreviacao;

    @Basic(optional = false)
    @Column(name = "peso")
    private Float peso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "avaliacao")
    private List<Nota> listaNota =  new ArrayList<Nota>();

    @JoinColumn(name = "idperiodoavaliacao", referencedColumnName = "idperiodoavaliacao")
    @ManyToOne(optional = false)
    private PeriodoAvaliacao periodoAvaliacao;

    public Avaliacao() {
    }

    public Avaliacao(Integer idavaliacao) {
        this.id = idavaliacao;
    }

    public Avaliacao(Integer idavaliacao, String descricao, Float peso) {
        this.id = idavaliacao;
        this.descricao = descricao;
        this.peso = peso;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idavaliacao) {
        this.id = idavaliacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public List<Nota> getListaNota() {
        return listaNota;
    }

    public void setListaNota(List<Nota> notas) {
        this.listaNota = notas;
    }

    /**
     * @return the periodoAvaliacao
     */
    public PeriodoAvaliacao getPeriodoAvaliacao() {
        return periodoAvaliacao;
    }

    /**
     * @param periodoAvaliacao the periodoAvaliacao to set
     */
    public void setPeriodoAvaliacao(PeriodoAvaliacao periodoAvaliacao) {
        this.periodoAvaliacao = periodoAvaliacao;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Avaliacao aval = (Avaliacao) obj;

        return new EqualsBuilder()
                    .append(this.descricao, aval.descricao)
                    .append(this.id, aval.id)
                    .append(this.peso, aval.peso)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.descricao)
                    .append(this.id)
                    .append(this.peso)
                    .toHashCode();

    }

    @Override
    public String toString() {

        return this.getDescricao();

    }

}
