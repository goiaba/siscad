/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "frequencia")
public class Frequencia extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="FREQUENCIA_ID_GEN", sequenceName="frequencia_idfrequencia_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FREQUENCIA_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idfrequencia")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "valor")
    private float valor;
    
    @JoinColumn(name = "idperiodoavaliacao", referencedColumnName = "idperiodoavaliacao")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private PeriodoAvaliacao periodoAvaliacao;
    
    @JoinColumn(name = "idmatricula", referencedColumnName = "idmatricula")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Matricula matricula;
    
    @JoinColumn(name = "iddisciplina", referencedColumnName = "iddisciplina")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Disciplina disciplina;

    public Frequencia() {
    }

    public Frequencia(Integer idfrequencia) {
        this.id = idfrequencia;
    }

    public Frequencia(Integer idfrequencia, float valor) {
        this.id = idfrequencia;
        this.valor = valor;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public PeriodoAvaliacao getPeriodoAvaliacao() {
        return periodoAvaliacao;
    }

    public void setPeriodoAvaliacao(PeriodoAvaliacao periodoAvaliacao) {
        this.periodoAvaliacao = periodoAvaliacao;
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

    @Override
    public int hashCode() {
        
        return new HashCodeBuilder(7,11)
                    .append(this.id)
                    .append(this.valor)
                    .toHashCode();
                        
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Frequencia frequencia = (Frequencia) obj;

        return new EqualsBuilder()
                    .append(this.id, frequencia.id)
                    .append(this.valor, frequencia.valor)
                    .isEquals();
    
    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Frequencia[ idfrequencia=" + id + " ]";
    }
    
}
