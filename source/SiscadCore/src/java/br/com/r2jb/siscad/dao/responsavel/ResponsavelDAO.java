/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.responsavel;

import br.com.r2jb.siscad.business.entity.Responsavel;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface ResponsavelDAO extends BaseDAO<Responsavel> {

    Responsavel findByCpf(String cpf);

    List<Responsavel> findByNome(String nome);

    List<Responsavel> findByNome(String nome, Boolean ativo);

}
