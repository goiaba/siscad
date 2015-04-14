/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.TipoEndereco;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "endereco")
@NamedQueries({
    @NamedQuery(name = "Endereco.findAll", query = "SELECT e FROM Endereco e"),
    @NamedQuery(name = "Endereco.findByBairro", query = "SELECT e FROM Endereco e WHERE e.bairro = :bairro"),
    @NamedQuery(name = "Endereco.findByRua", query = "SELECT e FROM Endereco e WHERE e.rua = :rua"),
    @NamedQuery(name = "Endereco.findByCep", query = "SELECT e FROM Endereco e WHERE e.cep = :cep"),
    @NamedQuery(name = "Endereco.findByCidade", query = "SELECT e FROM Endereco e WHERE e.cidade = :cidade"),
    @NamedQuery(name = "Endereco.findByEstado", query = "SELECT e FROM Endereco e WHERE e.estado = :estado")})
public class Endereco extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static String RUA = "rua";
    public static String NUMERO = "numeroCasa";
    public static String COMPLEMENTO = "complemento";
    public static String BAIRRO = "bairro";
    public static String TIPO = "tipoEndereco";
    public static String CIDADE = "cidade";
    public static String ESTADO = "estado";
    public static String CEP = "cep";
    public static String LISTA_ENDERECOS = "listaEnderecos";

    @Id
    @SequenceGenerator(name="ENDERECO_ID_GEN", sequenceName="endereco_idendereco_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENDERECO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idendereco")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "rua")
    private String rua;

    @Basic(optional = false)
    @Column(name = "numero")
    private int numero;

    @Basic(optional = false)
    @Column(name = "complemento")
    private String complemento;

    @Basic(optional = false)
    @Column(name = "bairro")
    private String bairro;

    @Basic(optional = false)
    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING)
    private TipoEndereco tipo;

    @Basic(optional = false)
    @Column(name = "cidade")
    private String cidade;

    @Basic(optional = false)
    @Column(name = "estado")
    private String estado;

    @Basic(optional = false)
    @Column(name = "cep")
    private String cep;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "endereco")
    private List<PessoaEndereco> listaPessoaEndereco =  new ArrayList<PessoaEndereco>();

    @OneToOne(mappedBy = "endereco")
    private Escola escola;

    public Endereco() {
    }

    public Endereco(Integer idendereco) {
        this.id = idendereco;
    }

    public Endereco(Integer idendereco, String rua, int numero, String complemento, String bairro, TipoEndereco tipo, String cep, String cidade, String estado) {

        this.id = idendereco;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.tipo = tipo;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idendereco) {
        this.id = idendereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep.replaceAll("[^0-9]", "");
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoEndereco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEndereco tipo) {
        this.tipo = tipo;
    }

    public List<PessoaEndereco> getListaPessoaEndereco() {

        return Collections.unmodifiableList(listaPessoaEndereco);

    }

    public void internalAddPessoaEndereco(PessoaEndereco pessoaEndereco) {

        listaPessoaEndereco.add(pessoaEndereco);

    }

    public void internalRemovePessoaEndereco(PessoaEndereco pessoaEndereco) {

        listaPessoaEndereco.remove(pessoaEndereco);

    }

    public void addPessoaEndereco(PessoaEndereco pessoaEndereco) {

        pessoaEndereco.setEndereco(this);

    }

    public void removePessoaEndereco(PessoaEndereco pessoaEndereco) {

        pessoaEndereco.setEndereco(null);

    }

    public void setListaPessoaEndereco(List<PessoaEndereco> listaPessoaEndereco) {
        this.listaPessoaEndereco = listaPessoaEndereco;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Endereco[idendereco=" + id + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Endereco endereco = (Endereco) obj;

        return new EqualsBuilder()
                    .append(this.id, endereco.id)
                    .append(this.rua, endereco.rua)
                    .append(this.numero, endereco.numero)
                    .append(this.complemento, endereco.complemento)
                    .append(this.bairro, endereco.bairro)
                    .append(this.tipo, endereco.tipo)
                    .append(this.cidade, endereco.cidade)
                    .append(this.estado, endereco.estado)
                    .append(this.cep, endereco.cep)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                    .append(this.id)
                    .append(this.rua)
                    .append(this.numero)
                    .append(this.complemento)
                    .append(this.bairro)
                    .append(this.tipo)
                    .append(this.cidade)
                    .append(this.estado)
                    .append(this.cep)
                    .toHashCode();

    }

}
