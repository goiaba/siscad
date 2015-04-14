/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Joaquim
 */
@Entity
@Table(name = "notasaluno")
@NamedQueries({
    @NamedQuery(name = "NotasAluno.findAll", query = "SELECT n FROM NotasAluno n"),
    @NamedQuery(name = "NotasAluno.findByRa", query = "SELECT n FROM NotasAluno n WHERE n.ra = :ra"),
    @NamedQuery(name = "NotasAluno.findByAluno", query = "SELECT n FROM NotasAluno n WHERE n.aluno = :aluno"),
    @NamedQuery(name = "NotasAluno.findByTurma", query = "SELECT n FROM NotasAluno n WHERE n.turma = :turma"),
    @NamedQuery(name = "NotasAluno.findByDisciplina", query = "SELECT n FROM NotasAluno n WHERE n.disciplina = :disciplina"),
    @NamedQuery(name = "NotasAluno.findByNota", query = "SELECT n FROM NotasAluno n WHERE n.nota = :nota")})
public class NotasAluno extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ra")
    private Integer ra;

    @Column(name = "aluno")
    private String aluno;

    @Column(name = "turma")
    private String turma;

    @Column(name = "disciplina")
    private String disciplina;

    @Column(name = "nota")
    private String nota;
    
    public NotasAluno() {
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public String getAluno() {
        return aluno;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

   @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        NotasAluno notasAluno = (NotasAluno) obj;

        return new EqualsBuilder()
                    .append(this.aluno, notasAluno.aluno)
                    .append(this.disciplina, notasAluno.disciplina)
                    .append(this.turma, notasAluno.turma)
                    .append(this.nota, notasAluno.nota)
                    .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                     .append(this.ra)
                     .append(this.aluno)
                     .append(this.turma)
                     .append(this.disciplina)
                     .append(this.nota)
                     .toHashCode();

    }

}
