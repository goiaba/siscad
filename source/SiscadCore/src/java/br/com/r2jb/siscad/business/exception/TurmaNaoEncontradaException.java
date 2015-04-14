/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class TurmaNaoEncontradaException extends Exception {

    public TurmaNaoEncontradaException() {

        super();

    }

    public TurmaNaoEncontradaException(String message) {

        super(message);

    }

    public TurmaNaoEncontradaException(String message, Throwable t) {

        super(message, t);

    }

}
