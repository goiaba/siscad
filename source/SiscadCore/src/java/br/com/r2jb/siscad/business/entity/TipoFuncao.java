/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.CodigoFuncao;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
@Table(name = "tipofuncao")
@Inheritance(strategy = InheritanceType.JOINED)
public class TipoFuncao extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TIPO_FUNCAO_ID_GEN", sequenceName = "tipofuncao_idtipofuncao_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPO_FUNCAO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idtipofuncao")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "codigofuncao")
    @Enumerated(value = EnumType.STRING)
    private CodigoFuncao codigoFuncao;

    @JoinColumn(name = "idpessoa", referencedColumnName = "idpessoa")
    @ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Pessoa pessoa;

    @Basic(optional = false)
    @Column(name = "ativo")
    private Boolean ativo;

    public TipoFuncao() {
    }

    public TipoFuncao(Integer idTipoFuncao) {
        this.id = idTipoFuncao;
    }

    public TipoFuncao(Integer idTipoFuncao, CodigoFuncao codigoFuncao) {
        this.id = idTipoFuncao;
        this.codigoFuncao = codigoFuncao;
    }

    /**
     * @return the idtipofuncao
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * @param idtipofuncao the idtipofuncao to set
     */
    @Override
    public void setId(Integer idtipofuncao) {
        this.id = idtipofuncao;
    }

    /**
     * @return the codigoFuncao
     */
    public CodigoFuncao getCodigoFuncao() {
        return codigoFuncao;
    }

    /**
     * @param codigoFuncao the codigoFuncao to set
     */
    public void setCodigoFuncao(CodigoFuncao codigoFuncao) {
        this.codigoFuncao = codigoFuncao;
    }


    /**
     * @return the pessoa
     */
    public Pessoa getPessoa() {
        return pessoa;
    }

    /**
     * @param pessoa the pessoa to set
     */
    public void setPessoa(Pessoa pessoa) {
        
        if (this.pessoa != null) {
            
            this.pessoa.internalRemoveFuncao(this);

        }

        this.pessoa = pessoa;

        if (pessoa != null) {

            pessoa.internalAddFuncao(this);

        }
        
    }

    /**
     * @return the ativo
     */
    public Boolean getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        TipoFuncao tipoFuncao = (TipoFuncao) obj;

        return new EqualsBuilder()
                    .append(this.id, tipoFuncao.id)
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
        return "testeagregacao.entity.TipoFuncao[idtipofuncao=" + id + "]";
    }

}
