/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Ricardo
 */
public class EnderecoRequeridoException extends Exception {

    public EnderecoRequeridoException() {

        super();

    }

    public EnderecoRequeridoException(String message) {

        super(message);

    }

    public EnderecoRequeridoException(String message, Throwable t) {

        super(message, t);

    }

}
