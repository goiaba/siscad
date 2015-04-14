/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class SenhaFracaException extends Exception {

    public SenhaFracaException() {

        super();

    }

    public SenhaFracaException(String message) {

        super(message);

    }

    public SenhaFracaException(String message, Throwable t) {

        super(message, t);

    }

}
