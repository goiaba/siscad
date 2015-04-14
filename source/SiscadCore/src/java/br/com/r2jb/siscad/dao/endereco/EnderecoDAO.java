/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.endereco;

import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.dao.base.BaseDAO;
import java.util.Map;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface EnderecoDAO extends BaseDAO<Endereco> {

    Map<String, String> getEstadosBrasileiros();

}
