/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.alocacao;

import br.com.r2jb.siscad.business.entity.Alocacao;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface AlocacaoDAO extends BaseDAO<Alocacao> {

    List<Alocacao> findByExample(Alocacao entity);

}
