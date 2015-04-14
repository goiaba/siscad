/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.curso;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Professor;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import br.com.r2jb.siscad.business.util.Ano;
import br.com.r2jb.siscad.business.util.Periodo;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface CursoFacade extends BaseFacade<Curso> {

    void inserir(Curso curso);

    void alterar(Curso curso);

    void remover(Curso curso);
    
    List<Curso> findByProfessor(Professor professor);

    List<Curso> findByProfessorEEscola(Professor professor, Escola escola);

    List<Curso> findByEscola(Escola escola);

    List<Curso> findByProfessorEscolaSemestreEAno(Professor professor, Escola escola, Periodo semestre, Ano ano);

    List<Curso> findByEscolaSemestreEAno(Escola escola, Periodo semestre, Ano ano);

}