/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "periodoavaliacao")
public class PeriodoAvaliacao extends BaseEntity implements Comparable<PeriodoAvaliacao> {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="PERIODOAVALIACAO_ID_GEN", sequenceName="periodoavaliacao_idperiodoavaliacao_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PERIODOAVALIACAO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idperiodoavaliacao")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "abreviacao")
    private String abreviacao;

    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "semestre")
    @Enumerated(value = EnumType.STRING)
    private Periodo semestre;

    @Basic(optional = false)
    @Column(name = "ano")
    @Enumerated(value = EnumType.STRING)
    private Ano ano;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "periodoAvaliacao")
    private List<Avaliacao> listaAvaliacao;

    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne(optional = false)
    private Curso curso;

    public PeriodoAvaliacao() {
    }

    public PeriodoAvaliacao(Integer idperiodoavaliacao) {
        this.id = idperiodoavaliacao;
    }

    public PeriodoAvaliacao(Integer idperiodoavaliacao, String abreviacao, String descricao) {
        this.id = idperiodoavaliacao;
        this.abreviacao = abreviacao;
        this.descricao = descricao;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idperiodoavaliacao) {
        this.id = idperiodoavaliacao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the semestre
     */
    public Periodo getSemestre() {
        return semestre;
    }

    /**
     * @param semestre the semestre to set
     */
    public void setSemestre(Periodo semestre) {
        this.semestre = semestre;
    }

    /**
     * @return the ano
     */
    public Ano getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(Ano ano) {
        this.ano = ano;
    }

    /**
     * @return the listaAvaliacao
     */
    public List<Avaliacao> getListaAvaliacao() {
        return listaAvaliacao;
    }

    /**
     * @param listaAvaliacao the listaAvaliacao to set
     */
    public void setListaAvaliacao(List<Avaliacao> listaAvaliacao) {
        this.listaAvaliacao = listaAvaliacao;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        PeriodoAvaliacao periodoAvaliacao = (PeriodoAvaliacao) obj;

        return new EqualsBuilder()
                    .append(this.abreviacao, periodoAvaliacao.abreviacao)
                    .append(this.descricao, periodoAvaliacao.descricao)
                    .append(this.id, periodoAvaliacao.id)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                     .append(this.id)
                     .append(this.abreviacao)
                     .append(this.descricao)
                     .toHashCode();

    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public int compareTo(PeriodoAvaliacao o) {

        String descricao1 = this.getSemestre().toString() + this.getAno().toString();
        String descricao2 = o.getSemestre().toString() + o.getAno().toString();
        
        if (descricao1.compareTo(descricao2) > 0) {
            return 1;
        } else if (descricao1.compareTo(descricao2) < 0) {
            return -1;
        } else {
            return 0;
        }

    }

    /**
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
