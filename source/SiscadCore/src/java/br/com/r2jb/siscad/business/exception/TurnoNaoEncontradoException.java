/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class TurnoNaoEncontradoException extends Exception {

    public TurnoNaoEncontradoException() {

        super();

    }

    public TurnoNaoEncontradoException(String message) {

        super(message);

    }

    public TurnoNaoEncontradoException(String message, Throwable t) {

        super(message, t);

    }

}
