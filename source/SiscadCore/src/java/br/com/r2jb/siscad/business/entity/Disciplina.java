/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "disciplina")
@NamedQueries({
    @NamedQuery(name = "Disciplina.findAll", query = "SELECT d FROM Disciplina d"),
    @NamedQuery(name = "Disciplina.findByNome", query = "SELECT d FROM Disciplina d WHERE d.nome = :nome"),
    @NamedQuery(name = "Disciplina.findByDescricao", query = "SELECT d FROM Disciplina d WHERE d.descricao = :descricao"),
    @NamedQuery(name = "Disciplina.findByAbreviacao", query = "SELECT d FROM Disciplina d WHERE d.abreviacao = :abreviacao")})
public class Disciplina extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="DISCIPLINA_ID_GEN", sequenceName="disciplina_iddisciplina_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISCIPLINA_ID_GEN")
    @Basic(optional = false)
    @Column(name = "iddisciplina")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "abreviacao")
    private String abreviacao;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "disciplina")
    private List<Alocacao> listaAlocacao =  new ArrayList<Alocacao>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "disciplina", fetch = FetchType.LAZY)
    private Collection<MatrizCurricularDisciplina> listaMatrizCurricularDisciplina;
    
    public Disciplina() {
    }

    public Disciplina(Integer iddisciplina) {
        this.id = iddisciplina;
    }

    public Disciplina(Integer iddisciplina, String nome, String descricao, String abreviacao) {
        this.id = iddisciplina;
        this.nome = nome;
        this.descricao = descricao;
        this.abreviacao = abreviacao;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer iddisciplina) {
        this.id = iddisciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Alocacao> getListaAlocacao() {
        return listaAlocacao;
    }

    public void setListaAlocacao(List<Alocacao> listaAlocacao) {
        this.listaAlocacao = listaAlocacao;
    }
    
    public Collection<MatrizCurricularDisciplina> getMatrizCurricularDisciplinaCollection() {
        return listaMatrizCurricularDisciplina;
    }

    public void setMatrizCurricularDisciplinaCollection(Collection<MatrizCurricularDisciplina> matrizCurricularDisciplinaCollection) {
        this.listaMatrizCurricularDisciplina = matrizCurricularDisciplinaCollection;
    }

    @Override
    public String toString() {

        return this.getDescricao();

    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Disciplina disciplina = (Disciplina) obj;

        return new EqualsBuilder()
                    .append(this.descricao, disciplina.descricao)
                    .append(this.abreviacao, disciplina.abreviacao)
                    .append(this.id, disciplina.id)
                    .append(this.nome, disciplina.nome)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.descricao)
                    .append(this.abreviacao)
                    .append(this.id)
                    .append(this.nome)
                    .toHashCode();

    }

}
