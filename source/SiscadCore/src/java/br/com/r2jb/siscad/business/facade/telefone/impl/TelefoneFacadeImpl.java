/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.facade.telefone.impl;


import br.com.r2jb.siscad.business.entity.PessoaTelefone;
import br.com.r2jb.siscad.business.entity.Telefone;
import br.com.r2jb.siscad.business.facade.base.impl.BaseFacadeImpl;
import br.com.r2jb.siscad.business.facade.telefone.TelefoneFacade;
import br.com.r2jb.siscad.dao.pessoaTelefone.PessoaTelefoneDAO;
import br.com.r2jb.siscad.dao.telefone.TelefoneDAO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@Service("TelefoneFacade")
public class TelefoneFacadeImpl extends BaseFacadeImpl<Telefone, TelefoneDAO> implements TelefoneFacade {

    @Autowired private TelefoneDAO telefoneDAO;
    @Autowired private PessoaTelefoneDAO pessoaTelefoneDAO;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserirTelefone(Telefone telefone) {

        verifyArgument(telefone, Telefone.class);

        telefoneDAO.save(telefone);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Telefone alterarTelefone(Telefone telefone) {

        verifyArgument(telefone, Telefone.class);

        return telefoneDAO.merge(telefone);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerTelefone(PessoaTelefone pessoaTelefone) {
        
        verifyArgument(pessoaTelefone, PessoaTelefone.class);

        Telefone telefone = telefoneDAO.attach(pessoaTelefone.getTelefone());
        telefoneDAO.remove(telefone);
        
        pessoaTelefone = pessoaTelefoneDAO.attach(pessoaTelefone);
        pessoaTelefoneDAO.remove(pessoaTelefone);

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removerTelefone(Collection<PessoaTelefone> listaTelefone) {

        verifyArgument(listaTelefone, Collection.class);
        
        for (PessoaTelefone pessoaTelefone : listaTelefone) {

            removerTelefone(pessoaTelefone);

        }

    }

}
