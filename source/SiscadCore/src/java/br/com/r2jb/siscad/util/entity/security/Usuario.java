/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.util.entity.security;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.BaseEntity;
import br.com.r2jb.siscad.business.entity.Pessoa;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "sec_usuario")
public class Usuario extends BaseEntity {

    private Integer id;

    private Aluno aluno;
    private Pessoa pessoa;

    private String usuario;
    private String senha;
    private Perfil perfil;
    private Date dataCriacao;
    private Date dataUltimoAcesso;
    private Date dataExpiracao;
    private String localePadrao;
    private Boolean ativo;

    protected Usuario() {
        
    }

    public Usuario(Aluno aluno) {

        if (null == aluno) {

            throw new IllegalArgumentException("null passado para o construtor br.com.r2jb.siscad.util.security.entity.Usuario(Aluno)");

        }

        this.aluno = aluno;
        usuario = Integer.toString(this.aluno.getRa());

    }

    public Usuario(Pessoa pessoa) {

        if (null == pessoa) {

            throw new IllegalArgumentException("null passado para o construtor br.com.r2jb.siscad.util.security.entity.Usuario(Professor)");

        }

        this.pessoa = pessoa;
        usuario = this.pessoa.getCpf();

    }

    public boolean isSecretario() {

        return this.getPerfil().getTipoPerfil().equals(TipoPerfil.SECRETARIO);

    }
    
    public boolean isDiretor() {
        
        return this.getPerfil().getTipoPerfil().equals(TipoPerfil.DIRETOR);
        
    }

    public boolean isAdministrador() {

        return this.getPerfil().getTipoPerfil().equals(TipoPerfil.ADMINISTRADOR);

    }

    public boolean isProfessor() {

        return this.getPerfil().getTipoPerfil().equals(TipoPerfil.PROFESSOR);

    }

    public boolean isAluno() {

        return this.getPerfil().getTipoPerfil().equals(TipoPerfil.ALUNO);

    }

    public boolean isResponsavel() {

        return this.getPerfil().getTipoPerfil().equals(TipoPerfil.RESPONSAVEL);

    }

    public boolean isAoMenosProfessor() {

        return this.getPerfil().getTipoPerfil().aoMenosProfessor();

    }

    public boolean isAoMenosSecretario() {

        return this.getPerfil().getTipoPerfil().aoMenosSecretario();

    }

    public boolean isAoMenosDiretor() {

        return this.getPerfil().getTipoPerfil().aoMenosDiretor();

    }

    public boolean isAoMenosAdministrador() {

        return this.getPerfil().getTipoPerfil().aoMenosAdministrador();

    }

    @Id
    @SequenceGenerator(name = "USUARIO_ID_GEN", sequenceName = "sec_usuario_idusuario_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID_GEN")
    @Basic(optional = false)
    @Column(name = "idusuario")
    @Override
    public Integer getId() {return this.id;}
    @Override
    public void setId(Integer id) {this.id = id;}

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="idAluno")
    public Aluno getAluno() {return aluno;}
    public void setAluno(Aluno aluno) {this.aluno = aluno;}

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="idPessoa")
    public Pessoa getPessoa() {return pessoa;}
    public void setPessoa(Pessoa pessoa) {this.pessoa = pessoa;}

    @Basic(optional = false)
    @Column(name = "usuario")
    public String getUsuario() {return usuario;}
    public void setUsuario(String usuario) {this.usuario = usuario;}

    @Basic(optional = false)
    @Column(name = "senha")
    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}


    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "idPerfil")
    public Perfil getPerfil() {return perfil;}
    public void setPerfil(Perfil perfil) {this.perfil = perfil;}

    @Basic(optional = false)
    @Column(name = "dataCriacao")
    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getDataCriacao() {return dataCriacao;}
    public void setDataCriacao(Date dataCriacao) {this.dataCriacao = dataCriacao;}

    @Basic(optional = false)
    @Column(name = "dataUltimoAcesso")
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getDataUltimoAcesso() {return dataUltimoAcesso;}
    public void setDataUltimoAcesso(Date dataUltimoAcesso) {this.dataUltimoAcesso = dataUltimoAcesso;}

    @Basic(optional = false)
    @Column(name = "dataExpiracao")
    @Temporal(value=TemporalType.TIMESTAMP)
    public Date getDataExpiracao() {return dataExpiracao;}
    public void setDataExpiracao(Date dataExpiracao) {this.dataExpiracao = dataExpiracao;}

    @Basic(optional = false)
    @Column(name = "localePadrao")
    public String getLocalePadrao() {return localePadrao;}
    public void setLocalePadrao(String localePadrao) {this.localePadrao = localePadrao;}

    @Basic(optional = false)
    @Column(name = "ativo")
    public Boolean getAtivo() {return ativo;}
    public void setAtivo(Boolean ativo) {this.ativo = ativo;}

    @Override
    public boolean equals(Object obj) {

        if ((null == obj) || (obj.getClass() != this.getClass())) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Usuario usr = (Usuario) obj;

        return new EqualsBuilder()
                    .append(this.getId(), usr.getId())
                    .append(this.getAtivo(), usr.getAtivo())
                    .append(this.getDataCriacao(), usr.getDataCriacao())
                    .append(this.getDataExpiracao(), usr.getDataExpiracao())
                    .append(this.getDataUltimoAcesso(), usr.getDataUltimoAcesso())
                    .append(this.getLocalePadrao(), usr.getLocalePadrao())
                    .append(this.getSenha(), usr.getSenha())
                    .append(this.getUsuario(), usr.getUsuario())
                    .isEquals();

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(3,5)
                    .append(this.getId())
                    .append(this.getAtivo())
                    .append(this.getDataCriacao())
                    .append(this.getDataExpiracao())
                    .append(this.getDataUltimoAcesso())
                    .append(this.getLocalePadrao())
                    .append(this.getSenha())
                    .append(this.getUsuario())
                    .toHashCode();

    }

}
