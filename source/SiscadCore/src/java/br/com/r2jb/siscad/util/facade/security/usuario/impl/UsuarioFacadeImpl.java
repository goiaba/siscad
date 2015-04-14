/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.util.facade.security.usuario.impl;

import br.com.r2jb.siscad.business.entity.Aluno;
import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.util.appconfig.ApplicationConfig;
import br.com.r2jb.siscad.util.security.SiscadSecurity;
import br.com.r2jb.siscad.util.dao.security.usuario.UsuarioDAO;
import br.com.r2jb.siscad.util.entity.security.Perfil;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import br.com.r2jb.siscad.util.entity.security.Usuario;
import br.com.r2jb.siscad.util.facade.security.perfil.PerfilFacade;
import br.com.r2jb.siscad.util.facade.security.usuario.UsuarioFacade;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("UsuarioFacade")
public class UsuarioFacadeImpl extends BaseFacadeImpl<Usuario, UsuarioDAO> implements UsuarioFacade {

    @Autowired private UsuarioDAO usuarioDAO;
    @Autowired private SiscadSecurity siscadSecurity;
    @Autowired private PerfilFacade perfilFacade;

    @Override
    public Usuario findByUsername(String username) {

        try {

            return usuarioDAO.findByUsername(username);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public List<Usuario> findAllUsersExceptAluno() {

        try {

            return usuarioDAO.findAllUsersExceptAluno();

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public List<Usuario> findByNomeAndTipoPerfil(String nome, TipoPerfil tipoPerfil, Boolean ativo) {

        try {

            return usuarioDAO.findByNomeAndTipoPerfil(nome, tipoPerfil, ativo);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Usuario> findByNomeAndTipoPerfil(String searchTerm, TipoPerfil tipoPerfil) {

        return findByNomeAndTipoPerfil(searchTerm, tipoPerfil, null);

    }

    @Override
    public Usuario findByCpf(String cpf) {
        
        verifyArgument(cpf, String.class);

        try {

            return usuarioDAO.findByCpf(cpf);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }
        
    }

    @Override
    public boolean usuarioExists(String cpf) {

        return findByCpf(cpf) != null ? true : false;
        
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void criaUsuarioParaPessoa(Pessoa pessoa, TipoPerfil tipoPerfil, Locale locale) {

        verifyLocale(locale);

        Usuario usuario = new Usuario(pessoa);

        String senha = siscadSecurity.generateRandomPassword();

        usuario.setDataCriacao(new Date());
        usuario.setLocalePadrao(locale.toString());

        Perfil perfil = perfilFacade.findByTipoPerfil(tipoPerfil);

        usuario.setPerfil(perfil);
        usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
        usuario.setAtivo(Boolean.TRUE);

        usuarioDAO.save(usuario);

        siscadSecurity.sendPassword(pessoa.getNome(), pessoa.getEmail(), usuario.getUsuario(), senha, locale);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void criaUsuarioParaPessoa(Usuario usuario) {

        usuario.setDataCriacao(new Date());
        usuario.setUsuario(usuario.getPessoa().getCpf());

        String senha = siscadSecurity.generateRandomPassword();
        usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));

        Perfil perfil = perfilFacade.findByTipoPerfil(usuario.getPerfil().getTipoPerfil());
        usuario.setPerfil(perfil);

        usuarioDAO.save(usuario);

        String[] infoLocale = usuario.getLocalePadrao().split("_");

        Locale locale = new Locale(infoLocale[0], infoLocale[1]);

        siscadSecurity.sendPassword(usuario.getPessoa().getNome(), usuario.getPessoa().getEmail(), usuario.getUsuario(), usuario.getSenha(), locale);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void criaUsuarioParaAluno(Aluno aluno, Locale locale) {

        verifyLocale(locale);

        Usuario usuario = new Usuario(aluno);

        String senha = siscadSecurity.generateRandomPassword();

        usuario.setDataCriacao(new Date());
        usuario.setLocalePadrao(locale.toString());

        Perfil perfil = perfilFacade.findByTipoPerfil(TipoPerfil.ALUNO);

        usuario.setPerfil(perfil);
        usuario.setSenha(BCrypt.hashpw(senha, BCrypt.gensalt()));
        usuario.setAtivo(Boolean.TRUE);

        usuarioDAO.save(usuario);

        siscadSecurity.sendPassword(aluno.getNome(), aluno.getEmail(), usuario.getUsuario(), senha, locale);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateDataUltimoAcesso(Usuario usuario) {

        verifyArgument("usuario", usuario, Usuario.class);
        usuario.setDataUltimoAcesso(new Date());
        usuarioDAO.merge(usuario);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remover(Usuario usuario) {

        verifyArgument("usuario", usuario, Usuario.class);
        usuario.setAtivo(Boolean.FALSE);
        usuarioDAO.merge(usuario);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void alterar(Usuario usuario) {

        verifyArgument("usuario", usuario, Usuario.class);
        usuarioDAO.merge(usuario);

    }

    private void verifyLocale(Locale locale) {

        if (null == locale) {

            String language = ApplicationConfig.getInstance().getProperty("i18n.language.default");
            String country = ApplicationConfig.getInstance().getProperty("i18n.country.default");

            locale = new Locale(language != null ? language : "pt", country != null ? country : "BR");

        }

    }

    public UsuarioDAO getUsuarioDAO() {
        return usuarioDAO;
    }

    public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public SiscadSecurity getSiscadSecurity() {
        return siscadSecurity;
    }

    public void setSiscadSecurity(SiscadSecurity siscadSecurity) {
        this.siscadSecurity = siscadSecurity;
    }

}
