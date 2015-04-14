/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.facade.base;

import br.com.r2jb.siscad.business.entity.BaseEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public interface BaseFacade<T extends BaseEntity> extends Serializable {

    T find(Integer id);

    List<T> findAll();

    List<T> findAll(int start, int max);

}
