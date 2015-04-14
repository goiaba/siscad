/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class TelefoneNaoEncontradoException extends Exception {

    public TelefoneNaoEncontradoException(String message) {

        super(message);

    }

    public TelefoneNaoEncontradoException(String message, Throwable t) {

        super(message, t);

    }
}
