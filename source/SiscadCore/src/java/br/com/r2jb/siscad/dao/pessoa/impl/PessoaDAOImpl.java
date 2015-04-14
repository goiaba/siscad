/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.dao.pessoa.impl;

import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.dao.base.impl.BaseDAOImpl;
import br.com.r2jb.siscad.dao.pessoa.PessoaDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Repository("PessoaDAO")
public class PessoaDAOImpl extends BaseDAOImpl<Pessoa> implements PessoaDAO {

    @Override
    public Pessoa findByCPF(String cpf) {

        List<Pessoa> lstResult =  getEntityManager()
                .createNamedQuery("Pessoa.findByCpf", Pessoa.class)
                .setParameter("cpf", cpf)
                .getResultList();

        //Dado que cpf é chave única
        return lstResult.isEmpty() ? null : lstResult.get(0);

    }

}
