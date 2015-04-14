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
@Table(name = "pessoaendereco")
public class PessoaEndereco extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="PESSOAENDERECO_ID_GEN", sequenceName="pessoaendereco_idpessoaendereco_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PESSOAENDERECO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idpessoaendereco")
    private Integer id;

    @JoinColumn(name = "idpessoa", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Pessoa pessoa;

    @JoinColumn(name = "idendereco", referencedColumnName = "idendereco")
    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    private Endereco endereco;

    public PessoaEndereco() {
    }

    public PessoaEndereco(Integer idpessoaendereco) {
        this.id = idpessoaendereco;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idpessoaendereco) {
        this.id = idpessoaendereco;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {

        if (this.pessoa != null) {

            this.pessoa.internalRemovePessoaEndereco(this);

        }

        this.pessoa = pessoa;

        if (pessoa != null) {

            pessoa.internalAddPessoaEndereco(this);

        }

    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {

        if (this.endereco != null) {

            this.endereco.internalRemovePessoaEndereco(this);

        }

        this.endereco = endereco;

        if (endereco != null) {

            endereco.internalAddPessoaEndereco(this);

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

        PessoaEndereco pessoaEndereco = (PessoaEndereco) obj;

        return new EqualsBuilder()
                    .append(this.id, pessoaEndereco.id)
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
        return "br.com.r2jb.siscad.business.entity.PessoaEndereco[idpessoaendereco=" + id + "]";
    }

}
