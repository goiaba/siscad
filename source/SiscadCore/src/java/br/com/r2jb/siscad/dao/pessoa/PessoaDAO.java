/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.pessoa;

import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.dao.base.BaseDAO;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface PessoaDAO extends BaseDAO<Pessoa> {

    Pessoa findByCPF(String cpf);

}
