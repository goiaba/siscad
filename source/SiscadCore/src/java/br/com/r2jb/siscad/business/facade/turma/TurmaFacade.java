/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.turma;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.entity.Turma;
import br.com.r2jb.siscad.business.entity.Turno;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.business.util.StatusTurma;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface TurmaFacade extends BaseFacade<Turma> {

    List<Turma> findTurmasAtivasByEscola(Escola escola);

    List<Turma> findTurmasByEscola(Escola escola);

    List<Turma> findByProfessorEscolaECurso(Professor professor, Escola escola, Curso curso);

    List<Turma> findByProfessorEscolaCursoETurno(Professor professor, Escola escola, Curso curso, Turno turno);

    List<Turma> findByProfessorEscolaCursoSemestreEAno(Professor professor, Escola escola, Curso curso, Periodo semestre, Ano ano);

    List<Turma> findByEscolaCursoSerieEStatus(Escola escola, Curso curso, Serie serie, StatusTurma status);

    List<Turma> findByEscolaCursoSerie(Escola escola, Curso curso, Serie serie);

    List<Turma> findByEscolaCursoSerieAnoSemestre(Escola escola, Curso curso, Serie serie, Ano ano, Periodo semestre);

    List<Turma> findAbertasEFechadasByEscolaCursoSerie(Escola escola, Curso curso, Serie serie);

    List<Turma> findByExample(Turma entity);

    void remover(Turma entity);

    void alterar(Turma entity);

    void inserir(Turma entity);

    boolean existsEntityWithSameUniqueKeyAttributes(Turma entity);

}
