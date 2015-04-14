/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "nota")
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n"),
    @NamedQuery(name = "Nota.findByValor", query = "SELECT n FROM Nota n WHERE n.valor = :valor")})
public class Nota extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @SequenceGenerator(name="NOTA_ID_GEN", sequenceName="nota_idnota_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NOTA_ID_GEN")
    @Column(name = "idnota")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "valor")
    private float valor;

    @JoinColumn(name = "idmatricula", referencedColumnName = "idmatricula")
    @ManyToOne(optional = false)
    private Matricula matricula;

    @JoinColumn(name = "iddisciplina", referencedColumnName = "iddisciplina")
    @ManyToOne(optional = false)
    private Disciplina disciplina;

    @JoinColumn(name = "idavaliacao", referencedColumnName = "idavaliacao")
    @ManyToOne(optional = false)
    private Avaliacao avaliacao;

    public Nota() {
    }

    public Nota(Integer idnota) {
        this.id = idnota;
    }

    public Nota(Integer idnota, float valor) {
        this.id = idnota;
        this.valor = valor;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idnota) {
        this.id = idnota;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Nota aluno = (Nota) obj;

        return new EqualsBuilder()
                    .append(this.id, aluno.id)
                    .append(this.valor, aluno.valor)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.id)
                    .append(this.valor)
                    .toHashCode();

    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Nota[idnota=" + id + "]";
    }

}
