/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.serie;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface SerieFacade extends BaseFacade<Serie> {

    void inserir(Serie serie);

    void alterar(Serie serie);

    void remover(Serie serie);

    List<Serie> findSeriePorCurso(Curso curso);

    List<Serie> findSeriePorEscolaECurso(Escola escola, Curso curso);

    List<Serie> findByEscola(Escola escola);

}
