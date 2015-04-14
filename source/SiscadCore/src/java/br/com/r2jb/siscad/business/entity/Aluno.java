/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import br.com.r2jb.siscad.business.util.Sexo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Entity
@Table(name = "aluno")
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a"),
    @NamedQuery(name = "Aluno.findByRa", query = "SELECT a FROM Aluno a WHERE a.ra = :ra"),
    @NamedQuery(name = "Aluno.findByNome", query = "SELECT a FROM Aluno a WHERE a.nome = :nome"),
    @NamedQuery(name = "Aluno.findByDatanascimento", query = "SELECT a FROM Aluno a WHERE a.datanascimento = :datanascimento"),
    @NamedQuery(name = "Aluno.findBySexo", query = "SELECT a FROM Aluno a WHERE a.sexo = :sexo")})
public class Aluno extends BaseEntity {
        
    private static final long serialVersionUID = 1L;

    public static final String NOME = "nomeAluno";
    public static final String RA = "ra";
    public static final String SEXO = "sexo";
    public static final String DATA_NASCIMENTO = "dataNascimento";
    public static final String LOCAL_NASCIMENTO = "localNascimento";
    public static final String NACIONALIDADE = "nacionalidade";
    public static final String EMAIL = "email";

    @Id
    @SequenceGenerator(name="ALUNO_ID_GEN", sequenceName="aluno_idaluno_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALUNO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idaluno")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "ra")
    private Integer ra;

    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;

    @Basic(optional = false)
    @Column(name = "datanascimento")
    @Temporal(TemporalType.DATE)
    private Date datanascimento;

    @Basic(optional = false)
    @Column(name = "sexo")
    @Enumerated(value = EnumType.STRING)
    private Sexo sexo;

    @Basic(optional = false)
    @Column(name = "localnascimento")
    private String localNascimento;

    @Basic(optional = false)
    @Column(name = "nacionalidade")
    private String nacionalidade;

    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    @JoinColumn(name = "idtipofuncao", referencedColumnName = "idtipofuncao")
    @ManyToOne(optional = false)
    private Responsavel responsavel;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "aluno")
    private List<Matricula> listaMatriculas = new ArrayList<Matricula>();

    @Basic(optional = false)
    @Column(name = "ativo")
    private Boolean ativo;

    public Aluno() {
    }

    public Aluno(Integer idaluno) {
        this.id = idaluno;
    }

    public Aluno(Integer idaluno, Integer ra, String nome, Date datanascimento, Sexo sexo) {
        this.id = idaluno;
        this.ra = ra;
        this.nome = nome;
        this.datanascimento = datanascimento;
        this.sexo = sexo;
    }

    @PreRemove
    public void preRemove() {
        
        setResponsavel(null);
        
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer idaluno) {
        this.id = idaluno;
    }

    public Integer getRa() {
        return ra;
    }

    public void setRa(Integer ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return datanascimento;
    }

    public void setDataNascimento(Date datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<Matricula> getListaMatriculas() {
        if (listaMatriculas.equals(Collections.EMPTY_LIST))
                return new ArrayList<Matricula>();
        return listaMatriculas;
    }

    public void setListaMatriculas(List<Matricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {

        if (this.responsavel != null) {

            this.responsavel.internalRemoveAluno(this);

        }

        this.responsavel = responsavel;

        if (responsavel != null) {

            responsavel.internalAddAluno(this);

        }

    }

    /**
     * @return the localNascimento
     */
    public String getLocalNascimento() {
        return localNascimento;
    }

    /**
     * @param localNascimento the localNascimento to set
     */
    public void setLocalNascimento(String localNascimento) {
        this.localNascimento = localNascimento;
    }

    /**
     * @return the nacionalidade
     */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
     * @param nacionalidade the nacionalidade to set
     */
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

        Aluno aluno = (Aluno) obj;

        return new EqualsBuilder()
                    .append(this.datanascimento, aluno.datanascimento)
                    .append(this.email, aluno.email)
                    .append(this.ativo, aluno.ativo)
                    .append(this.id, aluno.id)
                    .append(this.nacionalidade, aluno.nacionalidade)
                    .append(this.nome, aluno.nome)
                    .append(this.ra, aluno.ra)
                    .append(this.sexo, aluno.sexo)
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(7,11)
                    .append(this.datanascimento)
                    .append(this.email)
                    .append(this.ativo)
                    .append(this.id)
                    .append(this.nacionalidade)
                    .append(this.nome)
                    .append(this.ra)
                    .append(this.sexo)
                    .toHashCode();

    }

    @Override
    public String toString() {
        return "br.com.r2jb.siscad.business.entity.Aluno[idaluno=" + id + "]";
    }

}
