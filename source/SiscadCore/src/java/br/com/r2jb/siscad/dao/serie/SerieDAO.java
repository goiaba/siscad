/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.serie;

import br.com.r2jb.siscad.business.entity.Curso;
import br.com.r2jb.siscad.business.entity.Escola;
import br.com.r2jb.siscad.business.entity.Serie;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface SerieDAO extends BaseDAO<Serie> {

    List<Serie> findSeriePorEscolaECurso(Escola escola, Curso curso);

    List<Serie> findByEscola(Escola escola);

    List<Serie> findSeriePorCurso(Curso curso);

}
