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
import javax.persistence.FetchType;
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
@Table(name = "matrizcurricular")
public class MatrizCurricular extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="MATRIZCURRICULAR_ID_GEN", sequenceName="matrizcurricular_idmatrizcurricular_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATRIZCURRICULAR_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idmatrizcurricular")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "semestre")
    @Enumerated(value = EnumType.STRING)
    private Periodo periodo;

    @Basic(optional = false)
    @Column(name = "ano")
    @Enumerated(value = EnumType.STRING)
    private Ano ano;

    @JoinColumn(name = "idserie", referencedColumnName = "idserie")
    @ManyToOne(optional = false)
    private Serie serie;

    @JoinColumn(name = "idescola", referencedColumnName = "idescola")
    @ManyToOne(optional = false)
    private Escola escola;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REFRESH}, mappedBy = "matrizCurricular", fetch = FetchType.LAZY)
    private List<MatrizCurricularDisciplina> listaMatrizCurricularDisciplina;

    public MatrizCurricular() {
    }

    public MatrizCurricular(Integer idmatrizcurricular) {
        this.id = idmatrizcurricular;
    }

    public MatrizCurricular(Integer idmatrizcurricular, Ano ano, Periodo periodo) {
        this.id = idmatrizcurricular;
        this.ano = ano;
        this.periodo = periodo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idmatrizcurricular) {
        this.id = idmatrizcurricular;
    }

    public Ano getAno() {
        return ano;
    }

    public void setAno(Ano ano) {
        this.ano = ano;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public List<MatrizCurricularDisciplina> getListaMatrizCurricularDisciplina() {
        return listaMatrizCurricularDisciplina;
    }

    public void setListaMatrizCurricularDisciplina(List<MatrizCurricularDisciplina> listaMatrizCurricularDisciplina) {
        this.listaMatrizCurricularDisciplina = listaMatrizCurricularDisciplina;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        MatrizCurricular matrizCurricular = (MatrizCurricular) obj;

        return new EqualsBuilder()
                    .append(this.id, matrizCurricular.id)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                     .append(this.id)
                     .toHashCode();

    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.MatrizCurricular[idmatrizcurricular=" + id + "]";
    }

}
