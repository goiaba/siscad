/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.avaliacao;

import br.com.r2jb.siscad.business.entity.Avaliacao;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.facade.base.BaseFacade;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface AvaliacaoFacade extends BaseFacade<Avaliacao> {

    List<Avaliacao> findAvaliaçõesPorDisciplina(Disciplina disciplina);

    void remover(Avaliacao entity) throws EntidadePossuiRelacionamentoException;

    void alterar(Avaliacao entity);

    void inserir(Avaliacao entity);

    boolean existsEntityWithSameUniqueKeyAttributes(Avaliacao entity);

    List<Avaliacao> findByExample(Avaliacao entity);

}
