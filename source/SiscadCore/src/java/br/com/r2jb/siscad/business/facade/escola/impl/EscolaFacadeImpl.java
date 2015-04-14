/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.escola.impl;


import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.EscolaCurso;
import br.com.r2jb.siscad.business.entity.EscolaTelefone;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.exception.EnderecoRequeridoException;
import br.com.r2jb.siscad.business.exception.EscolaContemDadosAcademicosException;
import br.com.r2jb.siscad.business.exception.EscolaJaCadastradaException;
import br.com.r2jb.siscad.business.exception.TelefoneRequeridoException;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.escola.EscolaFacade;
import br.com.r2jb.siscad.business.facade.turma.TurmaFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.curso.CursoDAO;
import br.com.r2jb.siscad.dao.escola.EscolaDAO;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("EscolaFacade")
public class EscolaFacadeImpl extends BaseFacadeImpl<Escola, EscolaDAO> implements EscolaFacade {

    @Autowired private EscolaDAO escolaDao;
    @Autowired private CursoDAO cursoDao;
    @Autowired private TurmaFacade turmaFacade;
    
    @Override
    public List<Escola> findEscolasPorProfessor(Professor professor) {

        verifyArgument(professor, Professor.class);

        try {

            return escolaDao.findEscolasPorProfessor(professor);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    @Override
    public Escola findByNome(String nomeEscola) {

        verifyArgument(nomeEscola, String.class);

        try {

            return escolaDao.findByNome(nomeEscola);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public List<Escola> findByProfessorCursoSemestreEAno(Professor professor, Curso curso, Periodo semestre, Ano ano) {

        try {

            return escolaDao.findEscolas(professor, curso, semestre, ano);

        } catch (EmptyResultDataAccessException ex) {

            return Collections.EMPTY_LIST;

        }

    }

    public void setEscolaDAO(EscolaDAO escolaDAO) {
        this.escolaDao = escolaDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public boolean inserir(Escola escola, List<Endereco> enderecos, List<Telefone> telefones, List<Curso> cursos) throws EnderecoRequeridoException, TelefoneRequeridoException, EscolaJaCadastradaException {

        verifyArgument("escola", escola, Escola.class);
        verifyArgument("cursos", cursos, List.class);
        verifyArgument("enderecos", enderecos, List.class);
        verifyArgument("telefones", telefones, List.class);

        if (enderecos.isEmpty()) {

            throw new EnderecoRequeridoException();

        }

        if (telefones.isEmpty()) {

            throw new TelefoneRequeridoException();

        }

        if (isEscolaCadastrada(escola.getCnpj())) {

            throw new EscolaJaCadastradaException();

        }

        escola.setEndereco(enderecos.get(0));

        for (Curso curso : cursos) {

            criarRelacaoEscolaCurso(escola, curso);

        }

        for (Telefone telefone : telefones) {

            criarRelacaoEscolaTefone(escola, telefone);

        }

        escolaDao.save(escola);

        return true;

    }

    private void criarRelacaoEscolaCurso(Escola escola, Curso curso) {

        EscolaCurso ec = new EscolaCurso();

        ec.setEscola(escola);
        ec.setCurso(curso);

        escola.getListaEscolaCurso().add(ec);
        curso.getListaEscolaCurso().add(ec);

    }

    private void criarRelacaoEscolaTefone(Escola escola, Telefone telefone) {

        EscolaTelefone et = new EscolaTelefone();

        et.setEscola(escola);
        et.setTelefone(telefone);

        escola.getListaEscolaTelefone().add(et);
        telefone.getListaEscolaTelefone().add(et);

    }

    @Override
    public Escola findByCnpj(String cnpj) {

        verifyArgument(cnpj, String.class);

        try {

            return escolaDao.findByCnpj(cnpj);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public boolean isEscolaCadastrada(String cnpj) {

        return (findByCnpj(cnpj) != null) ? true : false;

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void alterar(Escola escola) {

        verifyArgument(escola, Escola.class);

        escolaDao.merge(escola);

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void alterar(Escola escola, List<Curso> cursos) {

        verifyArgument(escola, Escola.class);

        if (null != cursos) {

            setListaDeCursosParaAlteracao(escola, cursos);

        }

        alterar(escola);

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void remover(Escola escola) throws EscolaContemDadosAcademicosException {

        verifyArgument(escola, Escola.class);

        if (turmaFacade.findTurmasByEscola(escola).isEmpty()) {

            escola = escolaDao.attach(escola);
            escolaDao.remove(escola);

        } else {

            throw new EscolaContemDadosAcademicosException("Escola contém dados acadêmicos. Exclusão não permitida");

        }

    }

    private void setListaDeCursosParaAlteracao(Escola escola, List<Curso> cursos) {

        escola = escolaDao.attach(escola);

        for (Curso curso : escola.getCursos()) {

            if (!cursos.contains(curso)) {

                escola.removeCurso(curso);
                curso.removeFromEscola(escola);

            } else if (!escola.getCursos().contains(curso)) {

                escola.addCurso(curso);
                curso.addToEscola(escola);

            }

        }

    }

    /**
     * @return the turmaFacade
     */
    public TurmaFacade getTurmaFacade() {
        return turmaFacade;
    }

    /**
     * @param turmaFacade the turmaFacade to set
     */
    public void setTurmaFacade(TurmaFacade turmaFacade) {
        this.turmaFacade = turmaFacade;
    }

}
