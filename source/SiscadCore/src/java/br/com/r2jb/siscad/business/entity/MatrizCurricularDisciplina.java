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
@Table(name = "matrizcurriculardisciplina")
public class MatrizCurricularDisciplina extends BaseEntity {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name="MATRIZCURRICULARDISCIPLINA_ID_GEN", sequenceName="matrizcurriculardisciplina_idmatrizcurriculardisciplina_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MATRIZCURRICULARDISCIPLINA_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idMatrizCurricularDisciplina")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "ementa")
    private String ementa;
    
    @JoinColumn(name = "idmatrizcurricular", referencedColumnName = "idmatrizcurricular")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MatrizCurricular matrizCurricular;
    
    @JoinColumn(name = "iddisciplina", referencedColumnName = "iddisciplina")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Disciplina disciplina;

    public MatrizCurricularDisciplina() {
    }

    public MatrizCurricularDisciplina(Integer idmatrizcurriculardisciplina) {
        this.id = idmatrizcurriculardisciplina;
    }

    public MatrizCurricularDisciplina(Integer idmatrizcurriculardisciplina, String ementa) {
        this.id = idmatrizcurriculardisciplina;
        this.ementa = ementa;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idmatrizcurriculardisciplina) {
        this.id = idmatrizcurriculardisciplina;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public MatrizCurricular getMatrizCurricular() {
        return matrizCurricular;
    }

    public void setMatrizCurricular(MatrizCurricular matrizCurricular) {
        this.matrizCurricular = matrizCurricular;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder()
                    .append(this.id)
                    .append(this.ementa)
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

        MatrizCurricularDisciplina mcd = (MatrizCurricularDisciplina) obj;

        return new EqualsBuilder()
                    .append(this.ementa, mcd.ementa)
                    .append(this.id, mcd.id)
                    .isEquals();
    
    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.MatrizCurricularDisciplina[ idmatrizcurriculardisciplina=" + id + " ]";
    }
    
}
