/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class TipoDeEnderecoInvalidoException extends Exception {

    public TipoDeEnderecoInvalidoException() {

        super();

    }

    public TipoDeEnderecoInvalidoException(String message) {

        super(message);

    }

    public TipoDeEnderecoInvalidoException(String message, Throwable t) {

        super(message, t);

    }

}
