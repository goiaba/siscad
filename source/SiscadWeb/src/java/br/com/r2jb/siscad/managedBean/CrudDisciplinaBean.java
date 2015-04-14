/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.ServiceLocator;
import br.com.r2jb.siscad.business.entity.Disciplina;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException;
import br.com.r2jb.siscad.business.exception.EntidadePossuiRelacionamentoException.Relacionamento;
import br.com.r2jb.siscad.business.facade.disciplina.DisciplinaFacade;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
@ManagedBean
@ViewScoped
public class CrudDisciplinaBean extends AbstractCrudBean<Disciplina> {

    private DisciplinaFacade disciplinaFacade = ServiceLocator.getFacade(DisciplinaFacade.class);

    @PostConstruct
    public void init() {

        resetBean();

    }

    private void resetBean() {

        setEntity(new Disciplina());

    }

    @Override
    protected void handleCreate(Disciplina entity) {

        try {

            if (!disciplinaFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                disciplinaFacade.inserir(entity);
                resetBean();
                addFacesMessage("cadastroSucesso");

            } else {

                addFacesMessage("erroConstraint");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CDB.handleCreate.Exception", e);

        }

    }

    @Override
    protected List<Disciplina> handleRetrieve(String searchTerm) {

        try {

            if (null != getEntity()) {

                return disciplinaFacade.findByExample(getEntity());

            }

            return Collections.EMPTY_LIST;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CDB.handleResultList.Exception", e);
            return null;

        }

    }

    @Override
    protected void handleUpdate(Disciplina entity) {

        try {

            if (!disciplinaFacade.existsEntityWithSameUniqueKeyAttributes(entity)) {

                disciplinaFacade.alterar(entity);
                addFacesMessage("alteradoSucesso");

            } else {

                addFacesMessage("erroConstraint");

            }

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CDB.handleUpdate.Exception", e);

        }

    }

    @Override
    protected boolean handleDelete(Disciplina entity) {

        try {

            disciplinaFacade.remover(entity);
            addFacesMessage("remocaoSucesso");

            return true;

        } catch (EntidadePossuiRelacionamentoException e) {

            for (Relacionamento r : e.getRelacionamentosPreenchidos()) {

                addFacesMessage(r.getDescricao());

            }

            return false;

        } catch (Exception e) {

            addFacesMessage("erroInesperado", FacesMessage.SEVERITY_ERROR);
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "CDB.handleDelete.Exception", e);

            return false;

        }

    }

    @Override
    protected void handlePrepareToUpdate() {

    }

}
