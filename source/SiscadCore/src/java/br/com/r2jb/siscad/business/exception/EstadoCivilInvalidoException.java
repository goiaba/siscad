/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class EstadoCivilInvalidoException extends Exception {

    public EstadoCivilInvalidoException() {

        super();

    }

    public EstadoCivilInvalidoException(String message) {

        super(message);

    }

    public EstadoCivilInvalidoException(String message, Throwable t) {

        super(message, t);

    }

}
