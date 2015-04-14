/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.turma;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.entity.Turno;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.business.util.StatusTurma;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface TurmaDAO extends BaseDAO<Turma> {

    List<Turma> findTurmasPorProfessorEscolaCursoETurno(Professor professor, Escola escola, Curso curso, Turno turno);

    List<Turma> findTurmasPorProfessorEscolaECurso(Professor professor, Escola escola, Curso curso);

    List<Turma> findTurmas(Professor professor, Escola escola, Curso curso, Periodo periodo, Ano ano);

    List<Turma> findTurmasAtivasPorEscola(Escola escola);

    List<Turma> findTurmasPorEscola(Escola escola);

    List<Turma> findTurmasPorEscolaCursoSerie(Escola escola, Curso curso, Serie serie);

    List<Turma> findTurmasPorEscolaCursoSerieEStatus(Escola escola, Curso curso, Serie serie, StatusTurma status);

    List<Turma> findByEscolaCursoSerieAnoSemestre(Escola escola, Curso curso, Serie serie, Ano ano, Periodo periodo);

    List<Turma> findByExample(Turma turma);

    @Override
    Turma existsEntityWithSameUniqueKeyAttributes(Turma entity);

}
