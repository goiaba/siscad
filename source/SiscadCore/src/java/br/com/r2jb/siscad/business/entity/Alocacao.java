/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "alocacao")
public class Alocacao extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="ALOCACAO_ID_GEN", sequenceName="alocacao_idalocacao_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALOCACAO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idalocacao")
    private Integer id;

    @JoinColumn(name = "idtipofuncao", referencedColumnName = "idtipofuncao")
    @ManyToOne(optional = false)
    private Professor professor;

    @JoinColumn(name = "idturma", referencedColumnName = "idturma")
    @ManyToOne(optional = false)
    private Turma turma;

    @JoinColumn(name = "iddisciplina", referencedColumnName = "iddisciplina")
    @ManyToOne(optional = false)
    private Disciplina disciplina;

    public Alocacao() {
    }

    public Alocacao(Integer idalocacao) {
        this.id = idalocacao;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idalocacao) {
        this.id = idalocacao;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Alocacao alocacao = (Alocacao) obj;

        return new EqualsBuilder()
                    .append(this.id, alocacao.id)
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
        return "br.com.r2jb.siscad.business.entity.Alocacao[idalocacao=" + id + "]";
    }

}
