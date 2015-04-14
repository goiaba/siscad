/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.frequencia.impl;


import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.entity.Matricula;
import br.com.r2jb.siscad.business.entity.Frequencia;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.disciplina.DisciplinaFacade;
import br.com.r2jb.siscad.business.facade.matricula.MatriculaFacade;
import br.com.r2jb.siscad.business.facade.frequencia.FrequenciaFacade;
import br.com.r2jb.siscad.business.facade.periodoAvaliacao.PeriodoAvaliacaoFacade;
import br.com.r2jb.siscad.dao.frequencia.FrequenciaDAO;
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
@Service("FrequenciaFacade")
public class FrequenciaFacadeImpl extends BaseFacadeImpl<Frequencia, FrequenciaDAO> implements FrequenciaFacade {

    @Autowired private FrequenciaDAO frequenciaDAO;
    @Autowired private DisciplinaFacade disciplinaFacade;
    @Autowired private PeriodoAvaliacaoFacade periodoAvaliacaoFacade;
    @Autowired private MatriculaFacade matriculaFacade;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registrarFrequencias(List<Frequencia> frequencias) {

        verifyArgument(frequencias, List.class);

        for (Frequencia frequencia : frequencias) {

            registrarFrequencia(frequencia);

        }

    }

    @Transactional(propagation = Propagation.REQUIRED)
    private void registrarFrequencia(Frequencia frequencia) {

        verifyArgument(frequencia, Frequencia.class);

        frequenciaDAO.save(frequencia);

    }

    @Override
    public Float isFrequenciaJaRegistrada(Matricula matricula, Disciplina disciplina, PeriodoAvaliacao periodoAvaliacao) {

        verifyArgument("matricula", matricula, Matricula.class);
        verifyArgument("disciplina", disciplina, Disciplina.class);
        verifyArgument("periodoAvaliacao", periodoAvaliacao, PeriodoAvaliacao.class);

        try {

            return frequenciaDAO.isFrequenciaJaRegistrada(matricula, disciplina, periodoAvaliacao);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    @Override
    public Frequencia findByDisciplinaAvaliacaoEMatricula(String idDisciplina, String idPeriodoAvaliacao, String idMatricula) {

        verifyArgument("idDisciplina", idDisciplina, String.class);
        verifyArgument("idPeriodoAvaliacao", idPeriodoAvaliacao, String.class);
        verifyArgument("idMatricula", idMatricula, String.class);

        Disciplina disciplina = disciplinaFacade.find(Integer.parseInt(idDisciplina));
        PeriodoAvaliacao periodoAvaliacao = periodoAvaliacaoFacade.find(Integer.parseInt(idPeriodoAvaliacao));
        Matricula matricula = matriculaFacade.find(Integer.parseInt(idMatricula));

        verifyArgument("disciplina", disciplina, Disciplina.class);
        verifyArgument("periodoAvaliacao", periodoAvaliacao, PeriodoAvaliacao.class);
        verifyArgument("matricula", matricula, Matricula.class);

        try {

            return frequenciaDAO.findByDisciplinaAvaliacaoEMatricula(disciplina, periodoAvaliacao, matricula);

        } catch (EmptyResultDataAccessException ex) {

            return null;

        }

    }

    public void setFrequenciaDAO(FrequenciaDAO frequenciaDAO) {
        this.frequenciaDAO = frequenciaDAO;
    }
    
}
