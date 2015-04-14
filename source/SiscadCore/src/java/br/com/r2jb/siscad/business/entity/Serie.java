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
@Table(name = "serie")
@NamedQueries({
    @NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s"),
    @NamedQuery(name = "Serie.findByDescricao", query = "SELECT s FROM Serie s WHERE s.descricao = :descricao"),
    @NamedQuery(name = "Serie.findByOrdem", query = "SELECT s FROM Serie s WHERE s.ordem = :ordem")})
public class Serie extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="SERIE_ID_GEN", sequenceName="serie_idserie_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERIE_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idserie")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;

    @Basic(optional = false)
    @Column(name = "ordem")
    private int ordem;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
    private List<Turma> listaTurma =  new ArrayList<Turma>();

    @JoinColumn(name = "idseriesucessora", referencedColumnName = "idserie")
    @ManyToOne(optional = false)
    private Serie serieSucessora;

    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne(optional = false)
    private Curso curso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serie")
    private List<MatrizCurricular> listaMatrizCurricular =  new ArrayList<MatrizCurricular>();

    public Serie() {
    }

    public Serie(Integer idserie) {
        this.id = idserie;
    }

    public Serie(Integer idserie, String descricao, int ordem) {
        this.id = idserie;
        this.descricao = descricao;
        this.ordem = ordem;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idserie) {
        this.id = idserie;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public List<Turma> getListaTurma() {
        return listaTurma;
    }

    public void setListaTurma(List<Turma> listaTurma) {
        this.listaTurma = listaTurma;
    }

    public Serie getSerieSucessora() {
        return serieSucessora;
    }

    public void setSerieSucessora(Serie serie) {
        this.serieSucessora = serie;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<MatrizCurricular> getListaMatrizCurricular() {
        return listaMatrizCurricular;
    }

    public void setListaMatrizCurricular(List<MatrizCurricular> listaMatrizCurricular) {
        this.listaMatrizCurricular = listaMatrizCurricular;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Serie s = (Serie) obj;

        return new EqualsBuilder()
                    .append(this.descricao, s.descricao)
                    .append(this.id, s.id)
                    .append(this.ordem, s.ordem)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.id)
                    .append(this.descricao)
                    .append(this.ordem)
                    .toHashCode();

    }

    @Override
    public String toString() {

        return this.getDescricao();
        
    }

}
