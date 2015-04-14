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
@Table(name = "escolacurso")
public class EscolaCurso extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="ESCOLACURSO_ID_GEN", sequenceName="escolacurso_idescolacurso_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESCOLACURSO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idescolacurso")
    private Integer id;

    @JoinColumn(name = "idescola", referencedColumnName = "idescola")
    @ManyToOne(optional = false)
    private Escola escola;

    @JoinColumn(name = "idcurso", referencedColumnName = "idcurso")
    @ManyToOne(optional = false)
    private Curso curso;

    public EscolaCurso() {
    }

    public EscolaCurso(Escola escola, Curso curso) {
        this.escola = escola;
        this.curso = curso;
    }



    public EscolaCurso(Integer idescolacurso) {
        this.id = idescolacurso;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idescolacurso) {
        this.id = idescolacurso;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                     .append(this.id)
                     .toHashCode();

    }

    @Override
    public boolean equals(Object object) {

        if ((null == object) || (object.getClass() != this.getClass())) {
            return false;
        }

        if (object == this) {
            return true;
        }

        EscolaCurso escolaCurso = (EscolaCurso) object;

        return new EqualsBuilder()
                    .append(this.id, escolaCurso.id)
                    .isEquals();

    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.EscolaCurso[idescolacurso=" + id + "]";
    }

}
