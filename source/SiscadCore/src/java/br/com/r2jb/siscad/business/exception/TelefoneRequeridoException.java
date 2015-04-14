/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Ricardo
 */
public class TelefoneRequeridoException extends Exception {

    public TelefoneRequeridoException() {

        super();

    }

    public TelefoneRequeridoException(String message) {

        super(message);

    }

    public TelefoneRequeridoException(String message, Throwable t) {

        super(message, t);

    }
}
