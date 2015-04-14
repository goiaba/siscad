/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.entity;

import java.io.Serializable;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public abstract class BaseEntity implements Serializable {

    public abstract Integer getId();

    public abstract void setId(Integer id);

}
