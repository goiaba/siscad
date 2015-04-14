/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.periodoAvaliacao;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.PeriodoAvaliacao;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface PeriodoAvaliacaoDAO extends BaseDAO<PeriodoAvaliacao> {

    List<Ano> findAnosPorProfessor(Professor professor);

    List<Periodo> findSemestresPorProfessor(Professor professor);

    List<Periodo> findSemestres(Professor professor, Escola escola, Curso curso, Ano ano);

    List<Ano> findAnos(Professor professor, Escola escola, Curso curso, Periodo semestre);

    List<PeriodoAvaliacao> findByCursoPeriodoEAno(Curso curso, Periodo periodo, Ano ano);

    List<PeriodoAvaliacao> findByCurso(Curso curso, boolean anoAtualOuMaisRecente);

    PeriodoAvaliacao existsEntityWithSameUniqueKeyAttributes(PeriodoAvaliacao entity);

    List<PeriodoAvaliacao> findByExample(PeriodoAvaliacao entity);

}
