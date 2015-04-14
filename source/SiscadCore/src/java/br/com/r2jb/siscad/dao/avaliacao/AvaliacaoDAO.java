/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.avaliacao;

import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface AvaliacaoDAO extends BaseDAO<Avaliacao> {

    List<Avaliacao> findAvaliacoesPorDisciplina(Disciplina disciplina);

    Avaliacao existsEntityWithSameUniqueKeyAttributes(Avaliacao entity);

    List<Avaliacao> findByExample(Avaliacao entity);

}
