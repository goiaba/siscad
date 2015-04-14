/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class AnoInvalidoException extends Exception {

    public AnoInvalidoException() {

        super();

    }

    public AnoInvalidoException(String message) {

        super(message);

    }

    public AnoInvalidoException(String message, Throwable t) {

        super(message, t);

    }

}
