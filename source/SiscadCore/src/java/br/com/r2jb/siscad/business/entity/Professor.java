/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.CodigoFuncao;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "professor")
public class Professor extends TipoFuncao {

    private static final long serialVersionUID = 1L;

    public static final String MATRICULA = "matricula";

    @Basic(optional = false)
    @Column(name = "matricula")
    private int matricula;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "professor")
    private List<Alocacao> listaAlocacao =  new ArrayList<Alocacao>();

    public Professor() {
        setCodigoFuncao(CodigoFuncao.PROFESSOR);
    }

    public Professor(Integer idprofessor) {
        super(idprofessor, CodigoFuncao.PROFESSOR);
    }

    public Professor(Integer idprofessor, int matricula) {
        super(idprofessor,CodigoFuncao.PROFESSOR);
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public List<Alocacao> getListaAlocacao() {
        return listaAlocacao;
    }

    public void setListaAlocacao(List<Alocacao> listaAlocacao) {
        this.listaAlocacao = listaAlocacao;
    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Professor[idprofessor=" + getId() + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Professor responsavel = (Professor) obj;

        return new EqualsBuilder()
                    .append(super.getId(), responsavel.getId())
                    .append(this.matricula, responsavel.matricula)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                     .append(super.getId())
                     .append(this.matricula)
                     .toHashCode();

    }

}
