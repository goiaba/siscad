/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.endereco.impl;

import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.endereco.EnderecoDAO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("EnderecoDAO")
public class EnderecoDAOImpl extends BaseDAOImpl<Endereco> implements EnderecoDAO {

    @Override
    public Map<String, String> getEstadosBrasileiros() {

        getEntityManager().createNativeQuery("select * from estados").getResultList();

        return new HashMap<String, String>();

    }

}
