/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.managedBean;

import br.com.r2jb.siscad.business.entity.BaseEntity;
import com.rits.cloning.Cloner;
import java.util.Collections;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public abstract class AbstractCrudBean<T extends BaseEntity> extends BaseBean {

    public enum Mode {

        CREATE,
        RETRIEVE,
        UPDATE,
        DELETE;

    }

    private Mode mode;
    private String searchTerm;
    private T entity;
    private List<T> resultList;
    private DataModel<T> dataModel;
    private int index = -1;

    public void clear() {

        this.index = -1;
        this.entity = null;
        this.dataModel = null;
        this.resultList = null;

    }

    public List<T> getResultList() {

        if (null == this.resultList) {

            this.resultList = Collections.EMPTY_LIST;

        }

        return this.resultList;

    }

    public DataModel<T> getDataModel() {

        if (null == this.dataModel || (null != this.resultList && !this.dataModel.getWrappedData().equals(this.resultList))) {

            this.dataModel = new ListDataModel<T>(this.getResultList());

        }

        return this.dataModel;

    }

    public String create() {

        handleCreate(entity);

        return null;

    }

    public String retrieve() {

        this.resultList = handleRetrieve(this.searchTerm);

        return null;

    }

    public String update() {

        handleUpdate(entity);

        resultList.remove(index);
        resultList.add(index, entity);

        back();

        return null;
        
    }

    public String delete() {

        index = resultList.indexOf(entity);

        if (handleDelete(entity)) {

            resultList.remove(index);

        }

        return null;

    }

    public void back() {

        handlePrepareToBack();
        this.entity = null;
        this.setMode(Mode.RETRIEVE);

    }

    public void prepareToUpdate() {

        index = this.getDataModel().getRowIndex();

        T clone = new Cloner().shallowClone(this.getDataModel().getRowData());

        this.setEntity(clone);

        handlePrepareToUpdate();
        
        this.setMode(Mode.UPDATE);

    }

    public Boolean getRenderTable() {
        return (null == resultList || resultList.isEmpty()) ? Boolean.FALSE : Boolean.TRUE;
    }

    public Boolean getCreateMode() {
        return (null != this.getMode() && this.getMode() == Mode.CREATE) ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean getUpdateMode() {
        return (null != this.getMode() && this.getMode() == Mode.UPDATE) ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean getDeleteMode() {
        return (null != this.getMode() && this.getMode() == Mode.DELETE) ? Boolean.TRUE : Boolean.FALSE;
    }

    public Boolean getRetrieveMode() {
        return (null != this.getMode() && this.getMode() == Mode.RETRIEVE) ? Boolean.TRUE : Boolean.FALSE;
    }

    protected abstract void handleCreate(T entity);

    protected abstract List<T> handleRetrieve(String searchTerm);

    protected abstract void handleUpdate(T entity);

    protected abstract boolean handleDelete(T entity);

    protected abstract void handlePrepareToUpdate();

    protected void handlePrepareToBack(){};

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

}
