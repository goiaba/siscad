/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.r2jb.siscad.business.exception;

/**
 *
 * @author Bruno G. M. Corrêa <brunogmc at gmail.com>
 */
public class SituacaoMatriculaInvalidaException extends Exception {

    public SituacaoMatriculaInvalidaException() {

        super();

    }

    public SituacaoMatriculaInvalidaException(String message) {

        super(message);

    }

    public SituacaoMatriculaInvalidaException(String message, Throwable t) {

        super(message, t);

    }

}
