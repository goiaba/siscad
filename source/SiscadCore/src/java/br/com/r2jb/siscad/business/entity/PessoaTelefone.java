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
@Table(name = "pessoatelefone")
@NamedQueries({
    @NamedQuery(name = "PessoaTelefone.findAll", query = "SELECT p FROM PessoaTelefone p")})
public class PessoaTelefone extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="PESSOATELEFONE_ID_GEN", sequenceName="pessoatelefone_idpessoatelefone_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PESSOATELEFONE_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idpessoatelefone")
    private Integer id;

    @JoinColumn(name = "idtelefone", referencedColumnName = "idtelefone")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Telefone telefone;

    @JoinColumn(name = "idpessoa", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Pessoa pessoa;

    public PessoaTelefone() {
    }

    public PessoaTelefone(Integer idpessoatelefone) {
        this.id = idpessoatelefone;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idpessoatelefone) {
        this.id = idpessoatelefone;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {

        if (this.telefone != null) {

            this.telefone.internalRemovePessoaTelefone(this);

        }

        this.telefone = telefone;

        if (telefone != null) {

            telefone.internalAddPessoaTelefone(this);

        }
        
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {

        if (this.pessoa != null) {

            this.pessoa.internalRemovePessoaTelefone(this);

        }

        this.pessoa = pessoa;

        if (pessoa != null) {

            pessoa.internalAddPessoaTelefone(this);

        }

    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        PessoaTelefone pessoaTelefone = (PessoaTelefone) obj;

        return new EqualsBuilder()
                    .append(this.id, pessoaTelefone.id)
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
        return "br.com.r2jb.siscad.business.entity.PessoaTelefone[idpessoatelefone=" + id + "]";
    }

}
