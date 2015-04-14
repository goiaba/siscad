/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.EstadoCivil;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "pessoa")
public class Pessoa extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final String CPF = "cpf";
    public static final String EMAIL = "email";
    public static final String NOME = "nome";
    public static final String ESTADO_CIVIL = "estadoCivil";

    @Id
    @SequenceGenerator(name="PESSOA_ID_GEN", sequenceName="pessoa_idpessoa_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PESSOA_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idpessoa")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "cpf")
    private String cpf;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @Column(name = "estadocivil")
    @Enumerated(value = EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<TipoFuncao> lstFuncao = new ArrayList<TipoFuncao>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<PessoaEndereco> listaEndereco =  new ArrayList<PessoaEndereco>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoa")
    private List<PessoaTelefone> listaTelefone =  new ArrayList<PessoaTelefone>();

    public Pessoa() {
    }

    public Pessoa(Integer idpessoa) {
        this.id = idpessoa;
    }

    public Pessoa(Integer idpessoa, String cpf, String nome, EstadoCivil estadoCivil, String email) {
        this.id = idpessoa;
        this.cpf = cpf;
        this.nome = nome;
        this.estadoCivil = estadoCivil;
        this.email = email;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idpessoa) {
        this.id = idpessoa;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.replaceAll("[^0-9]", "");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TipoFuncao> getFuncoes() {

        return Collections.unmodifiableList(lstFuncao);

    }

    public void internalAddFuncao(TipoFuncao funcao) {

        lstFuncao.add(funcao);

    }

    public void internalRemoveFuncao(TipoFuncao funcao) {

        lstFuncao.remove(funcao);

    }

    public void addFuncao(TipoFuncao funcao) {

        funcao.setPessoa(this);

    }

    public void removeFuncao(TipoFuncao funcao) {

        funcao.setPessoa(null);

    }

    public List<PessoaEndereco> getListaEndereco() {

        return Collections.unmodifiableList(listaEndereco);

    }

    public void internalAddPessoaEndereco(PessoaEndereco pessoaEndereco) {

        listaEndereco.add(pessoaEndereco);

    }

    public void internalRemovePessoaEndereco(PessoaEndereco pessoaEndereco) {

        listaEndereco.remove(pessoaEndereco);

    }

    public void addPessoaEndereco(PessoaEndereco pessoaEndereco) {

        pessoaEndereco.setPessoa(this);

    }

    public void removePessoaEndereco(PessoaEndereco pessoaEndereco) {

        pessoaEndereco.setPessoa(null);

    }

    public void setListaEndereco(List<PessoaEndereco> listaEndereco) {
        this.listaEndereco = listaEndereco;
    }

    public List<PessoaTelefone> getListaTelefone() {

        return Collections.unmodifiableList(listaTelefone);

    }

    public void internalAddPessoaTelefone(PessoaTelefone pessoaTelefone) {

        listaTelefone.add(pessoaTelefone);

    }

    public void internalRemovePessoaTelefone(PessoaTelefone pessoaTelefone) {

        listaTelefone.remove(pessoaTelefone);

    }

    public void addPessoaTelefone(PessoaTelefone pessoaTelefone) {

        pessoaTelefone.setPessoa(this);

    }

    public void removePessoaTelefone(PessoaTelefone pessoaTelefone) {

        pessoaTelefone.setPessoa(null);

    }

    public void setListaTelefone(List<PessoaTelefone> listaTelefone) {
        this.listaTelefone = listaTelefone;
    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Pessoa[idpessoa=" + id + "]";
    }

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Pessoa pessoa = (Pessoa) obj;

        return new EqualsBuilder()
                    .append(this.id, pessoa.id)
                    .append(this.cpf, pessoa.cpf)
                    .append(this.email, pessoa.email)
                    .append(this.estadoCivil, pessoa.estadoCivil)
                    .append(this.nome, pessoa.nome)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                    .append(this.id)
                    .append(this.cpf)
                    .append(this.email)
                    .append(this.estadoCivil)
                    .append(this.nome)
                    .toHashCode();

    }

}
