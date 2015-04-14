/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.nota.impl;


import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Nota;
import br.com.r2jb.siscad.business.facade.avaliacao.AvaliacaoFacade;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.disciplina.DisciplinaFacade;
import br.com.r2jb.siscad.business.facade.matricula.MatriculaFacade;
import br.com.r2jb.siscad.business.facade.nota.NotaFacade;
import br.com.r2jb.siscad.dao.nota.NotaDAO;
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
@Service("NotaFacade")
public class NotaFacadeImpl extends BaseFacadeImpl<Nota, NotaDAO> implements NotaFacade {

    @Autowired private NotaDAO notaDAO;
    @Autowired private DisciplinaFacade disciplinaFacade;
    @Autowired private AvaliacaoFacade avaliacaoFacade;
    @Autowired private MatriculaFacade matriculaFacade;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarNotas(List<Nota> notas) {

        verifyArgument(notas, List.class);

        for (Nota nota : notas) {

            registrarNota(nota);

        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void registrarNota(Nota nota) {

        verifyArgument(nota, Nota.class);

        notaDAO.save(nota);

    }

    @Override
    public Float isNotaJaRegistrada(Matricula matricula, Disciplina disciplina, Avaliacao avaliacao) {

        verifyArgument("matricula", matricula, Matricula.class);
        verifyArgument("disciplina", disciplina, Disciplina.class);
        verifyArgument("avaliacao", avaliacao, Avaliacao.class);

        try {

            return notaDAO.isNotaJaRegistrada(matricula, disciplina, avaliacao);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public Nota findByDisciplinaAvaliacaoEMatricula(String idDisciplina, String idAvaliacao, String idMatricula) {

        verifyArgument("idDisciplina", idDisciplina, String.class);
        verifyArgument("idAvaliacao", idAvaliacao, String.class);
        verifyArgument("idMatricula", idMatricula, String.class);

        Disciplina disciplina = disciplinaFacade.find(Integer.parseInt(idDisciplina));
        Avaliacao avaliacao = avaliacaoFacade.find(Integer.parseInt(idAvaliacao));
        Matricula matricula = matriculaFacade.find(Integer.parseInt(idMatricula));

        verifyArgument("disciplina", disciplina, Disciplina.class);
        verifyArgument("avaliacao", avaliacao, Avaliacao.class);
        verifyArgument("matricula", matricula, Matricula.class);

        try {

            return notaDAO.findByDisciplinaAvaliacaoEMatricula(disciplina, avaliacao, matricula);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    public void setNotaDAO(NotaDAO notaDAO) {
        this.notaDAO = notaDAO;
    }
    
}
