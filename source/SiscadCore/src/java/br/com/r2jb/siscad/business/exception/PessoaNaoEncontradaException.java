/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class PessoaNaoEncontradaException extends Exception {

    public PessoaNaoEncontradaException() {

        super();

    }

    public PessoaNaoEncontradaException(String message) {

        super(message);

    }

    public PessoaNaoEncontradaException(String message, Throwable t) {

        super(message, t);

    }

}
