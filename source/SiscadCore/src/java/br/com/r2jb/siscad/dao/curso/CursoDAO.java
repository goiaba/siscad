/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.curso;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface CursoDAO extends BaseDAO<Curso> {

    List<Curso> findCursosPorProfessorEEscola(Professor professor, Escola escola);

    List<Curso> findCursosPorProfessor(Professor professor);

    List<Curso> findCursosPorEscola(Escola escola);

    List<Curso> findCursos(Professor professor, Escola escola, Periodo semestre, Ano ano);

}
