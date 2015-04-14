/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.professor.impl;


import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.PessoaEndereco;
import br.com.r2jb.siscad.business.entity.PessoaTelefone;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.endereco.EnderecoFacade;
import br.com.r2jb.siscad.business.exception.PessoaJaExisteException;
import br.com.r2jb.siscad.business.facade.professor.ProfessorFacade;
import br.com.r2jb.siscad.business.facade.telefone.TelefoneFacade;
import br.com.r2jb.siscad.dao.professor.ProfessorDAO;
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
@Service("ProfessorFacade")
public class ProfessorFacadeImpl extends BaseFacadeImpl<Professor, ProfessorDAO> implements ProfessorFacade {

    @Autowired private ProfessorDAO professorDAO;
    @Autowired private UsuarioFacade usuarioFacade;
    @Autowired private TelefoneFacade telefoneFacade;
    @Autowired private EnderecoFacade enderecoFacade;
    
    @Override
    public Professor findByCpf(String cpf) {
        
        verifyArgument(cpf, String.class);

        try {

            return professorDAO.findByCpf(cpf);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public boolean professorExiste(String cpf) {

        return (findByCpf(cpf) != null) ? true : false;

    }

    @Override
    public Professor findByMatricula(Integer matricula) {

        verifyArgument(matricula, Integer.class);
        
        try {

            return professorDAO.findByMatricula(matricula);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }
    
    @Override
    public List<Professor> findByNome(String nome) {

        verifyArgument(nome, String.class);

        try {

            return professorDAO.findByNome(nome);

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public List<Professor> findByNome(String nome, Boolean ativo) {

        verifyArgument(nome, String.class);
        
        try {

            return professorDAO.findByNome(nome, ativo);

        } catch (EmptyResultDataAccessException e) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean inserirProfessor(Professor professor, List<Endereco> enderecos, List<Telefone> telefones, Locale locale) throws PessoaJaExisteException {

        verifyArgument("professor", professor, Professor.class);
        verifyArgument("enderecos", enderecos, List.class);
        verifyArgument("telefones", telefones, List.class);
        
        if (professorExiste(professor.getPessoa().getCpf())) {

            throw new PessoaJaExisteException(professor.getPessoa().getCpf(), TipoPerfil.PROFESSOR.getDescricao(locale));

        }

        for (Endereco endereco : enderecos) {

            criarRelacaoProfessorEndereco(professor, endereco);

        }

        for (Telefone telefone : telefones) {

            criarRelacaoProfessorTelefone(professor, telefone);

        }

        professor.setMatricula(gerarMatricula());
        professor.setAtivo(Boolean.TRUE);
        
        professorDAO.save(professor);

        usuarioFacade.criaUsuarioParaPessoa(professor.getPessoa(), TipoPerfil.PROFESSOR, locale);

        return true;

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Professor alterarProfessor(Professor professor) {

        verifyArgument(professor, Professor.class);
        
        return professorDAO.merge(professor);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void removerProfessor(Professor professor) {

        verifyArgument(professor, Professor.class);

        professor.setAtivo(Boolean.FALSE);

        professorDAO.merge(professor);

    }

    private int gerarMatricula() {

        return professorDAO.getProximaMatricula().intValue();

    }

    private void criarRelacaoProfessorEndereco(Professor professor, Endereco endereco) {
        
        PessoaEndereco pe = new PessoaEndereco();

        pe.setPessoa(professor.getPessoa());
        pe.setEndereco(endereco);

        endereco.addPessoaEndereco(pe);
        professor.getPessoa().addPessoaEndereco(pe);

    }

    private void criarRelacaoProfessorTelefone(Professor professor, Telefone telefone) {

        PessoaTelefone pt = new PessoaTelefone();

        pt.setPessoa(professor.getPessoa());
        pt.setTelefone(telefone);

        telefone.addPessoaTelefone(pt);
        professor.getPessoa().addPessoaTelefone(pt);

    }

    /**
     * @return the professorDAO
     */
    public ProfessorDAO getProfessorDAO() {
        return professorDAO;
    }

    /**
     * @param professorDAO the professorDAO to set
     */
    public void setProfessorDAO(ProfessorDAO professorDAO) {
        this.professorDAO = professorDAO;
    }

    /**
     * @return the usuarioFacade
     */
    public UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    /**
     * @param usuarioFacade the usuarioFacade to set
     */
    public void setUsuarioFacade(UsuarioFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    /**
     * @return the telefoneFacade
     */
    public TelefoneFacade getTelefoneFacade() {
        return telefoneFacade;
    }

    /**
     * @param telefoneFacade the telefoneFacade to set
     */
    public void setTelefoneFacade(TelefoneFacade telefoneFacade) {
        this.telefoneFacade = telefoneFacade;
    }

    /**
     * @return the enderecoFacade
     */
    public EnderecoFacade getEnderecoFacade() {
        return enderecoFacade;
    }

    /**
     * @param enderecoFacade the enderecoFacade to set
     */
    public void setEnderecoFacade(EnderecoFacade enderecoFacade) {
        this.enderecoFacade = enderecoFacade;
    }

}
