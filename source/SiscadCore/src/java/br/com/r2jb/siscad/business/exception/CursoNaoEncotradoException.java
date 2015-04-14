/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class CursoNaoEncotradoException extends Exception {

    public CursoNaoEncotradoException() {

        super();

    }

    public CursoNaoEncotradoException(String message) {

        super(message);

    }

    public CursoNaoEncotradoException(String message, Throwable t) {

        super(message, t);

    }

}
