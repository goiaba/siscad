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
@Table(name = "curso")
@NamedQueries({
    @NamedQuery(name = "Curso.findAll", query = "SELECT c FROM Curso c"),
    @NamedQuery(name = "Curso.findByDescricao", query = "SELECT c FROM Curso c WHERE c.descricao = :descricao")})
public class Curso extends BaseEntity {
    
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="CURSO_ID_GEN", sequenceName="curso_idcurso_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CURSO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idcurso")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    
    @Basic(optional = false)
    @Column(name = "ementa")
    private String ementa;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<Serie> listaSeries =  new ArrayList<Serie>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "curso")
    private List<EscolaCurso> listaEscolaCurso =  new ArrayList<EscolaCurso>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "curso")
    private List<PeriodoAvaliacao> listaPeriodoAvaliacao = new ArrayList<PeriodoAvaliacao>();

    public Curso() {
    }

    public Curso(Integer idcurso) {
        this.id = idcurso;
    }

    public Curso(Integer idcurso, String descricao) {
        this.id = idcurso;
        this.descricao = descricao;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idcurso) {
        this.id = idcurso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public List<Serie> getListaSeries() {
        return listaSeries;
    }

    public void setListaSeries(List<Serie> listaSeries) {
        this.listaSeries = listaSeries;
    }

    public List<EscolaCurso> getListaEscolaCurso() {
        return listaEscolaCurso;
    }

    public void setListaEscolaCurso(List<EscolaCurso> listaEscolaCurso) {
        this.listaEscolaCurso = listaEscolaCurso;
    }

    public List<PeriodoAvaliacao> getListaPeriodoAvaliacao() {
        return listaPeriodoAvaliacao;
    }

    public void setListaPeriodoAvaliacao(List<PeriodoAvaliacao> listaPeriodoAvaliacao) {
        this.listaPeriodoAvaliacao = listaPeriodoAvaliacao;
    }

    public void addToEscola(Escola escola) {

        if (null == listaEscolaCurso) {
            listaEscolaCurso = new ArrayList<EscolaCurso>();
        }

        listaEscolaCurso.add(new EscolaCurso(escola, this));

    }

    public boolean removeFromEscola(Escola escola) {

        if (null != listaEscolaCurso) {
            return listaEscolaCurso.remove(new EscolaCurso(escola, this));
        }

        return false;

    }


    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Curso curso = (Curso) obj;

        return new EqualsBuilder()
                    .append(this.descricao, curso.descricao)
                    .append(this.ementa, curso.ementa)
                    .append(this.id, curso.id)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                     .append(this.id)
                     .append(this.descricao)
                     .append(this.ementa)
                     .toHashCode();

    }

    @Override
    public String toString() {

        return this.getDescricao();

    }

}
