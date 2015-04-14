/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class EscolaNaoEncontradaException extends Exception {

    public EscolaNaoEncontradaException() {

        super();

    }

    public EscolaNaoEncontradaException(String message) {

        super(message);

    }

    public EscolaNaoEncontradaException(String message, Throwable t) {

        super(message, t);

    }

}
