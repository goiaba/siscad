/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "escolatelefone")
public class EscolaTelefone extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="ESCOLATELEFONE_ID_GEN", sequenceName="escolatelefone_idescolatelefone_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESCOLATELEFONE_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idescolatelefone")
    private Integer id;

    @JoinColumn(name = "idtelefone", referencedColumnName = "idtelefone")
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Telefone telefone;

    @JoinColumn(name = "idescola", referencedColumnName = "idescola")
    @ManyToOne(optional = false)
    private Escola escola;

    public EscolaTelefone() {
    }

    public EscolaTelefone(Integer idescolatelefone) {
        this.id = idescolatelefone;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idescolatelefone) {
        this.id = idescolatelefone;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        EscolaTelefone escolaTelefone = (EscolaTelefone) obj;

        return new EqualsBuilder()
                    .append(this.id, escolaTelefone.id)
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
        return "br.com.r2jb.siscad.business.entity.EscolaTelefone[idescolatelefone=" + id + "]";
    }

}
