/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.pessoa.impl;


import br.com.r2jb.siscad.business.entity.Pessoa;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.pessoa.PessoaFacade;
import br.com.r2jb.siscad.dao.pessoa.PessoaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("PessoaFacade")
public class PessoaFacadeImpl extends BaseFacadeImpl<Pessoa, PessoaDAO> implements PessoaFacade {

    @Autowired private PessoaDAO pessoaDAO;

    @Override
    public Pessoa findByCPF(String cpf) {

        return pessoaDAO.findByCPF(cpf);

    }

    public void setPessoaDAO(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

}
