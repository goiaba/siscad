/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.periodoAvaliacao;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.exception.UniqueKeyConstraintViolatedException;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface PeriodoAvaliacaoFacade extends BaseFacade<PeriodoAvaliacao> {

    List<Ano> findAnosPorProfessor(Professor professor);
    
    List<Periodo> findSemestresPorProfessor(Professor professor);

    List<Ano> findAnos(Professor professor, Escola escola, Curso curso, Periodo semestre);

    List<Periodo> findSemestres(Professor professor, Escola escola, Curso curso, Ano ano);

    List<PeriodoAvaliacao> findByCursoPeriodoEAno(Curso curso, Periodo periodo, Ano ano);

    List<PeriodoAvaliacao> findByCurso(Curso curso, Boolean anoAtualOuMaisRecente);

    boolean existsEntityWithSameUniqueKeyAttributes(PeriodoAvaliacao entity);

    List<PeriodoAvaliacao> findByExample(PeriodoAvaliacao entity);

    void inserir(PeriodoAvaliacao entity) throws UniqueKeyConstraintViolatedException;

    void alterar(PeriodoAvaliacao entity) throws UniqueKeyConstraintViolatedException;

    void remover(PeriodoAvaliacao entity) throws EntidadePossuiRelacionamentoException;

}
