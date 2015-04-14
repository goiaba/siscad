/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.endereco.impl;


import br.com.r2jb.siscad.business.entity.Endereco;
import br.com.r2jb.siscad.business.entity.PessoaEndereco;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.endereco.EnderecoFacade;
import br.com.r2jb.siscad.dao.endereco.EnderecoDAO;
import br.com.r2jb.siscad.dao.pessoaEndereco.PessoaEnderecoDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("EnderecoFacade")
public class EnderecoFacadeImpl extends BaseFacadeImpl<Endereco, EnderecoDAO> implements EnderecoFacade {

    @Autowired private EnderecoDAO enderecoDAO;
    @Autowired private PessoaEnderecoDAO pessoaEnderecoDAO;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserirEndereco(Endereco endereco) {

        verifyArgument(endereco, Endereco.class);
        
        enderecoDAO.save(endereco);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Endereco alterarEndereco(Endereco endereco) {

        verifyArgument(endereco, Endereco.class);
        
        return enderecoDAO.merge(endereco);
            
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerEndereco(PessoaEndereco pessoaEndereco) {

        verifyArgument(pessoaEndereco, PessoaEndereco.class);
        
        Endereco endereco = enderecoDAO.attach(pessoaEndereco.getEndereco());
        enderecoDAO.remove(endereco);
        
        pessoaEndereco = pessoaEnderecoDAO.attach(pessoaEndereco);
        pessoaEnderecoDAO.remove(pessoaEndereco);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerEndereco(Collection<PessoaEndereco> listaEndereco) {

        verifyArgument(listaEndereco, Collection.class);
        
        for (PessoaEndereco pessoaEndereco : listaEndereco) {

            removerEndereco(pessoaEndereco);

        }

    }

    public void setEnderecoDAO(EnderecoDAO enderecoDAO) {
        this.enderecoDAO = enderecoDAO;
    }

    public void setPessoaEnderecoDAO(PessoaEnderecoDAO pessoaEnderecoDAO) {
        this.pessoaEnderecoDAO = pessoaEnderecoDAO;
    }
    
}
