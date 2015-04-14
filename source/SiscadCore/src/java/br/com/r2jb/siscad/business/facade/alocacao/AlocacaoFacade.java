/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.alocacao;

import br.com.r2jb.siscad.business.entity.Alocacao;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface AlocacaoFacade extends BaseFacade<Alocacao> {

    boolean existsEntityWithSameUniqueKeyAttributes(Alocacao entity);

    void inserir(Alocacao entity);

    void alterar(Alocacao entity);

    void remover(Alocacao entity);

    List<Alocacao> findByExample(Alocacao entity);

}
