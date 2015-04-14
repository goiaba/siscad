/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.responsavel.impl;


import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.PessoaEndereco;
import br.com.r2jb.siscad.business.entity.PessoaTelefone;
import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import br.com.r2jb.siscad.business.exception.ResponsavelPossuiDependentesException;
import br.com.r2jb.siscad.business.facade.responsavel.ResponsavelFacade;
import br.com.r2jb.siscad.dao.responsavel.ResponsavelDAO;
import br.com.r2jb.siscad.util.entity.security.TipoPerfil;
import br.com.r2jb.siscad.util.facade.security.usuario.UsuarioFacade;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("ResponsavelFacade")
public class ResponsavelFacadeImpl extends BaseFacadeImpl<Responsavel, ResponsavelDAO> implements ResponsavelFacade {

    @Autowired private ResponsavelDAO responsavelDAO;
    @Autowired private UsuarioFacade usuarioFacade;
    
    @Override
    public Responsavel findByCPF(String cpf) {
        
        verifyArgument(cpf, String.class);

        try {

            return responsavelDAO.findByCpf(cpf);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

@Override
    public List<Responsavel> findByNome(String nome) {

        verifyArgument(nome, String.class);

        try {

            return responsavelDAO.findByNome(nome);

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Responsavel> findByNome(String nome, Boolean ativo) {

        verifyArgument(nome, String.class);

        try {

            return responsavelDAO.findByNome(nome, ativo);

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }


    @Override
    public boolean responsavelExiste(String cpf) {

        verifyArgument(cpf, String.class);
        
        return (findByCPF(cpf) != null) ? true : false;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean inserirResponsavel(Responsavel responsavel, List<Endereco> enderecos, List<Telefone> telefones, Locale locale) throws PessoaJaExisteException {

        verifyArgument("responsavel", responsavel, Responsavel.class);
        verifyArgument("enderecos", enderecos, List.class);
        verifyArgument("telefones", telefones, List.class);
        
        if (responsavelExiste(responsavel.getPessoa().getCpf())) {

            throw new PessoaJaExisteException(responsavel.getPessoa().getCpf(), TipoPerfil.RESPONSAVEL.getDescricao(locale));

        }

        for (Endereco endereco : enderecos) {

            criarRelacaoResponsavelEndereco(responsavel, endereco);

        }

        for (Telefone telefone : telefones) {

            criarRelacaoResponsavelTelefone(responsavel, telefone);

        }

        responsavel.setAtivo(Boolean.TRUE);

        responsavelDAO.save(responsavel);

        usuarioFacade.criaUsuarioParaPessoa(responsavel.getPessoa(), TipoPerfil.RESPONSAVEL, locale);

        return true;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Responsavel alterarResponsavel(Responsavel responsavel) {
        
        verifyArgument(responsavel, Responsavel.class);

        return responsavelDAO.merge(responsavel);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerResponsavel(Responsavel responsavel) throws ResponsavelPossuiDependentesException {

        verifyArgument(responsavel, Responsavel.class);

        if (responsavel.possuiDependentes()) {

            throw new ResponsavelPossuiDependentesException(responsavel.getListaAluno());

        }

        responsavel.setAtivo(Boolean.FALSE);

        responsavelDAO.merge(responsavel);

    }

    private Telefone criarRelacaoResponsavelTelefone(Responsavel responsavel, Telefone telefone) {

        PessoaTelefone pt = new PessoaTelefone();

        pt.setPessoa(responsavel.getPessoa());
        pt.setTelefone(telefone);

        telefone.addPessoaTelefone(pt);
        responsavel.getPessoa().addPessoaTelefone(pt);

        return telefone;

    }

    private Endereco criarRelacaoResponsavelEndereco(Responsavel responsavel, Endereco endereco) {

        PessoaEndereco pe = new PessoaEndereco();

        pe.setPessoa(responsavel.getPessoa());
        pe.setEndereco(endereco);

        endereco.addPessoaEndereco(pe);
        responsavel.getPessoa().addPessoaEndereco(pe);
        
        return endereco;

    }

    public void setResponsavelDAO(ResponsavelDAO responsavelDAO) {
        this.responsavelDAO = responsavelDAO;
    }

    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

}
