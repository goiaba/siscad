/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class SemestreInvalidoException extends Exception {

    public SemestreInvalidoException() {

        super();

    }

    public SemestreInvalidoException(String message) {

        super(message);

    }

    public SemestreInvalidoException(String message, Throwable t) {

        super(message, t);

    }
}
